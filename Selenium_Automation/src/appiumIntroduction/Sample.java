package appiumIntroduction;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import driverSupport.WebDriverExtensions;
import pageObjects.IndexPage;
import pageObjects.LoginPage;

public class Sample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:8080/login.do");
		LoginPage _loginPage = new LoginPage(driver);
		_loginPage.loginWithValidCredentials("admin", "manager");
		List<WebElement> elements = driver.findElements(By.xpath("//span[@class='dashedLink']"));
		System.out.println(elements.size());
		System.out.println(elements.get(0).getText());
		WebDriverExtensions.waitforElementByClass(driver, "dashedLink");
		WebElement element = driver.findElement(By.className("dashedLink"));
		element.click();
		
		
	}

}
