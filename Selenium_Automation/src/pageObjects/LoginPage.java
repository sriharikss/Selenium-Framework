package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;

public class LoginPage{

	private String pageHeader = "Login";
	private WebDriver webDriver;
	
	@FindBy(how = How.NAME, using ="username" ) 
	private WebElement _txtUsername;
	
	@FindBy(how = How.NAME, using ="pwd" ) 
	private WebElement _txtPassword;
	
	@FindBy(how = How.ID, using ="loginButton" ) 
	private WebElement _btnLogin;
	
		public LoginPage(WebDriver driver){
			webDriver = driver;
			PageFactory.initElements(driver, this);
			AssertJUnit.assertTrue("This is not login page", driver.getTitle().trim().contains(pageHeader));
	}

	public IndexPage loginWithValidCredentials(String userName, String password) {
		
		_txtUsername.clear();
		_txtUsername.sendKeys(userName);
		_txtPassword.clear();
		_txtPassword.sendKeys(password);
		_btnLogin.click();
		return new IndexPage(webDriver);
	}

}
