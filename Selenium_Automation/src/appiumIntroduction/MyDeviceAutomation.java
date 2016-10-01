package appiumIntroduction;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MyDeviceAutomation {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		
		File classRootPath = new File(System.getProperty("user.dir"));
		File appDir = new File(classRootPath, "//Apps//");
		File app = new File(appDir, "base.apk");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "XT1033");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.0.2");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.bt.bms");
		capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.bt.bms.activities.MainTabActivity");
		
		AppiumDriver<WebElement> driver = new AndroidDriver<WebElement>(
				new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(5000);
		WebElement btnTickets = driver.findElement(By.id("tab_tickets"));
		btnTickets.click();
		Thread.sleep(3000);
		driver.findElementByXPath("//android.widget.TextView[@text='MOVIES']").click();
		driver.closeApp();
		driver.quit();
	}
}
