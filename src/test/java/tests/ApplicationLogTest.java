package tests;

import java.util.Random;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.Admin_AlertsPage;
import pages.Admin_ApplicationLogPage;
import pages.Admin_Endpoint_SettingPage;
import pages.Admin_UsersPage;
import pages.FilterDialogPage;
import pages.LoginPage;
import utility.DriverTestCase;
import utility.ExcelUtils;
import utility.ExecutionLog;
import utility.PropertyReader;
import utility.QAAnnotations.TestCaseInfo;

public class ApplicationLogTest extends DriverTestCase {

	private static Admin_ApplicationLogPage admin_applicationLogPage;
	private static Admin_AlertsPage admin_alertsPage;
	private static LoginPage loginPage;
	private static ExcelUtils sheet;
	private static FilterDialogPage filterDialogPage;
	private static Admin_UsersPage admin_UsersPage;
	private static Admin_Endpoint_SettingPage admin_Endpoint_SettingPage;

	PropertyReader propertyReader = new PropertyReader();
	String userName = propertyReader.readProperty("username");
	String password = propertyReader.readProperty("password");

	Random random = new Random();
	int randNo = random.nextInt(100000);

	@BeforeMethod(alwaysRun = true)
	public void initForAppLog() throws Exception {
		setup();
		loginPage = new LoginPage(getWebDriver());
		admin_applicationLogPage = new Admin_ApplicationLogPage(getWebDriver());
		admin_alertsPage = new Admin_AlertsPage(getWebDriver());
		sheet = new ExcelUtils();
		filterDialogPage = new FilterDialogPage(getWebDriver());
		admin_UsersPage = new Admin_UsersPage(getWebDriver());
		admin_Endpoint_SettingPage = new Admin_Endpoint_SettingPage(getWebDriver());

		loginPage.loginIntoApplication(userName, password);

	}

	@TestCaseInfo(testCaseID = "163", title = "Verify application log is created with object type as 'Alert' and action as 'Created'")
	@Test(priority = 2, groups = { "SmokeTest" })
	public void testApplicationLogIsCreatedForAlertCreated() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 1, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 1, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 1, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Users") + randNo + ".com";

		String[] alertDetail = { alertType, "", alertSeverity, "Edit", "Delete" };

		try {

			String[] logDetail = { "Alert", alertName, "Created", "nakhter@qa.local", "" };

			admin_alertsPage.selectAdminMenu();
			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);
			admin_applicationLogPage.selectAppLogMenu();
			admin_applicationLogPage.verifyLogDetailsOnApplicationLogPage(alertName, logDetail);

		} catch (Error e) {
			captureScreenshot("testApplicationLogIsCreatedForAlertCreated");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testApplicationLogIsCreatedForAlertCreated");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				admin_alertsPage.selectAdminMenu();
				admin_alertsPage.deleteExistingAlert(alertName);

			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "164", title = "Verify application log is created with object type as 'Alert' and action as 'Modified' ")
	@Test(priority = 2, groups = { "Functional" })
	public void testApplicationLogIsCreatedForAlertModified() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 1, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 1, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 1, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Users") + randNo + ".com";
		String alertModifiedName = sheet.getSingleCellData("AlertName", 1, "Admin_Alert") + randNo + "New";

		try {

			String[] logDetail = { "Alert", alertModifiedName, "Modified", "nakhter@qa.local", "" };

			admin_alertsPage.selectAdminMenu();
			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.clickOnSaveButton();

			admin_alertsPage.editExistingAlertName(alertName, alertModifiedName);

			admin_applicationLogPage.selectAppLogMenu();
			admin_applicationLogPage.verifyLogDetailsOnApplicationLogPage(alertModifiedName, logDetail);

		} catch (Error e) {
			captureScreenshot("testApplicationLogIsCreatedForAlertModified");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testApplicationLogIsCreatedForAlertModified");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				admin_alertsPage.selectAdminMenu();
				admin_alertsPage.deleteExistingAlert(alertModifiedName);

			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "162", title = "Verify application log is created with object type as 'Alert' and action as 'Deleted' ")
	@Test(priority = 2, groups = { "Functional" })
	public void testApplicationLogIsCreatedForAlertDeleted() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 1, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 1, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 1, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Users") + randNo + ".com";

		try {

			String[] logDetail = { "Alert", alertName, "Deleted", "nakhter@qa.local", "" };

			admin_alertsPage.selectAdminMenu();
			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.clickOnSaveButton();

			admin_alertsPage.deleteExistingAlert(alertName);

			admin_applicationLogPage.selectAppLogMenu();
			admin_applicationLogPage.verifyLogDetailsOnApplicationLogPage(alertName, logDetail);

		} catch (Error e) {
			captureScreenshot("testApplicationLogIsCreatedForAlertDeleted");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testApplicationLogIsCreatedForAlertDeleted");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		}
	}

	@TestCaseInfo(testCaseID = "160", title = "Verify 'Log In' action log is generated when user logged in to the application. ")
	@Test(priority = 2, groups = { "Functional" })
	public void testApplicationLogIsCreatedForUserLogin() throws Exception {

		try {

			String[] logDetail = { "User", userName, "Log In", userName, "" };

			admin_applicationLogPage.selectAppLogMenu();
			admin_applicationLogPage.verifyLogDetailsOnApplicationLogPage(userName, logDetail);

		} catch (Error e) {
			captureScreenshot("testApplicationLogIsCreatedForUserLogin");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testApplicationLogIsCreatedForUserLogin");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		}
	}

	@TestCaseInfo(testCaseID = "161", title = "Verify 'Log In Failed' action log is generated when user try to log in with invalid credentials ")
	@Test(priority = 2, groups = { "Functional" })
	public void testApplicationLogIsCreatedForUserLoginFailed() throws Exception {

		try {

			String invalidUserName = "manees@qa.local";
			String invalidPassword = "abc1";

			String[] logDetail = { "User", invalidUserName, "Log In Failed", invalidUserName, "40.89.166.214" };

			loginPage.logoutUserFromApplication();

			loginPage.loginIntoApplication(invalidUserName, invalidPassword);

			loginPage.loginIntoApplication(userName, password);

			admin_applicationLogPage.selectAppLogMenu();
			admin_applicationLogPage.verifyLogDetailsOnApplicationLogPage(invalidUserName, logDetail);

		} catch (Error e) {
			captureScreenshot("testApplicationLogIsCreatedForUserLoginFailed");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testApplicationLogIsCreatedForUserLoginFailed");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		}
	}

	@TestCaseInfo(testCaseID = "165", title = "Verify application log is created with Object type as 'User' and action as 'Created'")
	@Test(priority = 2, groups = { "Regression" })
	public void testApplicationLogIsCreatedForUserCreated() throws Exception {

		String username = sheet.getSingleCellData("Name", 1, "Admin_Users") + randNo;
		String userEmail = sheet.getSingleCellData("Email", 1, "Admin_Users") + randNo + ".com";
		String userRole = sheet.getSingleCellData("Role", 1, "Admin_Users");
		String passwordSettingOption = sheet.getSingleCellData("PasswordOption", 1, "Admin_Users");
		try {

			String[] logDetail = { "User", userEmail, "Created", userName, "" };

			admin_UsersPage.selectAdminUsersMenu();
			admin_UsersPage.createAUser(username, userEmail, userRole, passwordSettingOption);

			admin_applicationLogPage.selectAppLogMenu();
			admin_applicationLogPage.verifyLogDetailsOnApplicationLogPage(userEmail, logDetail);

		} catch (Error e) {
			captureScreenshot("testApplicationLogIsCreatedForUserCreated");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testApplicationLogIsCreatedForUserCreated");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				admin_UsersPage.selectAdminUsersMenu();
				admin_UsersPage.deleteExistingUser(username);

			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "166", title = "Verify application log is created with Object type as 'User' and action as 'Modified'")
	@Test(priority = 2, groups = { "Functional" })
	public void testApplicationLogIsCreatedForUserModified() throws Exception {

		String username = sheet.getSingleCellData("Name", 1, "Admin_Users") + randNo;
		String userEmail = sheet.getSingleCellData("Email", 1, "Admin_Users") + randNo + ".com";
		String userRole = sheet.getSingleCellData("Role", 1, "Admin_Users");
		String passwordSettingOption = sheet.getSingleCellData("PasswordOption", 1, "Admin_Users");
		String UserModifiedName = username + "New";
		String modifedRole = sheet.getSingleCellData("Role", 2, "Admin_Users");
		try {
			String[] logDetail = { "User", userEmail, "Modified", userName, "" };

			admin_UsersPage.selectAdminUsersMenu();
			admin_UsersPage.createAUser(username, userEmail, userRole, passwordSettingOption);

			admin_UsersPage.editExistingUser(username, UserModifiedName, modifedRole);

			admin_applicationLogPage.selectAppLogMenu();
			admin_applicationLogPage.verifyLogDetailsOnApplicationLogPage(userEmail, logDetail);

		} catch (Error e) {
			captureScreenshot("testApplicationLogIsCreatedForUserModified");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testApplicationLogIsCreatedForUserModified");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				admin_UsersPage.selectAdminUsersMenu();
				admin_UsersPage.deleteExistingUser(UserModifiedName);

			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "167", title = "Verify application log is created with Object type as 'User' and action as 'Deleted'")
	@Test(priority = 2, groups = { "Functional" })
	public void testApplicationLogIsCreatedForUserDeleted() throws Exception {

		String username = sheet.getSingleCellData("Name", 1, "Admin_Users") + randNo;
		String userEmail = sheet.getSingleCellData("Email", 1, "Admin_Users") + randNo + ".com";
		String userRole = sheet.getSingleCellData("Role", 1, "Admin_Users");
		String passwordSettingOption = sheet.getSingleCellData("PasswordOption", 1, "Admin_Users");

		try {
			String[] logDetail = { "User", userEmail, "Deleted", userName, "" };

			admin_UsersPage.selectAdminUsersMenu();
			admin_UsersPage.createAUser(username, userEmail, userRole, passwordSettingOption);

			admin_UsersPage.deleteExistingUser(username);

			admin_applicationLogPage.selectAppLogMenu();
			admin_applicationLogPage.verifyLogDetailsOnApplicationLogPage(userEmail, logDetail);

		} catch (Error e) {
			captureScreenshot("testApplicationLogIsCreatedForUserDeleted");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testApplicationLogIsCreatedForUserDeleted");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		}
	}

	@TestCaseInfo(testCaseID = "168", title = "Verify application log is created with Object type as 'Print Job Settings' and action as 'Modified'")
	@Test(priority = 2, groups = { "Regression" })
	public void testApplicationLogIsCreatedForPrintJobModified() throws Exception {

		try {
			String[] logDetail = { "Print Job Settings", "qa", "Modified", userName, "" };

			admin_Endpoint_SettingPage.selectAdminEndpointsSettingMenu();
			admin_Endpoint_SettingPage.switchToTab("Print Jobs");
			admin_Endpoint_SettingPage.checkUncheckEnabledCheckBox(true);
			admin_Endpoint_SettingPage.clickOnSaveButton();

			admin_applicationLogPage.selectAppLogMenu();
			admin_applicationLogPage.verifyLogDetailsOnApplicationLogPage("qa", logDetail);

		} catch (Error e) {
			captureScreenshot("testApplicationLogIsCreatedForPrintJobModified");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testApplicationLogIsCreatedForPrintJobModified");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		}
	}

	@TestCaseInfo(testCaseID = "169", title = "Verify application log is created with Object type as 'File Audit Settings' and action as 'Modified'")
	@Test(priority = 2, groups = { "Functional" })
	public void testApplicationLogIsCreatedForFileAuditModified() throws Exception {

		try {
			String[] logDetail = { "File Audit Settings", "qa", "Modified", userName, "" };

			admin_Endpoint_SettingPage.selectAdminEndpointsSettingMenu();
			admin_Endpoint_SettingPage.switchToTab("File Audit");
			admin_Endpoint_SettingPage.checkUncheckEnabledCheckBox(true);
			admin_Endpoint_SettingPage.clickOnSaveButton();

			admin_applicationLogPage.selectAppLogMenu();
			admin_applicationLogPage.verifyLogDetailsOnApplicationLogPage("qa", logDetail);

		} catch (Error e) {
			captureScreenshot("testApplicationLogIsCreatedForFileAuditModified");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testApplicationLogIsCreatedForFileAuditModified");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		}
	}

	@TestCaseInfo(testCaseID = "170", title = "Verify When User filter Application log with Object Type as 'User' then only  User object type logs are getting displayed.")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserIsAbleToFilterApplicationLogWithObjectTypeUser() throws Exception {

		try {
			String fieldName = "Object Type";
			String objectType = "User";

			admin_applicationLogPage.selectAppLogMenu();
			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, objectType);
			filterDialogPage.clickOnOkButton();

			admin_applicationLogPage.assertValuesInColumns(fieldName, objectType);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterApplicationLogWithObjectTypeUser");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterApplicationLogWithObjectTypeUser");
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

	@TestCaseInfo(testCaseID = "171", title = "Verify When User filter Application log with Action as 'Modified' then only Modified action logs are getting displayed.")
	@Test(priority = 2, groups = { "Functional" })
	public void testUserIsAbleToFilterApplicationLogWithActionModified() throws Exception {

		try {
			String fieldName = "Action";
			String objectType = "Modified";

			admin_applicationLogPage.selectAppLogMenu();
			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, objectType);
			filterDialogPage.clickOnOkButton();

			admin_applicationLogPage.assertValuesInColumns(fieldName, objectType);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterApplicationLogWithActionModified");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterApplicationLogWithActionModified");
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

	@TestCaseInfo(testCaseID = "172", title = "Verify When User filter Application log with Object contains as '<keyword>' then only matching logs are getting displayed.")
	@Test(priority = 2, groups = { "Functional" })
	public void testUserIsAbleToFilterApplicationLogWithObjectContains() throws Exception {

		try {
			String fieldName = "Object";
			String condition = "Contains";
			String fieldValue = "nakhter";

			admin_applicationLogPage.selectAppLogMenu();
			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, condition);
			filterDialogPage.enterValueInInputField(fieldName, fieldValue);
			filterDialogPage.clickOnOkButton();

			admin_applicationLogPage.assertValuesInColumns(fieldName, fieldValue);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterApplicationLogWithObjectContains");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterApplicationLogWithObjectContains");
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

	@TestCaseInfo(testCaseID = "173", title = "Verify When User filter Application log with 'User' contains as '<keyword>' then only matching logs are getting displayed.")
	@Test(priority = 2, groups = { "Functional" })
	public void testUserIsAbleToFilterApplicationLogWithUserContains() throws Exception {

		try {
			String fieldName = "User";
			String condition = "Contains";
			String fieldValue = "nakhter";

			admin_applicationLogPage.selectAppLogMenu();
			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, condition);
			filterDialogPage.enterValueInInputField(fieldName, fieldValue);
			filterDialogPage.clickOnOkButton();

			admin_applicationLogPage.assertValuesInColumns(fieldName, fieldValue);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterApplicationLogWithUserContains");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterApplicationLogWithUserContains");
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

	@TestCaseInfo(testCaseID = "174", title = "Verify When User is able to Export and download in selected formats by Users.")
	@Test(priority = 2, groups = { "Functional" })
	public void testUserIsAbleExportAndDownloadPageEventsInSelectedFormatsByUser() throws Exception {

		try {
			String formatsName = "TIFF file";

			admin_applicationLogPage.selectAppLogMenu();

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown("Action", "Log In Failed");
			filterDialogPage.clickOnOkButton();

			admin_applicationLogPage.downloadExportedFileEventsInSelectedFormats(formatsName);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleExportAndDownloadPageEventsInSelectedFormatsByUser");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleExportAndDownloadPageEventsInSelectedFormatsByUser");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				admin_applicationLogPage.clickOnCloseButtonOnExportPopUp();

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
