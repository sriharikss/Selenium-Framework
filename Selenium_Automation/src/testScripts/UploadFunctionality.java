package testScripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import driverSupport.TestReports;

@Listeners(TestReports.class)
public class UploadFunctionality {
	
	@Test
	public void uploadUsingAutoIt() throws IOException, InterruptedException{
		WebDriver driver = new FirefoxDriver();
		Runtime.getRuntime().exec("G:\\Testing\\Selenium\\Tutorial\\AutoIt\\UploadFile.exe");
		driver.navigate().to("http://www.2shared.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("upField")).click();
		driver.findElement(By.cssSelector("input[title='Upload file']")).click();
		driver.findElement(By.cssSelector("img[alt='Close']")).click();
		driver.quit();
	}

}
