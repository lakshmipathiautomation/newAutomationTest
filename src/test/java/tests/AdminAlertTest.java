package tests;

import static org.testng.Assert.assertTrue;

import java.util.Random;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.Admin_AlertsPage;
import pages.FilterDialogPage;
import pages.LoginPage;
import utility.DriverTestCase;
import utility.ExcelUtils;
import utility.ExecutionLog;
import utility.PropertyReader;
import utility.QAAnnotations.TestCaseInfo;

public class AdminAlertTest extends DriverTestCase {

	private static Admin_AlertsPage admin_alertsPage;
	private static LoginPage loginPage;
	private static ExcelUtils sheet;
	private static FilterDialogPage filterDialogPage;

	PropertyReader propertyReader = new PropertyReader();
	String userName = propertyReader.readProperty("username");
	String password = propertyReader.readProperty("password");

	Random random = new Random();
	int randNo = random.nextInt(100000);

	@BeforeMethod(alwaysRun = true)
	public void initForAlerts() throws Exception {
		setup();
		admin_alertsPage = new Admin_AlertsPage(getWebDriver());
		sheet = new ExcelUtils();
		loginPage = new LoginPage(getWebDriver());
		filterDialogPage = new FilterDialogPage(getWebDriver());

		loginPage.loginIntoApplication(userName, password);

		admin_alertsPage.selectAdminMenu();
	}

	@TestCaseInfo(testCaseID = "16", title = "Verify alert is created successfully and displayed on the alert page when severity as 'Info'")
	@Test(priority = 1, groups = { "SmokeTest" })
	public void testActivityLessthanXhoursInfoAlertCreated() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 1, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 1, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 1, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");

		String[] alertDetail = { alertType, "", alertSeverity, "Edit", "Delete" };

		try {

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testActivityLessthanXhoursInfoAlertCreated");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testActivityLessthanXhoursInfoAlertCreated");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "85", title = "Verify alert is created successfully and displayed on the alert page when severity as 'Warning'")
	@Test(priority = 1, groups = { "SmokeTest" })
	public void testActivityLessthanXhoursWarningAlertCreated() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 2, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 1, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 2, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");

		try {

			String[] alertDetail = { alertType, "", alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testActivityLessthanXhoursWarningAlertCreated");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testActivityLessthanXhoursWarningAlertCreated");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "86", title = "Verify alert is created successfully and displayed on the alert page when severity as 'Critical'")
	@Test(priority = 1, groups = { "SmokeTest" })
	public void testActivityLessthanXhoursCriticalAlertCreated() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 3, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 1, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 3, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");

		try {

			String[] alertDetail = { alertType, "", alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testActivityLessthanXhoursCriticalAlertCreated");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testActivityLessthanXhoursCriticalAlertCreated");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "87", title = "Verify alert is created successfully and displayed on the alert page when Operation as Deleted")
	@Test(priority = 2, groups = { "Regression" })
	public void testCreateAnAlertOfFileOperationOnAgentsForDeleteOperation() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 4, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 4, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 4, "Admin_Alert");
		String operation = sheet.getSingleCellData("Operation", 4, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");
		String path = sheet.getSingleCellData("Path", 4, "Admin_Alert");

		try {

			String[] alertDetail = { alertType, "", alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.enterFileFolder(path);
			admin_alertsPage.selectOperation(operation);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testCreateAnAlertOfFileOperationOnAgentsForDeleteOperation");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testCreateAnAlertOfFileOperationOnAgentsForDeleteOperation");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "88", title = "Verify alert is created successfully and displayed on the alert page Operation as Read.")
	@Test(priority = 2, groups = { "Regression" })
	public void testCreateAnAlertOfFileOperationOnAgentsForReadOperation() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 5, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 4, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 5, "Admin_Alert");
		String operation = sheet.getSingleCellData("Operation", 5, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");
		String path = sheet.getSingleCellData("Path", 4, "Admin_Alert");

		try {

			String[] alertDetail = { alertType, "", alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.enterFileFolder(path);
			admin_alertsPage.selectOperation(operation);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testCreateAnAlertOfFileOperationOnAgentsForReadOperation");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testCreateAnAlertOfFileOperationOnAgentsForReadOperation");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "89", title = "Verify alert is created successfully and displayed on the alert page Operation as Created.")
	@Test(priority = 2, groups = { "Regression" })
	public void testCreateAnAlertOfFileOperationOnAgentsForCreatedOperation() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 6, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 4, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 6, "Admin_Alert");
		String operation = sheet.getSingleCellData("Operation", 6, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");
		String path = sheet.getSingleCellData("Path", 4, "Admin_Alert");

		try {

			String[] alertDetail = { alertType, "", alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.enterFileFolder(path);
			admin_alertsPage.selectOperation(operation);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testCreateAnAlertOfFileOperationOnAgentsForCreatedOperation");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testCreateAnAlertOfFileOperationOnAgentsForCreatedOperation");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "90", title = "Verify alert is created successfully and displayed on the alert page Operation as Modified.")
	@Test(priority = 2, groups = { "Regression" })
	public void testCreateAnAlertOfFileOperationOnAgentsForModifiedOperation() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 7, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 4, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 7, "Admin_Alert");
		String operation = sheet.getSingleCellData("Operation", 7, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");
		String path = sheet.getSingleCellData("Path", 4, "Admin_Alert");

		try {

			String[] alertDetail = { alertType, "", alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.enterFileFolder(path);
			admin_alertsPage.selectOperation(operation);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testCreateAnAlertOfFileOperationOnAgentsForModifiedOperation");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testCreateAnAlertOfFileOperationOnAgentsForModifiedOperation");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "91", title = "Verify  alert is created successfully for Deleted Operation on File Operation on integrators when Integration is set as 'netappqa'  and displayed on the alert page")
	@Test(priority = 2, groups = { "Regression" })
	public void testCreateAnAlertOfNetAppQaFileOperationOnIntegratorsForDeletedOperation() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 8, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 8, "Admin_Alert");
		String integration = sheet.getSingleCellData("Integration", 8, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 8, "Admin_Alert");
		String operation = sheet.getSingleCellData("Operation", 8, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");
		String path = sheet.getSingleCellData("Path", 8, "Admin_Alert");

		try {

			String[] alertDetail = { alertType, integration, alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.selectIntegrationType(integration);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.enterFileFolder(path);
			admin_alertsPage.selectOperation(operation);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testCreateAnAlertOfNetAppQaFileOperationOnIntegratorsForDeletedOperation");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testCreateAnAlertOfNetAppQaFileOperationOnIntegratorsForDeletedOperation");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "92", title = "Verify  alert is created successfully for 'Read' Operation on File Operation on integrators when Integration is set as 'netappqa'  and displayed on the alert page")
	@Test(priority = 2, groups = { "Regression" })
	public void testCreateAnAlertOfNetAppQaFileOperationOnIntegratorsForReadOperation() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 9, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 8, "Admin_Alert");
		String integration = sheet.getSingleCellData("Integration", 8, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 9, "Admin_Alert");
		String operation = sheet.getSingleCellData("Operation", 9, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");
		String path = sheet.getSingleCellData("Path", 8, "Admin_Alert");

		try {

			String[] alertDetail = { alertType, integration, alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.selectIntegrationType(integration);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.enterFileFolder(path);
			admin_alertsPage.selectOperation(operation);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testCreateAnAlertOfNetAppQaFileOperationOnIntegratorsForReadOperation");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testCreateAnAlertOfNetAppQaFileOperationOnIntegratorsForReadOperation");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "93", title = "Verify  alert is created successfully for 'Created' Operation on File Operation on integrators when Integration is set as 'netappqa'  and displayed on the alert page")
	@Test(priority = 2, groups = { "Regression" })
	public void testCreateAnAlertOfNetAppQaFileOperationOnIntegratorsForCreatedOperation() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 10, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 8, "Admin_Alert");
		String integration = sheet.getSingleCellData("Integration", 8, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 10, "Admin_Alert");
		String operation = sheet.getSingleCellData("Operation", 10, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");
		String path = sheet.getSingleCellData("Path", 8, "Admin_Alert");

		try {

			String[] alertDetail = { alertType, integration, alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.selectIntegrationType(integration);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.enterFileFolder(path);
			admin_alertsPage.selectOperation(operation);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testCreateAnAlertOfNetAppQaFileOperationOnIntegratorsForCreatedOperation");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testCreateAnAlertOfNetAppQaFileOperationOnIntegratorsForCreatedOperation");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "94", title = "Verify  alert is created successfully for 'Modified' Operation on File Operation on integrators when Integration is set as 'netappqa'  and displayed on the alert page")
	@Test(priority = 2, groups = { "Regression" })
	public void testCreateAnAlertOfNetAppQaFileOperationOnIntegratorsForModifiedOperation() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 11, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 8, "Admin_Alert");
		String integration = sheet.getSingleCellData("Integration", 8, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 11, "Admin_Alert");
		String operation = sheet.getSingleCellData("Operation", 11, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");
		String path = sheet.getSingleCellData("Path", 8, "Admin_Alert");

		try {

			String[] alertDetail = { alertType, integration, alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.selectIntegrationType(integration);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.enterFileFolder(path);
			admin_alertsPage.selectOperation(operation);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testCreateAnAlertOfNetAppQaFileOperationOnIntegratorsForModifiedOperation");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testCreateAnAlertOfNetAppQaFileOperationOnIntegratorsForModifiedOperation");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "95", title = "Verify  alert is created successfully for 'Uploaded' Operation on File Operation on Office 365 when Integration is set as 'arexdev'  and displayed on the alert page")
	@Test(priority = 2, groups = { "Regression" })
	public void testCreateAnAlertFileOperationOffice365ForUploadedOperation() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 12, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 12, "Admin_Alert");
		String integration = sheet.getSingleCellData("Integration", 12, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 12, "Admin_Alert");
		String operation = sheet.getSingleCellData("Operation", 12, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");
		String path = sheet.getSingleCellData("Path", 12, "Admin_Alert");

		try {

			String[] alertDetail = { alertType, integration, alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.selectIntegrationType(integration);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.enterFileFolder(path);
			admin_alertsPage.selectOperation(operation);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testCreateAnAlertFileOperationOffice365ForUploadedOperation");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testCreateAnAlertFileOperationOffice365ForUploadedOperation");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "96", title = "Verify  alert is created successfully for 'Deleted' Operation on File Operation on Office 365 when Integration is set as 'arexdev'  and displayed on the alert page")
	@Test(priority = 2, groups = { "Regression" })
	public void testCreateAnAlertFileOperationOffice365ForDeletedOperation() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 13, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 12, "Admin_Alert");
		String integration = sheet.getSingleCellData("Integration", 12, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 13, "Admin_Alert");
		String operation = sheet.getSingleCellData("Operation", 13, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");
		String path = sheet.getSingleCellData("Path", 12, "Admin_Alert");

		try {

			String[] alertDetail = { alertType, integration, alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.selectIntegrationType(integration);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.enterFileFolder(path);
			admin_alertsPage.selectOperation(operation);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testCreateAnAlertFileOperationOffice365ForDeletedOperation");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testCreateAnAlertFileOperationOffice365ForDeletedOperation");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "97", title = "Verify  alert is created successfully for 'Renamed' Operation on File Operation on Office 365 when Integration is set as 'arexdev'  and displayed on the alert page")
	@Test(priority = 2, groups = { "Regression" })
	public void testCreateAnAlertFileOperationOffice365ForRenamedOperation() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 14, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 12, "Admin_Alert");
		String integration = sheet.getSingleCellData("Integration", 12, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 14, "Admin_Alert");
		String operation = sheet.getSingleCellData("Operation", 14, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");
		String path = sheet.getSingleCellData("Path", 12, "Admin_Alert");

		try {

			String[] alertDetail = { alertType, integration, alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.selectIntegrationType(integration);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.enterFileFolder(path);
			admin_alertsPage.selectOperation(operation);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testCreateAnAlertFileOperationOffice365ForRenamedOperation");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testCreateAnAlertFileOperationOffice365ForRenamedOperation");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "98", title = "Verify  alert is created successfully for 'Read' Operation on File Operation on Office 365 when Integration is set as 'arexdev'  and displayed on the alert page")
	@Test(priority = 2, groups = { "Regression" })
	public void testCreateAnAlertFileOperationOffice365ForReadOperation() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 15, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 12, "Admin_Alert");
		String integration = sheet.getSingleCellData("Integration", 12, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 15, "Admin_Alert");
		String operation = sheet.getSingleCellData("Operation", 15, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");
		String path = sheet.getSingleCellData("Path", 12, "Admin_Alert");

		try {

			String[] alertDetail = { alertType, integration, alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.selectIntegrationType(integration);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.enterFileFolder(path);
			admin_alertsPage.selectOperation(operation);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testCreateAnAlertFileOperationOffice365ForReadOperation");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testCreateAnAlertFileOperationOffice365ForReadOperation");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "99", title = "Verify  alert is created successfully for 'Modified' Operation on File Operation on Office 365 when Integration is set as 'arexdev'  and displayed on the alert page")
	@Test(priority = 2, groups = { "Regression" })
	public void testCreateAnAlertFileOperationOffice365ForModifiedOperation() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 16, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 12, "Admin_Alert");
		String integration = sheet.getSingleCellData("Integration", 12, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 16, "Admin_Alert");
		String operation = sheet.getSingleCellData("Operation", 16, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");
		String path = sheet.getSingleCellData("Path", 12, "Admin_Alert");

		try {

			String[] alertDetail = { alertType, integration, alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.selectIntegrationType(integration);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.enterFileFolder(path);
			admin_alertsPage.selectOperation(operation);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testCreateAnAlertFileOperationOffice365ForModifiedOperation");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testCreateAnAlertFileOperationOffice365ForModifiedOperation");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "100", title = "Verify alert is created successfully and displayed on the alert page Operation as Renamed.")
	@Test(priority = 2, groups = { "Regression" })
	public void testCreateAnAlertOfFileOperationOnAgentsForRenamedOperation() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 17, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 4, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 17, "Admin_Alert");
		String operation = sheet.getSingleCellData("Operation", 17, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");
		String path = sheet.getSingleCellData("Path", 4, "Admin_Alert");

		try {

			String[] alertDetail = { alertType, "", alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.enterFileFolder(path);
			admin_alertsPage.selectOperation(operation);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testCreateAnAlertOfFileOperationOnAgentsForRenamedOperation");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testCreateAnAlertOfFileOperationOnAgentsForRenamedOperation");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "121", title = "Verify alert is created successfully for 'Deleted' Operation on File Operation on integrators when Integration is set as 'window_file_server'")
	@Test(priority = 2, groups = { "Regression" })
	public void testCreateAnAlertOfFileOperationOnIntegratorWindowServerAgentDeletedOperationAlert() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 18, "Admin_Alert") + randNo;
		String integration = sheet.getSingleCellData("Integration", 18, "Admin_Alert");
		String alertType = sheet.getSingleCellData("Type", 18, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 18, "Admin_Alert");
		String operation = sheet.getSingleCellData("Operation", 18, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");
		String path = sheet.getSingleCellData("Path", 18, "Admin_Alert");
		try {

			String[] alertDetail = { alertType, integration, alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.selectIntegrationType(integration);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.enterFileFolder(path);
			admin_alertsPage.selectOperation(operation);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testCreateAnAlertOfFileOperationOnIntegratorWindowServerAgentDeletedOperationAlert");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testCreateAnAlertOfFileOperationOnIntegratorWindowServerAgentDeletedOperationAlert");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "122", title = "Verify alert is created successfully for 'Read' Operation on File Operation on integrators when Integration is set as 'window_file_server'")
	@Test(priority = 2, groups = { "Regression" })
	public void testCreateAnAlertOfFileOperationOnIntegratorWindowServerAgentasReadOperationAlert() throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 19, "Admin_Alert") + randNo;
		String integration = sheet.getSingleCellData("Integration", 18, "Admin_Alert");
		String alertType = sheet.getSingleCellData("Type", 18, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 19, "Admin_Alert");
		String operation = sheet.getSingleCellData("Operation", 19, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");
		String path = sheet.getSingleCellData("Path", 18, "Admin_Alert");
		try {

			String[] alertDetail = { alertType, integration, alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.selectIntegrationType(integration);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.enterFileFolder(path);
			admin_alertsPage.selectOperation(operation);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testCreateAnAlertOfFileOperationOnIntegratorWindowServerAgentReadOperationAlert");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testCreateAnAlertOfFileOperationOnIntegratorWindowServerAgentReadOperationAlert");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "123", title = "Verify alert is created successfully for 'Created' Operation on File Operation on integrators when Integration is set as 'window_file_server'")
	@Test(priority = 2, groups = { "Regression" })
	public void testCreateAnAlertOfFileOperationOnIntegratorWindowServerAgentasCreatedOperationAlert()
			throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 20, "Admin_Alert") + randNo;
		String integration = sheet.getSingleCellData("Integration", 18, "Admin_Alert");
		String alertType = sheet.getSingleCellData("Type", 18, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 20, "Admin_Alert");
		String operation = sheet.getSingleCellData("Operation", 20, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");
		String path = sheet.getSingleCellData("Path", 18, "Admin_Alert");
		try {

			String[] alertDetail = { alertType, integration, alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectIntegrationType(integration);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.enterFileFolder(path);
			admin_alertsPage.selectOperation(operation);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testCreateAnAlertOfFileOperationOnIntegratorWindowServerAgentasCreatedOperationAlert");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testCreateAnAlertOfFileOperationOnIntegratorWindowServerAgentasCreatedOperationAlert");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "124", title = "Verify alert is created successfully for 'Modified' Operation on File Operation on integrators when Integration is set as 'window_file_server'")
	@Test(priority = 2, groups = { "Regression" })
	public void testCreateAnAlertOfFileOperationOnIntegratorWindowServerAgentasModifiedOperationAlert()
			throws Exception {

		String alertName = sheet.getSingleCellData("AlertName", 21, "Admin_Alert") + randNo;
		String integration = sheet.getSingleCellData("Integration", 18, "Admin_Alert");
		String alertType = sheet.getSingleCellData("Type", 18, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 21, "Admin_Alert");
		String operation = sheet.getSingleCellData("Operation", 21, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");
		String path = sheet.getSingleCellData("Path", 18, "Admin_Alert");

		try {

			String[] alertDetail = { alertType, integration, alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.enterAlertName(alertName);
			admin_alertsPage.selectIntegrationType(integration);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.enterFileFolder(path);
			admin_alertsPage.selectOperation(operation);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testCreateAnAlertOfFileOperationOnIntegratorWindowServerAgentasModifiedOperationAlert");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testCreateAnAlertOfFileOperationOnIntegratorWindowServerAgentasModifiedOperationAlert");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "136", title = "Verify only those alerts are getting displayed which contains 'QA' in the 'Name'. Also, verify red bubble number displays as 1 on filter button present at 'Alerts' page.")
	@Test(priority = 1, groups = { "SmokeTest" })
	public void testUserIsAbleToFilterAlertWithNameContains() throws Exception {
		try {

			String fieldName = sheet.getSingleCellData("FieldName", 1, "Filter_Dialog");
			String condition = sheet.getSingleCellData("Condition", 1, "Filter_Dialog");
			String searchKeyword = sheet.getSingleCellData("Keyword", 1, "Filter_Dialog");

			String[] filter = { fieldName, condition, searchKeyword };

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, condition);
			filterDialogPage.enterValueInInputField(fieldName, searchKeyword);
			filterDialogPage.clickOnOkButton();
			filterDialogPage.assertNumberOnFilterButton(1);
			admin_alertsPage.assertValuesInColumns(fieldName, searchKeyword);
			admin_alertsPage.assertAppliedFilterInFilterButtonTooltip(filter);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterAlertWithNameContains");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterAlertWithNameContains");
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

	@TestCaseInfo(testCaseID = "137", title = "Verify only those alerts are getting displayed whose name equals to search keyword.Also, verify red bubble number displays as 1 on filter button present at 'Alerts' page.")
	@Test(priority = 3, groups = { "Functional" })
	public void testUserIsAbleToFilterAlertWithNameEquals() throws Exception {
		try {

			String fieldName = sheet.getSingleCellData("FieldName", 1, "Filter_Dialog");
			String condition = sheet.getSingleCellData("Condition", 2, "Filter_Dialog");
			String searchKeyword = sheet.getSingleCellData("Keyword", 2, "Filter_Dialog");

			String[] filter = { fieldName, condition, searchKeyword };

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, condition);
			filterDialogPage.enterValueInInputField(fieldName, searchKeyword);
			filterDialogPage.clickOnOkButton();
			filterDialogPage.assertNumberOnFilterButton(1);
			admin_alertsPage.assertValuesInColumns(fieldName, searchKeyword);
			admin_alertsPage.assertAppliedFilterInFilterButtonTooltip(filter);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterAlertWithNameEquals");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterAlertWithNameEquals");
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
	
	@TestCaseInfo(testCaseID = "138", title = "Verify only those alerts are getting displayed whose name Start With to search keyword.Also, verify red bubble number displays as 1 on filter button present at 'Alerts' page.")
	@Test(priority = 3, groups = { "Functional" })
	public void testUserIsAbleToFilterAlertWithNameStartWith() throws Exception {
		try {

			String fieldName = sheet.getSingleCellData("FieldName", 1, "Filter_Dialog");
			String condition = sheet.getSingleCellData("Condition", 3, "Filter_Dialog");
			String searchKeyword = sheet.getSingleCellData("Keyword", 2, "Filter_Dialog");

			String[] filter = { fieldName, condition, searchKeyword };

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, condition);
			filterDialogPage.enterValueInInputField(fieldName, searchKeyword);
			filterDialogPage.clickOnOkButton();
			filterDialogPage.assertNumberOnFilterButton(1);
			admin_alertsPage.assertValuesInColumns(fieldName, searchKeyword);
			admin_alertsPage.assertAppliedFilterInFilterButtonTooltip(filter);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterAlertWithNameStartWith");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterAlertWithNameStartWith");
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
	
	@TestCaseInfo(testCaseID = "139", title = "Verify only 'Activity Less Than X hours' alerts are getting displayed on alerts page if select type as 'Activity Less Than X hours'")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserIsAbleToFilterAlertByIntegrationNameActivityLessThanXHours() throws Exception {
		try {

			String fieldName = sheet.getSingleCellData("FieldName", 2, "Filter_Dialog");
			String condition = sheet.getSingleCellData("Condition", 2, "Filter_Dialog");
			String typeName = sheet.getSingleCellData("TypeName", 1, "Filter_Dialog");

			String[] filter = { fieldName, condition, typeName };

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, typeName);
			filterDialogPage.clickOnOkButton();
			filterDialogPage.assertNumberOnFilterButton(1);
			admin_alertsPage.assertValuesInColumns(fieldName, typeName);
			admin_alertsPage.assertAppliedFilterInFilterButtonTooltip(filter);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterAlertByIntegrationNameActivityLessThanXHours");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterAlertByIntegrationNameActivityLessThanXHours");
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

	@TestCaseInfo(testCaseID = "140", title = "Verify only 'File Operation on Agents' alerts are getting displayed on alerts page if select type as 'File Operation Agents'")
	@Test(priority = 3, groups = { "Functional" })
	public void testUserIsAbleToFilterAlertByIntegrationNameFileOperationOnAgents() throws Exception {
		try {

			String fieldName = sheet.getSingleCellData("FieldName", 2, "Filter_Dialog");
			String condition = sheet.getSingleCellData("Condition", 2, "Filter_Dialog");
			String typeName = sheet.getSingleCellData("TypeName", 2, "Filter_Dialog");

			String[] filter = { fieldName, condition, typeName };

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, typeName);
			filterDialogPage.clickOnOkButton();
			filterDialogPage.assertNumberOnFilterButton(1);
			admin_alertsPage.assertValuesInColumns(fieldName, typeName);
			admin_alertsPage.assertAppliedFilterInFilterButtonTooltip(filter);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterAlertByIntegrationNameFileOperationOnAgents");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterAlertByIntegrationNameFileOperationOnAgents");
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

	@TestCaseInfo(testCaseID = "141", title = "Verify only 'File Operation on Integrators' alerts are getting displayed on alerts page if select type as 'File Operation Integrators'")
	@Test(priority = 3, groups = { "Functional" })
	public void testUserIsAbleToFilterAlertByIntegrationNameFileOperationOnIntegrators() throws Exception {
		try {

			String fieldName = sheet.getSingleCellData("FieldName", 2, "Filter_Dialog");
			String condition = sheet.getSingleCellData("Condition", 2, "Filter_Dialog");
			String typeName = sheet.getSingleCellData("TypeName", 3, "Filter_Dialog");

			String[] filter = { fieldName, condition, typeName };

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, typeName);
			filterDialogPage.clickOnOkButton();
			filterDialogPage.assertNumberOnFilterButton(1);
			admin_alertsPage.assertValuesInColumns(fieldName, typeName);
			admin_alertsPage.assertAppliedFilterInFilterButtonTooltip(filter);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterAlertByIntegrationNameFileOperationOnIntegrators");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterAlertByIntegrationNameFileOperationOnIntegrators");
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

	@TestCaseInfo(testCaseID = "148", title = "Verify only 'Critical' alerts are getting displayed on alerts page if select Severity as 'Critical'")
	@Test(priority = 3, groups = { "Functional" })
	public void testUserIsAbleToFilterAlertBySeverityAsCritical() throws Exception {
		try {

			String fieldName = sheet.getSingleCellData("FieldName", 4, "Filter_Dialog");
			String condition = sheet.getSingleCellData("Condition", 2, "Filter_Dialog");
			String severityName = sheet.getSingleCellData("SeverityName", 1, "Filter_Dialog");

			String[] filter = { fieldName, condition, severityName };

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, severityName);
			filterDialogPage.clickOnOkButton();
			filterDialogPage.assertNumberOnFilterButton(1);
			admin_alertsPage.assertValuesInColumns(fieldName, severityName);
			admin_alertsPage.assertAppliedFilterInFilterButtonTooltip(filter);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterAlertBySeverityAsCritical");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterAlertBySeverityAsCritical");
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

	@TestCaseInfo(testCaseID = "149", title = "Verify only 'Info' alerts are getting displayed on alerts page if select Severity as 'Info'")
	@Test(priority = 3, groups = { "Functional" })
	public void testUserIsAbleToFilterAlertBySeverityAsInfo() throws Exception {
		try {

			String fieldName = sheet.getSingleCellData("FieldName", 4, "Filter_Dialog");
			String condition = sheet.getSingleCellData("Condition", 2, "Filter_Dialog");
			String severityName = sheet.getSingleCellData("SeverityName", 2, "Filter_Dialog");

			String[] filter = { fieldName, condition, severityName };

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, severityName);
			filterDialogPage.clickOnOkButton();
			filterDialogPage.assertNumberOnFilterButton(1);
			admin_alertsPage.assertValuesInColumns(fieldName, severityName);
			admin_alertsPage.assertAppliedFilterInFilterButtonTooltip(filter);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterAlertBySeverityAsInfo");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterAlertBySeverityAsInfo");
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

	@TestCaseInfo(testCaseID = "150", title = "Verify only 'Warning' alerts are getting displayed on alerts page if select Severity as 'Warning'")
	@Test(priority = 3, groups = { "Functional" })
	public void testUserIsAbleToFilterAlertBySeverityAsWarning() throws Exception {
		try {

			String fieldName = sheet.getSingleCellData("FieldName", 4, "Filter_Dialog");
			String condition = sheet.getSingleCellData("Condition", 2, "Filter_Dialog");
			String severityName = sheet.getSingleCellData("SeverityName", 3, "Filter_Dialog");

			String[] filter = { fieldName, condition, severityName };

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, severityName);
			filterDialogPage.clickOnOkButton();
			filterDialogPage.assertNumberOnFilterButton(1);
			admin_alertsPage.assertValuesInColumns(fieldName, severityName);
			admin_alertsPage.assertAppliedFilterInFilterButtonTooltip(filter);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterAlertBySeverityAsWarning");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterAlertBySeverityAsWarning");
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

	@TestCaseInfo(testCaseID = "152", title = "Verify alerts are getting displayed if Name as Contains -<xyz> and Type as Activity Less Than X Hours are selected on filter pop up.")
	@Test(priority = 3, groups = { "Functional" })
	public void testUserIsAbleToFilterAlertByNameWithContainAndIntegrationAsActivityLessThanXHours() throws Exception {
		try {
			String keyword = sheet.getSingleCellData("Keyword", 5, "Filter_Dialog");
			String nameField = sheet.getSingleCellData("FieldName", 1, "Filter_Dialog");
			String condition = sheet.getSingleCellData("Condition", 1, "Filter_Dialog");

			String typeName = sheet.getSingleCellData("TypeName", 1, "Filter_Dialog");
			String typeField = sheet.getSingleCellData("FieldName", 2, "Filter_Dialog");
			String condition1 = sheet.getSingleCellData("Condition", 2, "Filter_Dialog");

			String[] filter = { nameField, condition, keyword, typeField, condition1, typeName };

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(nameField, condition);
			filterDialogPage.enterValueInInputField(nameField, keyword);
			filterDialogPage.selectOptionFromDropdown(typeField, typeName);
			filterDialogPage.clickOnOkButton();
			filterDialogPage.assertNumberOnFilterButton(2);
			admin_alertsPage.assertValuesInColumns(nameField, keyword);
			admin_alertsPage.assertValuesInColumns(typeField, typeName);
			admin_alertsPage.assertAppliedFilterInFilterButtonTooltip(filter);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterAlertByNameWithContainAndIntegrationAsActivityLessThanXHours");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterAlertByNameWithContainAndIntegrationAsActivityLessThanXHours");
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

	@TestCaseInfo(testCaseID = "142", title = "Verify only  those alerts are getting displayed whose 'Integration' contains 'QA'.Also, verify red bubble number displays as 1 on filter button present at 'Alerts' page.")
	@Test(priority = 3, groups = { "Functional" })
	public void testUserIsAbleToFilterAlertContainsQAInIntegrators() throws Exception {
		try {

			String integrationField = sheet.getSingleCellData("FieldName", 3, "Filter_Dialog");
			String condition = sheet.getSingleCellData("Condition", 1, "Filter_Dialog");
			String integratorsValue = sheet.getSingleCellData("Integrators", 1, "Filter_Dialog");

			String[] filter = { integrationField, condition, integratorsValue };

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(integrationField, condition);
			filterDialogPage.enterValueInInputField(integrationField, integratorsValue);
			filterDialogPage.clickOnOkButton();
			filterDialogPage.assertNumberOnFilterButton(1);
			admin_alertsPage.assertValuesInColumns(integrationField, integratorsValue);
			admin_alertsPage.assertAppliedFilterInFilterButtonTooltip(filter);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterAlertContainsQAInIntegrators");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterAlertContainsQAInIntegrators");
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

	@TestCaseInfo(testCaseID = "143", title = "Verify no  alerts are getting displayed if 'Integration' contains  Value is not having any matched item  .Also, verify red bubble number displays as 1 on filter button present at 'Alerts' page.")
	@Test(priority = 3, groups = { "Functional" })
	public void testNoItemsIsDisplayedWhenUserApplyFilterWithUnmatchedKeywordAndContainsConditionForIntegratorsFields()
			throws Exception {
		try {

			String integrationField = sheet.getSingleCellData("FieldName", 3, "Filter_Dialog");
			String condition = sheet.getSingleCellData("Condition", 1, "Filter_Dialog");
			String integratorsValue = sheet.getSingleCellData("Integrators", 2, "Filter_Dialog");

			String[] filter = { integrationField, condition, integratorsValue };

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(integrationField, condition);
			filterDialogPage.enterValueInInputField(integrationField, integratorsValue);
			filterDialogPage.clickOnOkButton();
			filterDialogPage.assertNumberOnFilterButton(1);
			assertTrue(admin_alertsPage.assertNoRecordFound());
			admin_alertsPage.assertAppliedFilterInFilterButtonTooltip(filter);

		} catch (Error e) {
			captureScreenshot(
					"testNoItemsIsDisplayedWhenUserApplyFilterWithUnmatchedKeywordAndContainsConditionForIntegratorsFields");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot(
					"testNoItemsIsDisplayedWhenUserApplyFilterWithUnmatchedKeywordAndContainsConditionForIntegratorsFields");
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

	@TestCaseInfo(testCaseID = "144", title = "Verify only  those alerts are getting displayed whose 'Integration' equals to 'netappqa'. Also, verify red bubble number displays as 1 on filter button present at 'Alerts' page.")
	@Test(priority = 3, groups = { "Functional" })
	public void testUserIsAbleToFilterAlertEqualsToNetAppQAInIntegrators() throws Exception {
		try {

			String integrationField = sheet.getSingleCellData("FieldName", 3, "Filter_Dialog");
			String condition = sheet.getSingleCellData("Condition", 2, "Filter_Dialog");
			String integratorsValue = sheet.getSingleCellData("Integrators", 3, "Filter_Dialog");

			String[] filter = { integrationField, condition, integratorsValue };

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(integrationField, condition);
			filterDialogPage.enterValueInInputField(integrationField, integratorsValue);
			filterDialogPage.clickOnOkButton();
			filterDialogPage.assertNumberOnFilterButton(1);
			admin_alertsPage.assertValuesInColumns(integrationField, integratorsValue);
			admin_alertsPage.assertAppliedFilterInFilterButtonTooltip(filter);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterAlertEqualsToNetAppQAInIntegrators");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterAlertEqualsToNetAppQAInIntegrators");
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

	@TestCaseInfo(testCaseID = "145", title = "Verify no  alerts are getting displayed if 'Integration' equals to 'QA' is selected on filter pop up. Also, verify red bubble number displays as 1 on filter button present at 'Alerts' page.")
	@Test(priority = 3, groups = { "Functional" })
	public void testNoItemsIsDisplayedWhenUserApplyFilterWithQAKeywordAndEqualsConditionForIntegratorsFields()
			throws Exception {
		try {

			String integrationField = sheet.getSingleCellData("FieldName", 3, "Filter_Dialog");
			String condition = sheet.getSingleCellData("Condition", 2, "Filter_Dialog");
			String integratorsValue = sheet.getSingleCellData("Integrators", 1, "Filter_Dialog");

			String[] filter = { integrationField, condition, integratorsValue };

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(integrationField, condition);
			filterDialogPage.enterValueInInputField(integrationField, integratorsValue);
			filterDialogPage.clickOnOkButton();
			filterDialogPage.assertNumberOnFilterButton(1);
			assertTrue(admin_alertsPage.assertNoRecordFound());
			admin_alertsPage.assertAppliedFilterInFilterButtonTooltip(filter);

		} catch (Error e) {
			captureScreenshot(
					"testNoItemsIsDisplayedWhenUserApplyFilterWithQAKeywordAndEqualsConditionForIntegratorsFields");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot(
					"testNoItemsIsDisplayedWhenUserApplyFilterWithQAKeywordAndEqualsConditionForIntegratorsFields");
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

	@TestCaseInfo(testCaseID = "146", title = "Verify only  those alerts are getting displayed whose 'Integration' start with 'QA'. Also, verify red bubble number displays as 1 on filter button present at 'Alerts' page.")
	@Test(priority = 3, groups = { "Functional" })
	public void testNoItemsIsDisplayedWhenUserApplyFilterWithnetaapKeywordAndStarWithConditionForIntegratorsFields()
			throws Exception {
		try {

			String integrationField = sheet.getSingleCellData("FieldName", 3, "Filter_Dialog");
			String condition = sheet.getSingleCellData("Condition", 3, "Filter_Dialog");
			String integratorsValue = sheet.getSingleCellData("Integrators", 4, "Filter_Dialog");

			String[] filter = { integrationField, condition, integratorsValue };

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(integrationField, condition);
			filterDialogPage.enterValueInInputField(integrationField, integratorsValue);
			filterDialogPage.clickOnOkButton();
			filterDialogPage.assertNumberOnFilterButton(1);
			admin_alertsPage.assertValuesInColumns(integrationField, integratorsValue);
			admin_alertsPage.assertAppliedFilterInFilterButtonTooltip(filter);

		} catch (Error e) {
			captureScreenshot(
					"testNoItemsIsDisplayedWhenUserApplyFilterWithnetaapKeywordAndStarWithConditionForIntegratorsFields");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot(
					"testNoItemsIsDisplayedWhenUserApplyFilterWithnetaapKeywordAndStarWithConditionForIntegratorsFields");
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

	@TestCaseInfo(testCaseID = "153", title = "Verify alerts are getting displayed based on the below selected criteria on filter pop up.1. Name: Contains - Sample,2. Type: File operation on integrators.3. Integration: Contains - netapp")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserIsAbleToFilterAlertByNameTypeAndIntegration() throws Exception {
		try {
			String nameField = sheet.getSingleCellData("FieldName", 1, "Filter_Dialog");
			String keyword = sheet.getSingleCellData("Keyword", 5, "Filter_Dialog");
			String condition = sheet.getSingleCellData("Condition", 1, "Filter_Dialog");

			String typeName = sheet.getSingleCellData("TypeName", 1, "Filter_Dialog");
			String typeField = sheet.getSingleCellData("FieldName", 2, "Filter_Dialog");
			String condition1 = sheet.getSingleCellData("Condition", 2, "Filter_Dialog");

			String integratorValue = sheet.getSingleCellData("Integrators", 4, "Filter_Dialog");
			String integratorsField = sheet.getSingleCellData("FieldName", 3, "Filter_Dialog");

			String[] filter = { nameField, condition, keyword, typeField, condition1, typeName, integratorsField,
					condition, integratorValue };

			filterDialogPage.clickOnFilterButton();

			filterDialogPage.selectOptionFromDropdown(nameField, condition);
			filterDialogPage.enterValueInInputField(nameField, keyword);

			filterDialogPage.selectOptionFromDropdown(typeField, typeName);

			filterDialogPage.selectOptionFromDropdown(integratorsField, condition);
			filterDialogPage.enterValueInInputField(integratorsField, integratorValue);

			filterDialogPage.clickOnOkButton();

			filterDialogPage.assertNumberOnFilterButton(3);

			admin_alertsPage.assertValuesInColumns(nameField, keyword);
			admin_alertsPage.assertValuesInColumns(typeField, typeName);
			admin_alertsPage.assertValuesInColumns(integratorsField, integratorValue);

			admin_alertsPage.assertAppliedFilterInFilterButtonTooltip(filter);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterAlertByNameTypeAndIntegration");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterAlertByNameTypeAndIntegration");
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

	@TestCaseInfo(testCaseID = "154", title = "Verify alerts are getting displayed based on the below selected criteria on filter pop up.1. Name: Contains - Sample2. Type: File operation on integrators.3. Integration: Contains - QA-test 4. Severity: Info")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserIsAbleToFilterAlertByNameTypeIntegrationAndSeverity() throws Exception {
		try {

			String nameField = sheet.getSingleCellData("FieldName", 1, "Filter_Dialog");
			String keyword = sheet.getSingleCellData("Keyword", 1, "Filter_Dialog");
			String condition = sheet.getSingleCellData("Condition", 1, "Filter_Dialog");

			String typeValue = sheet.getSingleCellData("TypeName", 3, "Filter_Dialog");
			String typeField = sheet.getSingleCellData("FieldName", 2, "Filter_Dialog");
			String condition1 = sheet.getSingleCellData("Condition", 2, "Filter_Dialog");

			String integratorValue = sheet.getSingleCellData("Integrators", 3, "Filter_Dialog");
			String integratorsField = sheet.getSingleCellData("FieldName", 3, "Filter_Dialog");

			String severityField = sheet.getSingleCellData("FieldName", 4, "Filter_Dialog");
			String severityValue = sheet.getSingleCellData("SeverityName", 3, "Filter_Dialog");

			String[] filter = { nameField, condition, keyword, typeField, condition1, typeValue, integratorsField,
					condition, integratorValue, severityField, condition1, severityValue };

			filterDialogPage.clickOnFilterButton();

			filterDialogPage.selectOptionFromDropdown(nameField, condition);
			filterDialogPage.enterValueInInputField(nameField, keyword);

			filterDialogPage.selectOptionFromDropdown(typeField, typeValue);

			filterDialogPage.selectOptionFromDropdown(integratorsField, condition);
			filterDialogPage.enterValueInInputField(integratorsField, integratorValue);

			filterDialogPage.selectOptionFromDropdown(severityField, severityValue);

			filterDialogPage.clickOnOkButton();

			filterDialogPage.assertNumberOnFilterButton(4);

			admin_alertsPage.assertValuesInColumns(nameField, keyword);
			admin_alertsPage.assertValuesInColumns(typeField, typeValue);
			admin_alertsPage.assertValuesInColumns(integratorsField, integratorValue);
			admin_alertsPage.assertValuesInColumns(severityField, severityValue);

			admin_alertsPage.assertAppliedFilterInFilterButtonTooltip(filter);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterAlertByNameTypeIntegrationAndSeverity");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterAlertByNameTypeIntegrationAndSeverity");
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

	@TestCaseInfo(testCaseID = "155", title = "Verify all fields present on filter pop up get reset or goes to default state if click on 'Clear' button")
	@Test(priority = 2, groups = { "Regression" })
	public void testUserIsAbleToGetResetDefaultValueWhenClickedOnClearButtonOnFilterPoUp() throws Exception {
		try {

			String nameField = sheet.getSingleCellData("FieldName", 1, "Filter_Dialog");
			String keyword = sheet.getSingleCellData("Keyword", 1, "Filter_Dialog");
			String condition = sheet.getSingleCellData("Condition", 1, "Filter_Dialog");

			String typeValue = sheet.getSingleCellData("TypeName", 3, "Filter_Dialog");
			String typeField = sheet.getSingleCellData("FieldName", 2, "Filter_Dialog");
			String condition1 = sheet.getSingleCellData("Condition", 2, "Filter_Dialog");

			String integratorValue = sheet.getSingleCellData("Integrators", 3, "Filter_Dialog");
			String integratorsField = sheet.getSingleCellData("FieldName", 3, "Filter_Dialog");

			String severityField = sheet.getSingleCellData("FieldName", 4, "Filter_Dialog");
			String severityValue = sheet.getSingleCellData("SeverityName", 3, "Filter_Dialog");

			String[] filter = { nameField, condition, keyword, typeField, condition1, typeValue, integratorsField,
					condition, integratorValue, severityField, condition1, severityValue };

			filterDialogPage.clickOnFilterButton();

			filterDialogPage.selectOptionFromDropdown(nameField, condition);
			filterDialogPage.enterValueInInputField(nameField, keyword);

			filterDialogPage.selectOptionFromDropdown(typeField, typeValue);

			filterDialogPage.selectOptionFromDropdown(integratorsField, condition);
			filterDialogPage.enterValueInInputField(integratorsField, integratorValue);

			filterDialogPage.selectOptionFromDropdown(severityField, severityValue);

			filterDialogPage.clickOnOkButton();

			filterDialogPage.assertNumberOnFilterButton(4);

			admin_alertsPage.assertValuesInColumns(nameField, keyword);
			admin_alertsPage.assertValuesInColumns(typeField, typeValue);
			admin_alertsPage.assertValuesInColumns(integratorsField, integratorValue);
			admin_alertsPage.assertValuesInColumns(severityField, severityValue);

			admin_alertsPage.assertAppliedFilterInFilterButtonTooltip(filter);
			filterDialogPage.clickOnFilterButton();
			filterDialogPage.clickOnClearButton();
			filterDialogPage.clickOnOkButton();

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToFilterAlertByNameTypeIntegrationAndSeverity");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToFilterAlertByNameTypeIntegrationAndSeverity");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

				filterDialogPage.clickOnFilterButton();
				filterDialogPage.clickOnCancelButton();
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "158", title = "Verify user is able to edit already existing alert")
	@Test(priority = 3, groups = { "Functional" })
	public void testUserIsAbleToEditExistingAlert() throws Exception {

		String alertExistingName = sheet.getSingleCellData("AlertName", 1, "Admin_Alert") + randNo;
		String alertType = sheet.getSingleCellData("Type", 1, "Admin_Alert");
		String alertSeverity = sheet.getSingleCellData("Severity", 1, "Admin_Alert");
		String email = sheet.getSingleCellData("Email", 1, "Admin_Alert");

		String alertNewName = sheet.getSingleCellData("AlertName", 1, "Admin_Alert") + randNo + "NewAlert";
		try {

			String[] alertDetail = { alertType, "", alertSeverity, "Edit", "Delete" };

			admin_alertsPage.clickOnCreateAlertButton();
			admin_alertsPage.selectType(alertType);
			admin_alertsPage.enterAlertName(alertExistingName);
			admin_alertsPage.selectSeverity(alertSeverity);
			admin_alertsPage.sendEmailTo(email);
			admin_alertsPage.clickOnSaveButton();
			admin_alertsPage.clickonHeaderNameToSortList("Name");
			admin_alertsPage.verifySavedAlertDetails(alertExistingName, alertDetail);
			admin_alertsPage.editExistingAlertName(alertExistingName, alertNewName);
			admin_alertsPage.verifySavedAlertDetails(alertNewName, alertDetail);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleToEditExistingAlert");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleToEditExistingAlert");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

				admin_alertsPage.deleteExistingAlert(alertNewName);
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
