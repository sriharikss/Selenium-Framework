package pageObjects;

import org.openqa.selenium.By;
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
	
	@FindBy(how = How.XPATH, using ="//button[@id='ext-gen23' and contains(text(),'Customer')]" ) 
	private WebElement _ddCustomer;
	
	@FindBy(how = How.ID, using = "projectPopup_projectNameField") 
	private WebElement _txtProjectName;
	
	@FindBy(how = How.ID, using = "projectPopup_projectDescriptionField") 
	private WebElement _txtDescription;
	
	@FindBy(how = How.ID, using = "projectPopup_commitBtn") 
	private WebElement _btnCreateProject;
	
	@FindBy(how = How.CSS, using ="#projectPopup_titlePlaceholder" ) 
	private WebElement _lblpageHeader;

	public CreateNewProjectPage(WebDriver driver){
		webDriver = driver;
		waitForPageLoad();
		PageFactory.initElements(driver, this);
		AssertJUnit.assertEquals("This is not Create Project page", _lblpageHeader.getText().trim(), pageHeader);
	}
	
	public IndexPage createProject(String customerName, String projectName, String description){
		_ddCustomer.click();
		WebElement customer = webDriver.findElement(By.xpath("//a[text()='"+customerName.trim()+"']"));
		customer.click();
		_txtProjectName.sendKeys(projectName);
		_txtDescription.sendKeys(description);
		_btnCreateProject.click();
		return new IndexPage(webDriver);
	}
	
	public void waitForPageLoad() {
		WebDriverExtensions.waitforElementByCssSelector(webDriver,
				"#projectPopup_titlePlaceholder");
	}
}
