package testScripts;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import driverSupport.BaseClass;
import driverSupport.TestReports;
import pageObjects.CreateNewProjectPage;
import pageObjects.IndexPage;
import pageObjects.LoginPage;
import pageObjects.OpenTasksPage;
import pageObjects.ProjectAndCustomersPage;
import testData.DataProviderClass;

@Listeners(TestReports.class)
public class CreateNewProject extends BaseClass{
	private LoginPage _loginPage;
	private IndexPage _indexPage;
	private OpenTasksPage _openTasksPage;
	private ProjectAndCustomersPage _projectAndCustomerPage;
	private CreateNewProjectPage _createNewProjectPage;

	@Test(groups = {"firstTest"},dataProvider="createProject",dataProviderClass=DataProviderClass.class)
	public void createNewProject(String userName, String password,
			String customerName, String projectName, String description) {

		_loginPage = new LoginPage(driver);
		Reporter.log("Login page is displayed \n",0);
		_indexPage = _loginPage.loginWithValidCredentials(userName, password);
		Reporter.log("Logged in using the credentials username:"+userName+" password:"+password+"\n", 1);
		_openTasksPage = _indexPage.navigateToTasksPage();
		Reporter.log("Navigated Tasks page\n",2);
		_projectAndCustomerPage = _openTasksPage.navigateToProjectAndCustomersPage();
		_createNewProjectPage = _projectAndCustomerPage.navigateToCreateProject();
		_createNewProjectPage.createProject(customerName, projectName, description);
		_loginPage = _indexPage.logout();
	}	
}
