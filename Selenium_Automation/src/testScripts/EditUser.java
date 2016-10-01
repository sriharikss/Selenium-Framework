package testScripts;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

import pageObjects.EditUserPage;
import pageObjects.IndexPage;
import pageObjects.LoginPage;
import pageObjects.UserListPage;
import testData.DataProviderClass;
import driverSupport.BaseClass;
import driverSupport.TestReports;

@Listeners(TestReports.class)
public class EditUser extends BaseClass{

	private LoginPage _loginPage;
	private IndexPage _indexPage;
	private UserListPage _userListPage;
	private EditUserPage _editUserPage;
	
	@Test(dataProvider = "editUser", dataProviderClass = DataProviderClass.class)
	public void editUser(String userName, String password, String nameInUsersTable, String firstName,
			String lastName, String emailId, String projuserName,
			String projUserpassword, String retypePassword) {

		_loginPage = new LoginPage(driver);
		_indexPage = _loginPage.loginWithValidCredentials(userName, password);
		_userListPage = _indexPage.naviagteToUsersPage();
		_editUserPage = _userListPage.navigateToEditUserPage(nameInUsersTable);
		_userListPage = _editUserPage.editContactDetails(firstName, lastName, emailId,
				projuserName, projUserpassword, retypePassword);
		_loginPage = _indexPage.logout();
	}		
}