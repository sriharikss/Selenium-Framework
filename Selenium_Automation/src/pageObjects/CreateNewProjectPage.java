package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;

import driverSupport.WebDriverExtensions;

public class CreateNewProjectPage{	
	
	private String pageHeader = "Create New Project";
	private WebDriver webDriver;
	
	@FindBy(how = How.XPATH, using ="//select[@name='customerId']" ) 
	private WebElement _ddCustomer;
	
	@FindBy(how = How.XPATH, using = "//td/input[@name='name']") 
	private WebElement _txtProjectName;
	
	@FindBy(how = How.XPATH, using = "//td/textarea[@name='description']") 
	private WebElement _txtDescription;
	
	@FindBy(how = How.XPATH, using = "//td/input[@name='createProjectSubmit']") 
	private WebElement _btnCreateProject;
	
	@FindBy(how = How.XPATH, using ="//td[@class='pagetitle' and contains(text(), 'New Project')]" ) 
	private WebElement _lblpageHeader;

	public CreateNewProjectPage(WebDriver driver){
		webDriver = driver;
		waitForPageLoad();
		PageFactory.initElements(driver, this);
		AssertJUnit.assertEquals("This is not Create Project page", _lblpageHeader.getText().trim(), pageHeader);
	}
	
	public void createProject(String customerName, String projectName, String description){
		Select _obCustomer = new Select(_ddCustomer);
		_obCustomer.selectByVisibleText(customerName);
		_txtProjectName.sendKeys(projectName);
		_txtDescription.sendKeys(description);
		_btnCreateProject.click();
	}
	
	public void waitForPageLoad() {
		WebDriverExtensions.waitforElementByXpath(webDriver,
				"//td[@class='pagetitle' and contains(text(), 'New Project')]");
	}
}
