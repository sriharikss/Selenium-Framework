package testData;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import driverSupport.BaseClass;
import driverSupport.Config;
import jxl.read.biff.BiffException;

public class DataProviderClass {

	@DataProvider(name = "createProject")
	public static Object[][] getDataForCreatingNewProject() throws BiffException, IOException {

		String[][] testData = BaseClass.getExcelData(Config.testDataSheet, "createNewProject");
		return testData;
//		return new Object[][] {
//
//				{ "Boston Chocolate", "Project1",
//						"Description" },
//				{ "Media Agency", "Project11",
//						"Description1" },
//				{ "Our Company", "Project11",
//						"Description1" } 
//				};
	}

	@DataProvider(name = "createANewTask")
	public static Object[][] getDataForCreatingNewTask() {

		return new Object[][] {

				{ Config.userName, Config.password, "Architects Bureau", "One-page web site",
						"Requirements Gathering", "Jan 01, 2015", "design" },
				{ Config.userName, Config.password, "Architects Bureau", "One-page web site",
						"Develop Application", "Jan 31, 2015", "programming" },
				{ Config.userName, Config.password, "Architects Bureau", "One-page web site",
						"Test Application", "Jan 31, 2015", "testing" } };
	}

	@DataProvider(name = "editUser")
	public static Object[][] getDataForEditUser() {

		return new Object[][] {

				{ Config.userName, Config.password, "Barber, Robert (admin)", "Robert", "Barber",
						"robert.barber@gmail.com", "admin", "manager", "manager" } };
	}
}