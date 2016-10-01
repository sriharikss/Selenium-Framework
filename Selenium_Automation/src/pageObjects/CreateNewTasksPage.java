package pageObjects;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import driverSupport.WebDriverExtensions;

public class CreateNewTasksPage{
	
	private String pageHeader = "Create New Tasks";
	private WebDriver webDriver;
	
	@FindBy(how = How.XPATH, using ="//tr/td[4]/select[@name='customerId']" ) 
	private WebElement _ddCustomer;
	
	@FindBy(how = How.XPATH, using ="//tr/td[4]/select[@name='projectId']" ) 
	private WebElement _ddProject;
	
	@FindBy(how = How.XPATH, using ="//tr/td/input[@id='task[0].name']" ) 
	private WebElement _txtTaskName1;
	
	@FindBy(how = How.XPATH, using ="//input[@id='task[0].deadline']" ) 
	private WebElement _txtDeadLine;
	
	@FindBy(how = How.XPATH, using ="//select[@id='task[0].billingType']" ) 
	private WebElement _ddBillingType;
	
	@FindBy(how = How.XPATH, using ="//input[@id='task[0].markedToBeAddedToUserTasks']" ) 
	private WebElement _cbAddToMyTimeTrack;
	
	@FindBy(how = How.XPATH, using ="//input[@class='hierarchy_element_wide_button']" ) 
	private WebElement _btnCreateTask;
	
	@FindBy(how = How.XPATH, using ="//td[@class='pagetitle' and contains(text(),'Create New Task')]" ) 
	private WebElement _lblpageHeader;
	
	public CreateNewTasksPage(WebDriver driver){
		webDriver = driver;
		waitForPageLoad();
		PageFactory.initElements(driver, this);
		if (!driver.getTitle().trim().contains(pageHeader)) {
			throw new NoSuchWindowException("This is not Create New Tasks page.");
		}
	}

	public OpenTasksPage createNewTask(String customer, String project, String taskName,
			String deadLine, String billingType) {

		selectCustomerName(customer);
		selectProject(project);
		enterTaskName(taskName);
		enterDeadLine(deadLine);
		selectBillingType(billingType);
		checkAddToMyTimeTrack();
		createTask();
		return new OpenTasksPage(webDriver);
	}
	
	public void selectCustomerName(String customerName){
		Select _objCustomer = new Select(_ddCustomer);
		_objCustomer.selectByVisibleText(customerName);
	}
	
	public void selectProject(String projectName){
		Select _objProject = new Select(_ddProject);
		_objProject.selectByVisibleText(projectName);
	}
	
	public void enterTaskName(String taskName){
		_txtTaskName1.clear();
		_txtTaskName1.sendKeys(taskName);
	}
	
	public void enterDeadLine(String deadLine){
		_txtDeadLine.clear();
		_txtDeadLine.sendKeys(deadLine);
	}
	
	public void selectBillingType(String billingType){
		Select _objBillingType = new Select(_ddBillingType);
		_objBillingType.selectByVisibleText(billingType);
	}
	
	public void checkAddToMyTimeTrack(){
		if(!_cbAddToMyTimeTrack.isSelected()){
			_cbAddToMyTimeTrack.click();
		}
	}
	
	public void createTask(){
		_btnCreateTask.click();
	}
	
	public void waitForPageLoad(){
		WebDriverExtensions.waitforElementByXpath(webDriver, "//td[@class='pagetitle' and contains(text(),'Create New Task')]");
	}
}
