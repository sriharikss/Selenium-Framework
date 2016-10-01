package testScripts;

import org.testng.annotations.Test;

import java.io.IOException;

import org.testng.annotations.Listeners;

import driverSupport.BaseClass;
import driverSupport.TestReports;
import pageObjects.CreateNewTasksPage;
import pageObjects.IndexPage;
import pageObjects.LoginPage;
import pageObjects.OpenTasksPage;
import testData.DataProviderClass;

@Listeners(TestReports.class)
public class CreateNewTask extends BaseClass{
	private LoginPage _loginPage;
	private IndexPage _indexPage;
	private CreateNewTasksPage _createNewTasksPage;
	private OpenTasksPage _openTasksPage;

	@Test(dataProvider = "createANewTask", dataProviderClass = DataProviderClass.class)
	public void createANewTask(String userName, String password,
			String customer, String project, String taskName, String deadLine,
			String billingType) throws IOException {

		_loginPage = new LoginPage(driver);
		_indexPage = _loginPage.loginWithValidCredentials(userName, password);
		_openTasksPage = _indexPage.navigateToTasksPage();
		_createNewTasksPage = _openTasksPage.naviagteToCreateTasksPage();
		_openTasksPage = _createNewTasksPage.createNewTask(customer, project, taskName,
				deadLine, billingType);
		_loginPage = _indexPage.logout();
	}

}