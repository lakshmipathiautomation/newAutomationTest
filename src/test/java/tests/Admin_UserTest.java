package tests;

import java.util.Random;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.Admin_UsersPage;
import pages.FilterDialogPage;
import pages.LoginPage;
import utility.DriverTestCase;
import utility.ExcelUtils;
import utility.ExecutionLog;
import utility.PropertyReader;
import utility.QAAnnotations.TestCaseInfo;

public class Admin_UserTest extends DriverTestCase {

	private static LoginPage loginPage;
	private static ExcelUtils sheet;
	private static FilterDialogPage filterDialogPage;
	private static Admin_UsersPage admin_UsersPage;

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
		admin_UsersPage = new Admin_UsersPage(getWebDriver());
		loginPage.loginIntoApplication(userName, password);
		admin_UsersPage.selectAdminUsersMenu();
	}

	@TestCaseInfo(testCaseID = "236", title = "Verify application allows to create a new user with role administration")
	@Test(priority = 2, groups = { "Regression" })
	public void testApplicationAllowsToCreateNewUserWithAdministrationRole() throws Exception {
		String username = sheet.getSingleCellData("Name", 1, "Admin_Users") + randNo;
		String userEmail = sheet.getSingleCellData("Email", 1, "Admin_Users") + randNo + ".com";
		String userRole = sheet.getSingleCellData("Role", 1, "Admin_Users");
		String passwordSettingOption = sheet.getSingleCellData("PasswordOption", 1, "Admin_Users");
		try {

			String[] userDetails = { userEmail, "", userRole, "" };

			admin_UsersPage.clickOnCreateUserButton();
			admin_UsersPage.enterName(username);
			admin_UsersPage.enterEmail(userEmail);
			admin_UsersPage.selectUsersRoles(userRole);
			admin_UsersPage.selectPasswordSettingRadioButton(passwordSettingOption);
			admin_UsersPage.clickOnSaveButton();
			admin_UsersPage.verifyUserIsSaved(username);
			admin_UsersPage.verifyUserDetailsOnUsersPage(username, userDetails);

		} catch (Error e) {
			captureScreenshot("testApplicationAllowsToCreateNewUserWithAdministrationRole");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testApplicationAllowsToCreateNewUserWithAdministrationRole");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
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

	@TestCaseInfo(testCaseID = "237", title = "Verify application allows to create a user with 'User Manager'")
	@Test(priority = 2, groups = { "Regression" })
	public void testApplicationAllowsToCreateAUserWithManageUserRole() throws Exception {

		String username = sheet.getSingleCellData("Name", 1, "Admin_Users") + randNo;
		String userEmail = sheet.getSingleCellData("Email", 1, "Admin_Users") + randNo + ".com";
		String userRole = sheet.getSingleCellData("Role", 2, "Admin_Users");
		String passwordSettingOption = sheet.getSingleCellData("PasswordOption", 1, "Admin_Users");
		String newRole = sheet.getSingleCellData("Role", 1, "Admin_Users");
		try {

			String[] teamMembers = { "QA", "test" };
			String[] userDetails = { userEmail, "", userRole, "" };

			admin_UsersPage.clickOnCreateUserButton();
			admin_UsersPage.enterName(username);
			admin_UsersPage.enterEmail(userEmail);
			admin_UsersPage.selectUsersRoles(userRole);
			admin_UsersPage.clickOnManagerOfCheckboxes(teamMembers);
			admin_UsersPage.selectPasswordSettingRadioButton(passwordSettingOption);
			admin_UsersPage.clickOnSaveButton();
			admin_UsersPage.verifyUserIsSaved(username);
			admin_UsersPage.verifyUserDetailsOnUsersPage(username, userDetails);

		} catch (Error e) {
			captureScreenshot("testApplicationAllowsToCreateAUserWithManageUserRole");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testApplicationAllowsToCreateAUserWithManageUserRole");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
//				admin_UsersPage.editExistingUser(username, username, newRole);
//				admin_UsersPage.deleteExistingUser(username);

			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "241", title = "Verify application allows to set the password.")
	@Test(priority = 2, groups = { "Regression" })
	public void testApplicationAllowsToSetPassword() throws Exception {
		String username = sheet.getSingleCellData("Name", 1, "Admin_Users") + randNo;
		String userEmail = sheet.getSingleCellData("Email", 1, "Admin_Users") + randNo + ".com";
		String userRole = sheet.getSingleCellData("Role", 1, "Admin_Users");
		String passwordSettingOption = sheet.getSingleCellData("PasswordOption", 1, "Admin_Users");
		String password = sheet.getSingleCellData("Password", 1, "Admin_Users");
		String confPassword = sheet.getSingleCellData("ConfirmPassword", 1, "Admin_Users");

		try {

			String[] userDetails = { userEmail, "", userRole, "" };

			admin_UsersPage.clickOnCreateUserButton();
			admin_UsersPage.enterName(username);
			admin_UsersPage.enterEmail(userEmail);
			admin_UsersPage.selectUsersRoles(userRole);
			admin_UsersPage.selectPasswordSettingRadioButton(passwordSettingOption);
			admin_UsersPage.clickOnSaveButton();
			admin_UsersPage.verifyUserIsSaved(username);
			admin_UsersPage.verifyUserDetailsOnUsersPage(username, userDetails);

			admin_UsersPage.setPasswordForExistingUser(username, password, confPassword);

			loginPage.logoutFromApplication();
			loginPage.loginIntoApplication(userEmail, password);
			loginPage.closeWelcomePopUp();
			loginPage.verifyLogin(userEmail);

		} catch (Error e) {
			captureScreenshot("testApplicationAllowsToSetPassword");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testApplicationAllowsToSetPassword");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {
				loginPage.logoutFromApplication();
				loginPage.loginIntoApplication(userName, password);
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

	@TestCaseInfo(testCaseID = "242", title = "Verify 'Password complexity requirements not met' message on 'Set Password' dialog box if password doesn't meet all conditions")
	@Test(priority = 2, groups = { "Functional" })
	public void testPasswordComplexityRequirementsNotMet() throws Exception {
		String username = sheet.getSingleCellData("Name", 1, "Admin_Users") + randNo;
		String userEmail = sheet.getSingleCellData("Email", 1, "Admin_Users") + randNo + ".com";
		String userRole = sheet.getSingleCellData("Role", 1, "Admin_Users");
		String passwordSettingOption = sheet.getSingleCellData("PasswordOption", 1, "Admin_Users");
		String password = sheet.getSingleCellData("Password", 2, "Admin_Users");
		String confPassword = sheet.getSingleCellData("ConfirmPassword", 2, "Admin_Users");

		try {

			String[] userDetails = { userEmail, "", userRole, "" };

			admin_UsersPage.clickOnCreateUserButton();
			admin_UsersPage.enterName(username);
			admin_UsersPage.enterEmail(userEmail);
			admin_UsersPage.selectUsersRoles(userRole);
			admin_UsersPage.selectPasswordSettingRadioButton(passwordSettingOption);
			admin_UsersPage.clickOnSaveButton();
			admin_UsersPage.verifyUserIsSaved(username);
			admin_UsersPage.verifyUserDetailsOnUsersPage(username, userDetails);

			admin_UsersPage.setPasswordForExistingUser(username, password, confPassword);
			admin_UsersPage.assertPasswordNotMetCriteria();
			admin_UsersPage.clickOnCancelButton();

		} catch (Error e) {
			captureScreenshot("testPasswordComplexityRequirementsNotMet");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testPasswordComplexityRequirementsNotMet");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "243", title = "Verify User is not able to login into application When profile is  disable")
	@Test(priority = 2, groups = { "Functional" })
	public void testUserIsNotAbleToLoginIntoApplicationWhenDisabledUserProfile() throws Exception {

		String username = sheet.getSingleCellData("Name", 1, "Admin_Users") + randNo;
		String userEmail = sheet.getSingleCellData("Email", 1, "Admin_Users") + randNo + ".com";
		String userRole = sheet.getSingleCellData("Role", 1, "Admin_Users");
		String passwordSettingOption = sheet.getSingleCellData("PasswordOption", 2, "Admin_Users");
		String password = sheet.getSingleCellData("Password", 1, "Admin_Users");
		String confPassword = sheet.getSingleCellData("ConfirmPassword", 1, "Admin_Users");

		try {

			String[] userDetails = { userEmail, "", userRole, "" };

			admin_UsersPage.clickOnCreateUserButton();
			admin_UsersPage.enterName(username);
			admin_UsersPage.enterEmail(userEmail);
			admin_UsersPage.selectUsersRoles(userRole);
			admin_UsersPage.selectPasswordSettingRadioButton(passwordSettingOption);
			admin_UsersPage.enterNewUserPassword(password, confPassword);
			admin_UsersPage.clickOnSaveButton();
			admin_UsersPage.verifyUserIsSaved(username);
			admin_UsersPage.verifyUserDetailsOnUsersPage(username, userDetails);

			admin_UsersPage.editAndDisableExistingUser(username);
			admin_UsersPage.clickOnSaveButton();

			admin_UsersPage.assertUserIsEnabledOrDisabled(username, false);
			loginPage.logoutUserFromApplication();
			loginPage.loginIntoApplication(username, password);

		} catch (Error e) {
			captureScreenshot("testUserIsNotAbleToLoginIntoApplicationWhenDisabledUserProfile");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsNotAbleToLoginIntoApplicationWhenDisabledUserProfile");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "244", title = "Verify User is not able to login into application When profile is  deleted")
	@Test(priority = 2, groups = { "Functional" })
	public void testUserIsNotAbleToLoginIntoApplicationWhenUserProfileIsDeleted() throws Exception {

		String username = sheet.getSingleCellData("Name", 1, "Admin_Users") + randNo;
		String userEmail = sheet.getSingleCellData("Email", 1, "Admin_Users") + randNo + ".com";
		String userRole = sheet.getSingleCellData("Role", 1, "Admin_Users");
		String passwordSettingOption = sheet.getSingleCellData("PasswordOption", 2, "Admin_Users");
		String password = sheet.getSingleCellData("Password", 1, "Admin_Users");
		String confPassword = sheet.getSingleCellData("ConfirmPassword", 1, "Admin_Users");

		try {

			String[] userDetails = { userEmail, "", userRole, "" };

			admin_UsersPage.clickOnCreateUserButton();
			admin_UsersPage.enterName(username);
			admin_UsersPage.enterEmail(userEmail);
			admin_UsersPage.selectUsersRoles(userRole);
			admin_UsersPage.selectPasswordSettingRadioButton(passwordSettingOption);
			admin_UsersPage.enterNewUserPassword(password, confPassword);
			admin_UsersPage.clickOnSaveButton();
			admin_UsersPage.verifyUserIsSaved(username);
			admin_UsersPage.verifyUserDetailsOnUsersPage(username, userDetails);

			admin_UsersPage.deleteExistingUser(username);

		} catch (Error e) {
			captureScreenshot("testUserIsNotAbleToLoginIntoApplicationWhenUserProfileIsDeleted");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsNotAbleToLoginIntoApplicationWhenUserProfileIsDeleted");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

				loginPage.logoutUserFromApplication();
				loginPage.loginIntoApplication(username, password);

			} catch (Error e) {
				ExecutionLog.logErrorMessage(e);
				throw e;
			} catch (Exception e) {
				ExecutionLog.logExceptionMessage(e);
				throw e;
			}
		}
	}

	@TestCaseInfo(testCaseID = "245", title = "Verify User is able to filter by  Name filed with contains conditions")
	@Test(priority = 2, groups = { "Functional" })
	public void testUserisAbleToFilterByNameFieldWithContainsConditions() throws Exception {
		String fieldName = "Name";
		String optionName = "Contains";
		String keyWord = "admin";

		try {

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, optionName);
			filterDialogPage.enterValueInInputField(fieldName, keyWord);
			filterDialogPage.clickOnOkButton();
			admin_UsersPage.assertValuesInColumns(fieldName, keyWord);

		} catch (Error e) {
			captureScreenshot("testUserisAbleToFilterByNameFieldWithContainsConditions");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserisAbleToFilterByNameFieldWithContainsConditions");
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

	@TestCaseInfo(testCaseID = "246", title = "Verify User is able to click on checkbox to 'Disable' to that User and cross mark should be displayed on User page list")
	@Test(priority = 2, groups = { "Functional" })
	public void testUserIsAbleDisabledUserAndCrossSignDisplayedWithUser() throws Exception {

		String username = sheet.getSingleCellData("Name", 1, "Admin_Users") + randNo;
		String userEmail = sheet.getSingleCellData("Email", 1, "Admin_Users") + randNo + ".com";
		String userRole = sheet.getSingleCellData("Role", 1, "Admin_Users");
		String passwordSettingOption = sheet.getSingleCellData("PasswordOption", 2, "Admin_Users");
		String password = sheet.getSingleCellData("Password", 1, "Admin_Users");
		String confPassword = sheet.getSingleCellData("ConfirmPassword", 1, "Admin_Users");

		try {

			String[] userDetails = { userEmail, "", userRole, "" };

			admin_UsersPage.clickOnCreateUserButton();
			admin_UsersPage.enterName(username);
			admin_UsersPage.enterEmail(userEmail);
			admin_UsersPage.selectUsersRoles(userRole);
			admin_UsersPage.selectPasswordSettingRadioButton(passwordSettingOption);
			admin_UsersPage.enterNewUserPassword(password, confPassword);
			admin_UsersPage.clickOnSaveButton();
			admin_UsersPage.verifyUserIsSaved(username);
			admin_UsersPage.verifyUserDetailsOnUsersPage(username, userDetails);

			admin_UsersPage.editAndDisableExistingUser(username);
			admin_UsersPage.clickOnSaveButton();

			admin_UsersPage.assertUserIsEnabledOrDisabled(username, false);

		} catch (Error e) {
			captureScreenshot("testUserIsAbleDisabledUserAndCrossSignDisplayedWithUser");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testUserIsAbleDisabledUserAndCrossSignDisplayedWithUser");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} finally {
			try {

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

	@TestCaseInfo(testCaseID = "254", title = "Verify application allows to filter User by Role dropdown condition")
	@Test(priority = 2, groups = { "Functional" })
	public void testApplicationAllowToFilterUserByRoleCondition() throws Exception {
		String fieldName = "Role";
		String optionName = "Administrator";
		String keyWord = "Administrator";

		try {

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, optionName);
			filterDialogPage.clickOnOkButton();
			filterDialogPage.assertNumberOnFilterButton(1);
			admin_UsersPage.assertValuesInColumns(fieldName, keyWord);

		} catch (Error e) {
			captureScreenshot("testApplicationAllowToFilterUserByRoleCondition");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testApplicationAllowToFilterUserByRoleCondition");
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

	@TestCaseInfo(testCaseID = "255", title = "Verify application allows to filter User by Email with 'Contains, or 'Equal' or 'Start with' dropdown condition")
	@Test(priority = 2, groups = { "Functional" })
	public void testApplicationAllowToFilterUserByEmailDropdownCondition() throws Exception {
		String fieldName = "Email";
		String optionName = "Contains";
		String keyWord = "nakhter";

		try {

			filterDialogPage.clickOnFilterButton();
			filterDialogPage.selectOptionFromDropdown(fieldName, optionName);
			filterDialogPage.enterValueInInputField(fieldName, keyWord);
			filterDialogPage.clickOnOkButton();
			filterDialogPage.assertNumberOnFilterButton(1);

		} catch (Error e) {
			captureScreenshot("testApplicationAllowToFilterUserByEmailDropdownCondition");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testApplicationAllowToFilterUserByEmailDropdownCondition");
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

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() throws Exception {
		destroyBrowser();
	}
}
