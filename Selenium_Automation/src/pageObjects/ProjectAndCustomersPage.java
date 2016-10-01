package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;

import driverSupport.WebDriverExtensions;

public class ProjectAndCustomersPage{
	
	private String pageHeader = "Active Customers and Projects";
	private WebDriver webDriver;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Create Project']")
	private WebElement _btnCreateProject;
	
	@FindBy(how = How.XPATH, using = "//td[@class='pagetitle' and contains(text(), 'Customer')]")
	private WebElement _lblpageHeader;
	
	public ProjectAndCustomersPage(WebDriver driver){
		webDriver = driver;
		waitForPageLoad();
		PageFactory.initElements(driver, this);
		AssertJUnit.assertTrue("This is not Project and Customers Page", _lblpageHeader.getText().trim().equalsIgnoreCase(pageHeader));
	}


	public CreateNewProjectPage navigateToCreateProject(){
		_btnCreateProject.click();
		return new CreateNewProjectPage(webDriver);
	}
	
	public void waitForPageLoad(){
		WebDriverExtensions.waitforElementByXpath(webDriver, "//td[@class='pagetitle' and contains(text(), 'Customer')]");
	}

}
