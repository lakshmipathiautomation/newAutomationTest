package tests;

import java.util.Random;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.Admin_AlertsPage;
import pages.Admin_ApplicationLogPage;
import pages.Admin_Endpoint_SettingPage;
import pages.Admin_UsersPage;
import pages.LoginPage;
import utility.DriverTestCase;
import utility.ExecutionLog;
import utility.PropertyReader;

public class Admin_Endpoint_SettingTest extends DriverTestCase {
	private static LoginPage loginPage;
	private static Admin_Endpoint_SettingPage admin_Endpoint_SettingPage;

	PropertyReader propertyReader = new PropertyReader();
	String userName = propertyReader.readProperty("username");
	String password = propertyReader.readProperty("password");

	Random random = new Random();
	int randNo = random.nextInt(100000);

	@BeforeTest
	public void initForAppLog() throws Exception {
		setup();
		loginPage = new LoginPage(getWebDriver());
		new Admin_ApplicationLogPage(getWebDriver());
		new Admin_AlertsPage(getWebDriver());
		new Admin_UsersPage(getWebDriver());
		admin_Endpoint_SettingPage = new Admin_Endpoint_SettingPage(getWebDriver());

		loginPage.loginIntoApplication(userName, password);

	}

	@Test(description = "C189", groups = "Functional Testing", testName = "Verify application allows to disable the File Audit ")
	public void testUserClickOnEnableButtonToDisableFileAuditOptionAndExtensions() throws Exception {

		try {
			Boolean status = true;

			admin_Endpoint_SettingPage.selectAdminEndpointsSettingMenu();
			admin_Endpoint_SettingPage.checkUncheckEnabledCheckBox(status);
			admin_Endpoint_SettingPage.clickOnSaveButton();

		} catch (Error e) {
			getScreenshot("testUserClickOnEnableButtonToDisableFileAuditOptionAndExtensions");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testUserClickOnEnableButtonToDisableFileAuditOptionAndExtensions");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				log.log("File Audit has been enabled to disable");
			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

}
