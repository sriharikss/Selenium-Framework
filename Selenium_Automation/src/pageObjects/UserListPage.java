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

public class UserListPage{

	private String pageHeader = "User List";
	private WebDriver webDriver;

	@FindBy(how = How.CLASS_NAME, using = "textTitle")
	private WebElement _lblpageHeader;

	@FindBy(how = How.XPATH, using = "//table[@class='userListTable canEditUsers hidePTO sortByName']/tbody/tr")
	private List<WebElement> _allRowsInTable;

	public UserListPage(WebDriver driver) {
		waitforPageLoad();
		PageFactory.initElements(driver, this);
		AssertJUnit.assertTrue("This is not UserList Page", driver.getTitle()
				.trim().contains(pageHeader));
	}

	public WebElement findUserInTable(WebDriver driver, String name) {
		WebElement element = null;
		if (_allRowsInTable.size() > 0) {
			for (WebElement eachRow : _allRowsInTable) {
				List<WebElement> cellData = eachRow.findElements(By
						.xpath("//td[@class='userNameCell first']"));
				for (WebElement elm : cellData) {
					if (elm.getText().equalsIgnoreCase(name))
						element = elm;
						return element;
				}
			}
		}
		return element;
	}

	public EditUserPage navigateToEditUserPage(String userName) {
		findUserInTable(webDriver, userName).click();
		return new EditUserPage(webDriver);
	}

	public void waitforPageLoad() {
		WebDriverExtensions
				.waitforElementByXpath(webDriver,
						"//table[@class='userListTable canEditUsers hidePTO sortByName']/tbody/tr");
	}

}
