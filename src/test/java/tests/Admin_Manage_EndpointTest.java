package tests;

import java.util.Random;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.Admin_AlertsPage;
import pages.Admin_Manage_EndpointPage;
import pages.FilterDialogPage;
import pages.LoginPage;
import utility.DriverTestCase;
import utility.ExcelUtils;
import utility.ExecutionLog;
import utility.PropertyReader;
import utility.QAAnnotations.TestCaseInfo;

public class Admin_Manage_EndpointTest extends DriverTestCase {
	private static Admin_AlertsPage admin_AlertsPage;
	private static LoginPage loginPage;
	private static ExcelUtils sheet;
	private static FilterDialogPage filterDialogPage;
	private static Admin_Manage_EndpointPage admin_Manage_EndpointPage;

	PropertyReader propertyReader = new PropertyReader();
	String userName = propertyReader.readProperty("username");
	String password = propertyReader.readProperty("password");

	Random random = new Random();
	int randNo = random.nextInt(100000);

	@BeforeMethod(alwaysRun = true)
	public void initForAppLog() throws Exception {
		setup();
		loginPage = new LoginPage(getWebDriver());
		sheet = new ExcelUtils();
		filterDialogPage = new FilterDialogPage(getWebDriver());
		admin_Manage_EndpointPage = new Admin_Manage_EndpointPage(getWebDriver());
		admin_AlertsPage = new Admin_AlertsPage(getWebDriver());
		loginPage.loginIntoApplication(userName, password);
		admin_Manage_EndpointPage.selectAdminEndpointMenu();
	}

	@TestCaseInfo(testCaseID = "625", title = "Verify user is able to naviagte to Endpoint page")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserAbleToNaviagateEnpointPage() throws Exception {

		try {

			admin_Manage_EndpointPage.selectAdminEndpointMenu();

		} catch (Error e) {
			getScreenshot("testUserAbleToNaviagateEnpointPage");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testUserAbleToNaviagateEnpointPage");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				admin_Manage_EndpointPage.clikOnRefreshButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "626", title = "Verify all three button 'filter' 'Export, 'Refresh' is present on top left side corner")
	@Test(priority = 2, groups = { "Regression" })
	public void testAllButtonFilterExportRefreshArePresentonTopLeftCornerOfEndpointPage() throws Exception {

		try {

			admin_Manage_EndpointPage.selectAdminEndpointMenu();
			admin_Manage_EndpointPage.getButtonNameOnPage();

		} catch (Error e) {
			getScreenshot("testAllButtonFilterExportRefreshArePresentonTopLeftCornerOfEndpointPage");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testAllButtonFilterExportRefreshArePresentonTopLeftCornerOfEndpointPage");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				admin_Manage_EndpointPage.clikOnRefreshButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "628", title = "Verify that all the columns 'Machine Name', 'Alias', 'Public IP', 'Local IP', 'Version', 'Last Connection' and 'Actions' are displays correctly in same order")
	@Test(priority = 2, groups = { "Regression" })
	public void testAllColumnHeaderNameShouldBeInDisplayInSameorderOfEndpointPage() throws Exception {
		try {

			admin_Manage_EndpointPage.selectAdminEndpointMenu();
//			admin_Manage_EndpointPage.verifyColumnHeaderName(headerName);
		} catch (Error e) {
			getScreenshot("testAllColumnHeaderNameShouldBeInDisplayInSameorderOfEndpointPage");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testAllColumnHeaderNameShouldBeInDisplayInSameorderOfEndpointPage");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				admin_Manage_EndpointPage.clikOnRefreshButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "629", title = "Verify that user is able to sort computer list by clicking on all column headers")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserAbleToSortMachineListByClickingOnColumnHeaderName() throws Exception {
		String headerName = "Alias";
		try {

			admin_Manage_EndpointPage.selectAdminEndpointMenu();
			admin_Manage_EndpointPage.clickonHeaderNameToSortList(headerName);

		} catch (Error e) {
			getScreenshot("testUserAbleToSortMachineListByClickingOnColumnHeaderName");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testUserAbleToSortMachineListByClickingOnColumnHeaderName");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				admin_Manage_EndpointPage.clikOnRefreshButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "630", title = "Verify that user is able refresh page by  Clicking On Refesh Button")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserAbleToRefreshPageByClickingOnRefreshButton() throws Exception {
		try {

			admin_Manage_EndpointPage.selectAdminEndpointMenu();
			admin_Manage_EndpointPage.clikOnRefreshButton();

		} catch (Error e) {
			getScreenshot("testUserAbleToRefreshPageByClickingOnRefreshButton");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testUserAbleToRefreshPageByClickingOnRefreshButton");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				admin_Manage_EndpointPage.clikOnRefreshButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "627", title = "Verify that computers should be filtered by Machine Name with Contains condition")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserAbleToFilterByMachineNameWithContainCondition() throws Exception {

		try {
			String fieldName = "Machine Name";
			String optionName = "Contains";
			String keyWord = "T4H9A7M";

			admin_Manage_EndpointPage.selectAdminEndpointMenu();
			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, optionName);
			filterDialogPage.enterValueInInputField(fieldName, keyWord);
			filterDialogPage.clickOnOkButton();

		} catch (Error e) {
			getScreenshot("testUserAbleToFilterByMachineNameWithContainCondition");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testUserAbleToFilterByMachineNameWithContainCondition");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				filterDialogPage.clickOnFilterButton();
				filterDialogPage.clickOnClearButton();
				filterDialogPage.clickOnOkButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "632", title = "Verify that computers should be filtered by Machine Name with Equals condition")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserAbleToFilterByMachineNameWithEqualsCondition() throws Exception {

		try {
			String fieldName = "Machine Name";
			String optionName = "Equals";
			String keyWord = "WIN-IAU3NU9P049";

			admin_Manage_EndpointPage.selectAdminEndpointMenu();
			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, optionName);
			filterDialogPage.enterValueInInputField(fieldName, keyWord);
			filterDialogPage.clickOnOkButton();

		} catch (Error e) {
			getScreenshot("testUserAbleToFilterByMachineNameWithEqualsCondition");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testUserAbleToFilterByMachineNameWithEqualsCondition");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				filterDialogPage.clickOnFilterButton();
				filterDialogPage.clickOnClearButton();
				filterDialogPage.clickOnOkButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "634", title = "Verify that computers should be filtered by Alias Name with Contains condition")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserAbleToFilterByAliasNameWithContainsCondition() throws Exception {

		try {
			String fieldName = "Alias";
			String optionName = "Contains";
			String keyWord = "bc";

			admin_Manage_EndpointPage.selectAdminEndpointMenu();
			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, optionName);
			filterDialogPage.enterValueInInputField(fieldName, keyWord);
			filterDialogPage.clickOnOkButton();

		} catch (Error e) {
			getScreenshot("testUserAbleToFilterByAliasNameWithContainsCondition");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testUserAbleToFilterByAliasNameWithContainsCondition");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				filterDialogPage.clickOnFilterButton();
				filterDialogPage.clickOnClearButton();
				filterDialogPage.clickOnOkButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "635", title = "Verify that computers should be filtered by Alias Name with Equal condition")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserAbleToFilterByAliasNameWithEqualCondition() throws Exception {

		try {
			String fieldName = "Alias";
			String optionName = "Equals";
			String keyWord = "abc";

			admin_Manage_EndpointPage.selectAdminEndpointMenu();
			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, optionName);
			filterDialogPage.enterValueInInputField(fieldName, keyWord);
			filterDialogPage.clickOnOkButton();

		} catch (Error e) {
			getScreenshot("testUserAbleToFilterByAliasNameWithEqualCondition");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testUserAbleToFilterByAliasNameWithEqualCondition");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				filterDialogPage.clickOnFilterButton();
				filterDialogPage.clickOnClearButton();
				filterDialogPage.clickOnOkButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "636", title = "Verify that computers should be filtered by Alias Name with Start With condition")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserAbleToFilterByAliasNameWithStartWithCondition() throws Exception {

		try {
			String fieldName = "Alias";
			String optionName = "Start With";
			String keyWord = "ab";

			admin_Manage_EndpointPage.selectAdminEndpointMenu();
			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, optionName);
			filterDialogPage.enterValueInInputField(fieldName, keyWord);
			filterDialogPage.clickOnOkButton();

		} catch (Error e) {
			getScreenshot("testUserAbleToFilterByAliasNameWithStartWithCondition");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testUserAbleToFilterByAliasNameWithStartWithCondition");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				filterDialogPage.clickOnFilterButton();
				filterDialogPage.clickOnClearButton();
				filterDialogPage.clickOnOkButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "631", title = "Verify that computers should be filtered by Local Ip with Contains condition")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserAbleToFilterByLOcalIPWithContainsCondition() throws Exception {

		try {
			String fieldName = "Local IP";
			String optionName = "Contains";
			String keyWord = "168";

			admin_Manage_EndpointPage.selectAdminEndpointMenu();
			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, optionName);
			filterDialogPage.enterValueInInputField(fieldName, keyWord);
			filterDialogPage.clickOnOkButton();

		} catch (Error e) {
			getScreenshot("testUserAbleToFilterByLOcalIPWithContainsCondition");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testUserAbleToFilterByLOcalIPWithContainsCondition");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				filterDialogPage.clickOnFilterButton();
				filterDialogPage.clickOnClearButton();
				filterDialogPage.clickOnOkButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "639", title = "Verify that computers should be filtered by Local Ip with Equals condition")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserAbleToFilterByLOcalIPWithEqualsCondition() throws Exception {

		try {
			String fieldName = "Local IP";
			String optionName = "Equals";
			String keyWord = "192.168.0.5";

			admin_Manage_EndpointPage.selectAdminEndpointMenu();
			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, optionName);
			filterDialogPage.enterValueInInputField(fieldName, keyWord);
			filterDialogPage.clickOnOkButton();

		} catch (Error e) {
			getScreenshot("testUserAbleToFilterByLOcalIPWithEqualsCondition");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testUserAbleToFilterByLOcalIPWithEqualsCondition");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				filterDialogPage.clickOnFilterButton();
				filterDialogPage.clickOnClearButton();
				filterDialogPage.clickOnOkButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "640", title = "Verify that computers should be filtered by Local Ip with Start with condition")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserAbleToFilterByLOcalIPWithStartWithCondition() throws Exception {

		try {
			String fieldName = "Local IP";
			String optionName = "Start With";
			String keyWord = "192.168";

			admin_Manage_EndpointPage.selectAdminEndpointMenu();
			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, optionName);
			filterDialogPage.enterValueInInputField(fieldName, keyWord);
			filterDialogPage.clickOnOkButton();

		} catch (Error e) {
			getScreenshot("testUserAbleToFilterByLOcalIPWithEqualsCondition");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testUserAbleToFilterByLOcalIPWithEqualsCondition");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				filterDialogPage.clickOnFilterButton();
				filterDialogPage.clickOnClearButton();
				filterDialogPage.clickOnOkButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "641", title = "Verify that computers should be filtered by Public IP with Contains condition")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserAbleToFilterByPublicIPWithContainsCondition() throws Exception {

		try {
			String fieldName = "Public IP";
			String optionName = "Contains";
			String keyWord = "191";

			admin_Manage_EndpointPage.selectAdminEndpointMenu();
			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, optionName);
			filterDialogPage.enterValueInInputField(fieldName, keyWord);
			filterDialogPage.clickOnOkButton();

		} catch (Error e) {
			getScreenshot("testUserAbleToFilterByPublicIPWithContainsCondition");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testUserAbleToFilterByPublicIPWithContainsCondition");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				filterDialogPage.clickOnFilterButton();
				filterDialogPage.clickOnClearButton();
				filterDialogPage.clickOnOkButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "642", title = "Verify that computers should be filtered by Public IP with Equals condition")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserAbleToFilterByPublicIPWithEqualsCondition() throws Exception {

		try {
			String fieldName = "Public IP";
			String optionName = "Equals";
			String keyWord = "103.225.191.105";

			admin_Manage_EndpointPage.selectAdminEndpointMenu();
			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, optionName);
			filterDialogPage.enterValueInInputField(fieldName, keyWord);
			filterDialogPage.clickOnOkButton();

		} catch (Error e) {
			getScreenshot("testUserAbleToFilterByPublicIPWithEqualsCondition");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testUserAbleToFilterByPublicIPWithEqualsCondition");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				filterDialogPage.clickOnFilterButton();
				filterDialogPage.clickOnClearButton();
				filterDialogPage.clickOnOkButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "643", title = "Verify that computers should be filtered by Public IP with Start With condition")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserAbleToFilterByPublicIPWithStartWithCondition() throws Exception {

		try {
			String fieldName = "Public IP";
			String optionName = "Start With";
			String keyWord = "103";

			admin_Manage_EndpointPage.selectAdminEndpointMenu();
			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, optionName);
			filterDialogPage.enterValueInInputField(fieldName, keyWord);
			filterDialogPage.clickOnOkButton();

		} catch (Error e) {
			getScreenshot("testUserAbleToFilterByPublicIPWithStartWithCondition");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testUserAbleToFilterByPublicIPWithStartWithCondition");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				filterDialogPage.clickOnFilterButton();
				filterDialogPage.clickOnClearButton();
				filterDialogPage.clickOnOkButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "637", title = "Verify on clicking on Cancel button - Filter popup should closed without filter")
	@Test(priority = 2, groups = { "Regression" })
	public void testWhenUserClickingOnCancelButtonFilterPouupClosedWithoutFilter() throws Exception {

		try {
			String fieldName = "Public IP";
			String optionName = "Start With";
			String keyWord = "103";

			admin_Manage_EndpointPage.selectAdminEndpointMenu();
			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, optionName);
			filterDialogPage.enterValueInInputField(fieldName, keyWord);
			filterDialogPage.clickOnCancelButton();

		} catch (Error e) {
			getScreenshot("testWhenUserClickingOnCancelButtonFilterPouupClosedWithoutFilter");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testWhenUserClickingOnCancelButtonFilterPouupClosedWithoutFilter");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				admin_Manage_EndpointPage.clikOnRefreshButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "638", title = "Verify on clicking on Clear button - Filter value reset to default value")
	@Test(priority = 2, groups = { "Regression" })
	public void testWhenUserClickingOnClearButtonFilterValueResetToDefaultValue() throws Exception {

		try {
			String fieldName = "Alias";
			String optionName = "Start With";
			String keyWord = "ab";

			admin_Manage_EndpointPage.selectAdminEndpointMenu();
			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, optionName);
			filterDialogPage.enterValueInInputField(fieldName, keyWord);
			filterDialogPage.clickOnClearButton();
			filterDialogPage.clickOnOkButton();

		} catch (Error e) {
			getScreenshot("testWhenUserClickingOnClearButtonFilterValueResetToDefaultValue");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testWhenUserClickingOnClearButtonFilterValueResetToDefaultValue");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				admin_Manage_EndpointPage.clikOnRefreshButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() throws Exception {
		destroyBrowser();
	}

}
