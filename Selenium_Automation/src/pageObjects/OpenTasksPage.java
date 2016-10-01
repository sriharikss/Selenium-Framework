package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;

public class OpenTasksPage{
	
	private String pageHeader = "Open Tasks";
	private WebDriver webDriver;
	
	@FindBy(how = How.CLASS_NAME, using ="pagetitle" ) 
	private WebElement _lblPageHeader;
	
	@FindBy(how = How.LINK_TEXT, using ="Projects & Customers" ) 
	private WebElement _lnkProjectandCustomers;
	
	@FindBy(how = How.XPATH, using ="//span[text()='Create Tasks']" ) 
	private WebElement _btnCreateTasks;

	public OpenTasksPage(WebDriver driver){
		webDriver = driver;
		PageFactory.initElements(driver, this);
		AssertJUnit.assertEquals("This is not Tasks Page", _lblPageHeader.getText().trim(), pageHeader);
	}
	
	public ProjectAndCustomersPage navigateToProjectAndCustomersPage(){
		_lnkProjectandCustomers.click();
		return new ProjectAndCustomersPage(webDriver);
	}
	public CreateNewTasksPage naviagteToCreateTasksPage(){
		_btnCreateTasks.click();
		return new CreateNewTasksPage(webDriver);
	}
}
