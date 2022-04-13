package pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import locators.Admin_ApplicationLogLocators;
import locators.Admin_UsersLocators;
import utility.ConstantsUtil;
import utility.DriverPage;

public class Admin_UsersPage extends DriverPage {

	public Admin_UsersPage(WebDriver webdriver) {
		super(webdriver);
	}

	public void selectAdminUsersMenu() {
		putWait(ConstantsUtil.minWait);
		selectTopMenu("Administration");
		selectSideMenu("Manage", "Users");
		putWait(ConstantsUtil.minWait);

	}

	public void clickOnCreateUserButton() {
		putWait(ConstantsUtil.minWait);
		clickOn(Admin_UsersLocators.createUserBtn);
		putWait(ConstantsUtil.minWait);
		log.log("Clicked on create alert button");
	}

	public void enterName(String name) {
		sendKeys(Admin_UsersLocators.nameField, name);
		log.log("User enters name : " + name);
	}

	public void enterEmail(String email) {
		sendKeys(Admin_UsersLocators.emailField, email);
		log.log("User enters email : " + email);
	}

	public void selectUsersRoles(String roles) {
		clickOn(Admin_UsersLocators.roleDropdownField);
		putWait(ConstantsUtil.oneMinWait);
		clickOn(Admin_UsersLocators.roleDropdownOption.replaceAll("@override", roles));
		putWait(ConstantsUtil.oneMinWait);
		log.log("User selected roles for new user : " + roles);
	}

	public void selectPasswordSettingRadioButton(String option) {
		clickOn(Admin_UsersLocators.passwordOption.replaceAll("@override", option));
		putWait(ConstantsUtil.oneMinWait);
		log.log("User selected password option : " + option);
	}

	public void clickOnSaveButton() {
		clickOn(Admin_UsersLocators.saveBtn);
		putWait(ConstantsUtil.oneMinWait);
		log.log("User clicked on Save button");
	}

	public void clickOnCancelButton() {
		clickOn(Admin_UsersLocators.cancelBtn);
		putWait(ConstantsUtil.oneMinWait);
		log.log("User clicked on Cancel button");
	}

	public void verifyUserIsSaved(String userName) {
		isElementPresent("//*[text()='" + userName + "']");
		log.log("User is verified.");
	}

	public void createAUser(String name, String email, String role, String passSetting) {
		clickOnCreateUserButton();
		enterName(name);
		enterEmail(email);
		selectUsersRoles(role);
		selectPasswordSettingRadioButton(passSetting);
		clickOnSaveButton();
		verifyUserIsSaved(name);
	}

	public void deleteExistingUser(String userName) {
		String deleteloc = Admin_UsersLocators.deleteUser.replaceAll("@override", userName);
		clickOn(deleteloc);
		waitForElementPresent(Admin_UsersLocators.deleteOKButton, 10);
		log.log("Clicked on delete button to delete User");
		putWait(ConstantsUtil.minWait);
		clickOn(Admin_UsersLocators.deleteOKButton);
		putWait(ConstantsUtil.minWait);
	}

	public void editExistingUser(String existingUserName, String newUserName, String role) {
		String editLoc = Admin_UsersLocators.editUser.replaceAll("@override", existingUserName);
		waitForElementPresent(editLoc, 10);
		clickOn(editLoc);
		waitForElementPresent(Admin_UsersLocators.nameField, 10);
		log.log("Clicked on edit button to edit User");
		putWait(ConstantsUtil.minWait);
		enterName(newUserName);
		selectUsersRoles(role);
		clickOnSaveButton();
		putWait(ConstantsUtil.minWait);
		verifyUserIsSaved(newUserName);
	}

	public void editAndDisableExistingUser(String existingUserName) {
		String editLoc = Admin_UsersLocators.editUser.replaceAll("@override", existingUserName);
		waitForElementPresent(editLoc, 10);
		clickOn(editLoc);
		waitForElementPresent(Admin_UsersLocators.nameField, 10);
		log.log("Clicked on edit button to edit User");
		putWait(ConstantsUtil.minWait);
		clickOn(Admin_UsersLocators.disabledCheckBox);
		putWait(ConstantsUtil.minWait);
		log.log("Checkbox has been checked");
	}

	public void editAndEnableExistingUser(String existingUserName) {
		String editLoc = Admin_UsersLocators.editUser.replaceAll("@override", existingUserName);
		waitForElementPresent(editLoc, 10);
		clickOn(editLoc);
		waitForElementPresent(Admin_UsersLocators.nameField, 10);
		log.log("Clicked on edit button to edit User");
		putWait(ConstantsUtil.minWait);
		clickOn(Admin_UsersLocators.disabledCheckBox);
		putWait(ConstantsUtil.minWait);
		log.log("Checkbox has been checked");
	}

	public void clickOnManagerOfCheckboxes(String[] teamMemberName) {

		for (int i = 0; i <= teamMemberName.length - 1; i++) {
			String managerCheckbox = Admin_UsersLocators.managerOfCheckbox.replaceAll("@override", teamMemberName[i]);
			clickOn(managerCheckbox);
			putWait(ConstantsUtil.minWait);
			log.log("User checked member : " + teamMemberName[i]);
		}
	}

	public void verifyUserDetailsOnUsersPage(String usersName, String[] userDetails) {

		String no = driver.findElement(By.xpath("//td[text()='" + usersName + "']/..")).getAttribute("aria-rowindex");

		List<WebElement> ele = driver.findElements(By.xpath("//tr[@aria-rowindex='" + no + "']/td"));

		for (int i = 1; i <= ele.size() - 2; i++) {
			String actualValues = ele.get(i).getText();

			String expectedValue = null;
			for (int j = 0; j <= i; j++) {

				if (j == i - 1) {
					expectedValue = userDetails[j];

				}
			}

			Assert.assertEquals(actualValues, expectedValue);
		}
	}

	public void clickOnSetPasswordButton(String existingUserName) {
		String editLoc = Admin_UsersLocators.setPassword.replaceAll("@override", existingUserName);
		waitForElementPresent(editLoc, 10);
		clickOn(editLoc);
		putWait(ConstantsUtil.minWait);
	}

	public void setPasswordForExistingUser(String existingUserName, String password, String confirmPassword) {
		clickOnSetPasswordButton(existingUserName);
		waitForElementPresent(Admin_UsersLocators.passwordField, 10);
		log.log("Clicked on edit button to edit User");
		sendKeys(Admin_UsersLocators.passwordField, password);
		sendKeys(Admin_UsersLocators.confPasswordField, confirmPassword);
		clickOnSaveButton();
		putWait(ConstantsUtil.mediumWait);
	}

	public void enterNewUserPassword(String password, String confirmPassword) {
		sendKeys(Admin_UsersLocators.newUserPasswordField, password);
		sendKeys(Admin_UsersLocators.newUserConfPasswordField, confirmPassword);
		putWait(ConstantsUtil.oneMinWait);
	}

	public void assertPasswordNotMetCriteria() {
		boolean editLoc = driver.findElement(By.xpath(Admin_UsersLocators.passNotMetMsg)).isDisplayed();
		assertTrue(editLoc);
		putWait(ConstantsUtil.minWait);
	}

	public void assertUserIsEnabledOrDisabled(String existingUserName, boolean status) {
		String no = driver.findElement(By.xpath("//td[text()='" + existingUserName + "']/.."))
				.getAttribute("aria-rowindex");

		if (status == true) {
			boolean enableStatus = driver
					.findElement(By.xpath(Admin_UsersLocators.userEnabledCross.replaceAll("@override", no)))
					.isDisplayed();
			assertTrue(enableStatus);
			log.log("Verfied user is enabled");
		} else {
			boolean disableStatus = driver
					.findElement(By.xpath(Admin_UsersLocators.userDisabledCross.replaceAll("@override", no)))
					.isDisplayed();
			assertTrue(disableStatus);
			log.log("Verfied user is disabled");
		}
		putWait(ConstantsUtil.minWait);
	}

	public void assertValuesInColumns(String columnName, String expectedColumnValue) {
		putWait(ConstantsUtil.oneMinWait);
		String actualValue = null;
		List<WebElement> ele = null;
		String[] colNames = { "Name", "Email", "User", "Role", "Enable", "Actions" };

		for (int i = 0; i <= colNames.length - 1; i++) {

			String colmName = colNames[i];

			if (columnName.equals(colmName)) {
				int colNo = i + 1;
				ele = driver.findElements(By.xpath(
						Admin_ApplicationLogLocators.columnEleLoc.replaceAll("@override", String.valueOf(colNo))));

				for (int j = 0; j <= ele.size() - 1; j++) {
					actualValue = ele.get(i).getText();
					Assert.assertTrue(actualValue.contains(expectedColumnValue));
					log.log("Verified that '" + actualValue + "' contains : '" + expectedColumnValue
							+ "' under the column : '" + columnName);
				}

			}
		}
	}

}
