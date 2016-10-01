package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import driverSupport.WebDriverExtensions;

public class IndexPage{
	
	private WebDriver webDriver;

	@FindBy(how = How.XPATH, using ="//a[contains(@href,'/tasks')]" ) 
	private WebElement _lnkTasks;
	
	@FindBy(how = How.ID, using ="logoutLink" ) 
	private WebElement _lnkTasksLogout;
	
	@FindBy(how = How.XPATH, using ="//a[contains(@href,'/user/submit')]" ) 
	private WebElement _lnkTimeTrack;
	
	@FindBy(how = How.XPATH, using ="//a[contains(@href,'/reports')]" ) 
	private WebElement _lnkReports;
	
	@FindBy(how = How.XPATH, using ="//a[contains(@href,'/administration/userlist')]" ) 
	private WebElement _lnkUsers;
	
	@FindBy(how = How.XPATH, using ="//a[contains(@href,'/administration/workingdays')]" ) 
	private WebElement _lnkWorkSchedule;
	

	public  IndexPage(WebDriver driver){
		webDriver = driver;
		waitForPageLoad();
		PageFactory.initElements(driver, this);		
	}
	
	public OpenTasksPage navigateToTasksPage() {
		_lnkTasks.click();
		return new OpenTasksPage(webDriver);
		}

	public UserListPage naviagteToUsersPage() {
		_lnkUsers.click();
		return new UserListPage(webDriver);
	}
	
	public LoginPage logout(){
		_lnkTasksLogout.click();
		return new LoginPage(webDriver);
	}
	
	public void waitForPageLoad(){
		WebDriverExtensions.waitforElementByXpath(webDriver, "//table[@id='topnav']/tbody");
	}

}
