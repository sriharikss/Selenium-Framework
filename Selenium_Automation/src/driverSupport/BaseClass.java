package driverSupport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.*;
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

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class BaseClass extends AbstractWebDriverEventListener {

	protected static EventFiringWebDriver driver;
	private static List<String> logText = new ArrayList<String>();
	static SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy HH_mm_ss");
	static Date dateobj = new Date();
	private static PrintWriter pw;

	private WebDriver determineDriver() {
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
		driver.navigate().to(Config.atUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		logText.add("Clicking on the button : " + element.getText());
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		logText.add("Navigating to the url : " + url);
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		logText.add("Landed to the page located at : " + driver.getTitle());
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		logText.add("Change the value of the field : "
				+ element.getAttribute("name"));
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		logText.add("Value of the field : " + element.getAttribute("name")
				+ " is changed to : " + element.getAttribute("value"));
	}

	@Override
	public void onException(Throwable arg0, WebDriver arg1) {
		logText.add("There is an exception in the script, please find the below error description \n"
				+ arg0.getStackTrace()+"\n"+arg0.getMessage());
	}

	public static void takeSnapShot(String fileName) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(Config.screenshotFolder+fileName);
		FileUtils.copyFile(srcFile,destFile);
	}

	@AfterMethod
	public static void tearDown() {
		driver.close();
		driver.quit();
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
	
	public static String[][] getExcelData(String fileName, String sheetName) {
		String[][] arrayExcelData = null;

		FileInputStream fis;
		try {
			fis = new FileInputStream(fileName);

			Workbook wb = Workbook.getWorkbook(fis);
			Sheet sh = wb.getSheet(sheetName);

			int noOfCols = sh.getColumns();
			int noOfRows = sh.getRows();

			arrayExcelData = new String[noOfCols - 1][noOfRows - 1];

			for (int i = 1; i < noOfRows; i++) {
				for (int j = 1; j < noOfCols; j++) {
					arrayExcelData[i][j] = sh.getCell(i, j).getContents();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}

}
