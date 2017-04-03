package testScripts;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import driverSupport.BaseClass;
import driverSupport.Config;
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
	public void createNewProject(String customerName, String projectName, String description) {

		_loginPage = new LoginPage(driver);
		_indexPage = _loginPage.loginWithValidCredentials(Config.userName, Config.password);
		_openTasksPage = _indexPage.navigateToTasksPage();
		_projectAndCustomerPage = _openTasksPage.navigateToProjectAndCustomersPage();
		_createNewProjectPage = _projectAndCustomerPage.navigateToCreateProject();
		_indexPage = _createNewProjectPage.createProject(customerName, projectName, description);
		_loginPage = _indexPage.logout();
	}	
}