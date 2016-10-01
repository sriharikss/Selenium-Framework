package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;

import driverSupport.WebDriverExtensions;

public class EditUserPage{
	
	private String pageHeader = "Edit User";
	private WebDriver webDriver;
	
	@FindBy(how = How.XPATH, using ="//div[contains(@id,'timeZoneSelector')]/table/tbody/tr/td[2]") 
	private WebElement _ddTimeZone;
	
	@FindBy(how = How.ID, using ="userDataLightBox_titlePlaceholder") 
	private WebElement _lblPageHeader;
	
	@FindBy(how = How.XPATH, using ="//table[@class='wdEditor']/tbody/tr" ) 
	private WebElement _rowsworkDuration;
	
	@FindBy(how = How.ID, using ="userDataLightBox_firstNameField" ) 
	private WebElement _txtFirstName;
	
	@FindBy(how = How.ID, using ="userDataLightBox_lastNameField" ) 
	private WebElement _txtLastName;
	
	@FindBy(how = How.ID, using ="userDataLightBox_emailField" ) 
	private WebElement _txtEmailId;
	
	@FindBy(how = How.ID, using ="userDataLightBox_usernameField" ) 
	private WebElement _txtUsername;
	
	@FindBy(how = How.ID, using ="userDataLightBox_passwordField" ) 
	private WebElement _txtPassword;
	
	@FindBy(how = How.ID, using ="userDataLightBox_passwordCopyField" ) 
	private WebElement _txtReTypePassword;
	
	@FindBy(how = How.ID, using ="userDataLightBox_submitTimeTrackChBox" ) 
	private WebElement _chkEnterTimeTrack;
	
	@FindBy(how = How.ID, using ="userDataLightBox_commitBtn" ) 
	private WebElement _btnSave;

	public EditUserPage(WebDriver driver){
		webDriver = driver;
		PageFactory.initElements(driver, this);
		AssertJUnit.assertTrue("This is not Edit User page", _lblPageHeader.getText().trim().contains(pageHeader));
	}
	
	public void enterWorkDuration(String[] workHours) {		

		List<WebElement> cellData = _rowsworkDuration.findElements(By
				.xpath("//td"));
		for (WebElement _txtHours : cellData) {
			int i = 0;
			_txtHours.sendKeys(workHours[i]);
			i++;
		}
	}

	public UserListPage editContactDetails(String firstName, String lastName,
			String emailId, String userName, String password,
			String retypePassword) {
		_txtFirstName.clear();
		_txtFirstName.sendKeys(firstName);

		_txtLastName.clear();
		_txtLastName.sendKeys(lastName);

		_txtEmailId.clear();
		_txtEmailId.sendKeys(emailId);

		_txtUsername.clear();
		_txtUsername.sendKeys(userName);

		_txtPassword.clear();
		_txtPassword.sendKeys(password);

		_txtReTypePassword.clear();
		_txtReTypePassword.sendKeys(retypePassword);
		
		saveEditUserPage();
		return new UserListPage(webDriver);
	}

	public void selectTimeTrack() {

		if (!_chkEnterTimeTrack.isSelected()) {
			_chkEnterTimeTrack.click();
		}
	}
	
	public void saveEditUserPage(){
		_btnSave.click();
	}
	
	public void waitForPageLoad(){
		WebDriverExtensions.waitforElementById(webDriver, "userDataLightBox_titlePlaceholder");
	}
}
