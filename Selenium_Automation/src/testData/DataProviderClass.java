package testData;

import org.testng.annotations.DataProvider;

import driverSupport.Config;

public class DataProviderClass {

	@DataProvider(name = "createProject")
	public static Object[][] getDataForCreatingNewProject() {

		return new Object[][] {

				{ Config.userName, Config.password, "Boston Chocolate", "Project1",
						"Description" },
				{ Config.userName, Config.password, "Media Agency", "Project11",
						"Description1" },
				{ Config.userName, Config.password, "Our Company", "Project11",
						"Description1" } };
	}

	@DataProvider(name = "createANewTask")
	public static Object[][] getDataForCreatingNewTask() {

		return new Object[][] {

				{ "admin", "manager", "Architects Bureau", "One-page web site",
						"Requirements Gathering", "Jan 01, 2015", "design" },
				{ "admin", "manager", "Architects Bureau", "One-page web site",
						"Develop Application", "Jan 31, 2015", "programming" },
				{ "admin", "manager", "Architects Bureau", "One-page web site",
						"Test Application", "Jan 31, 2015", "testing" } };
	}

	@DataProvider(name = "editUser")
	public static Object[][] getDataForEditUser() {

		return new Object[][] {

				{ "admin", "manager", "Barber, Robert (admin)", "Robert", "Barber",
						"robert.barber@gmail.com", "admin", "manager", "manager" } };
	}
}