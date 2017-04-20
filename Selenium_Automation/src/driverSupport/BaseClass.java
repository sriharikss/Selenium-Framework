package driverSupport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass extends AbstractWebDriverEventListener {

	protected static EventFiringWebDriver driver;
	private static List<String> logText = new ArrayList<String>();
	static SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy HH_mm_ss");
	static Date dateobj = new Date();
	private static PrintWriter pw;
	public static Logger log = Logger.getLogger("devpinoyLogger");

	private WebDriver determineDriver() {
		log.info("Browser is configured to: "+Config.browser);
		if (Config.browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"Drivers//chromedriver.exe");
			return new ChromeDriver();
		} else if (Config.browser.equalsIgnoreCase("Firefox")) {
			return new FirefoxDriver();
		} else if (Config.browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver",
					"Drivers//IEDriverServer.exe");
			return new InternetExplorerDriver();
		} else
			throw new IllegalArgumentException("Browser is not correct");
	}

	@BeforeMethod
	public void setup() {
		driver = new EventFiringWebDriver(determineDriver());
		driver.register(new BaseClass());
		log.debug("Launched"+Config.browser);
		driver.navigate().to(Config.atUrl);
		log.info("Launching the URL"+Config.atUrl);
		driver.manage().window().maximize();
		log.debug("Maximizing the browser window");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		log.debug("Adding the implicit wait 30 seconds");
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		log.debug(
				"Clicking on the button with text: " + element.getText() + "\n class:" + element.getAttribute("class")
						+ "\n id" + element.getAttribute("id") + "\n name:" + element.getAttribute("name"));
		logText.add("Clicking on the button : " + element.getText());
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		log.debug("Navigating to the url : " + url);
		logText.add("Navigating to the url : " + url);
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		log.debug("Landed to the page located at : " + driver.getCurrentUrl());
		logText.add("Landed to the page located at : " + driver.getCurrentUrl());
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		log.debug("Change the value of the element with text: " + element.getText() + "\n class:"
				+ element.getAttribute("class") + "\n id" + element.getAttribute("id") + "\n name:"
				+ element.getAttribute("name"));
		logText.add("Change the value of the field : " + element.getAttribute("name"));
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		log.debug(
				"Value of the element with text: " + element.getText() + "\n class:" + element.getAttribute("class")
						+ "\n id" + element.getAttribute("id") + "\n name:" + element.getAttribute("name")+ " is changed to : "
								+ element.getAttribute("value"));
		logText.add("Value of the field : " + element.getAttribute("name") + " is changed to : "
				+ element.getAttribute("value"));
	}

	@Override
	public void onException(Throwable arg0, WebDriver arg1) {
		log.debug("There is an exception in the script, please find the below error description \n"
				+ arg0.getStackTrace()+" at URL"+arg1.getCurrentUrl());
		logText.add("There is an exception in the script, please find the below error description \n"
				+ arg0.getStackTrace());
	}

	public static void takeSnapShot(String fileName) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		log.debug("Captured the screenshot");
		File destFile = new File(Config.screenshotFolder + fileName);
		FileUtils.copyFile(srcFile, destFile);
		log.info("Screenshot placed at location:"+destFile);
	}

	@AfterMethod
	public static void tearDown() {
		driver.close();
		log.info("Closing the driver");
		driver.quit();
		log.info("Quitting the driver");
		try {
			printAndSaveLogToTextFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printAndSaveLogToTextFile() throws Exception {
		FileWriter fw = new FileWriter(Config.logFolder + "log "
				+ df.format(dateobj));
		pw = new PrintWriter(fw);
		for (String log : logText) {
			pw.println(log);
			pw.println();
			pw.flush();
		}
		pw.close();
		fw.close();
	}
}