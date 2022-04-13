package pages;

import locators.Admin_AlertsLocators;
import locators.FilterDialogLocators;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utility.ConstantsUtil;
import utility.DriverPage;

public class Admin_AlertsPage extends DriverPage {

	public Admin_AlertsPage(WebDriver driver) {
		super(driver);
	}

	public void selectAdminMenu() {
		selectTopMenu("Administration");
		putWait(ConstantsUtil.mediumWait);
	}

	public void clickOnCreateAlertButton() {
		clickOn(Admin_AlertsLocators.createAlertBtn);
		putWait(ConstantsUtil.mediumWait);
		log.log("Clicked on create alert button");
	}

	public void selectType(String type) {
		clickOn(Admin_AlertsLocators.selectType);
		putWait(ConstantsUtil.minWait);
		getWebDriver().findElement(By.xpath("//li[text()='" + type + "']")).click();

	}

	public void enterAlertName(String name) {
		putWait(ConstantsUtil.oneMinWait);
		sendKeys(Admin_AlertsLocators.alertName, name);
		log.log("Alert name entered.");
	}

	public void doubleClickedOnAlertNameField() {
		doubleClick(Admin_AlertsLocators.alertName);
		putWait(ConstantsUtil.oneMinWait);
		log.log("User double clicked on name field ");
	}

	public void selectSeverity(String severity) {
		clickOn(Admin_AlertsLocators.severity);
		putWait(ConstantsUtil.minWait);
		getWebDriver().findElement(By.xpath("//li[text()='" + severity + "']")).click();
	}

	public void sendEmailTo(String email) {
		clickOn(Admin_AlertsLocators.emailCheckbox);
		sendKeys(Admin_AlertsLocators.sendEmailTo, email);
		log.log("Email entered.");
	}

	public void clickOnSaveButton() {
		putWait(ConstantsUtil.minWait);
		clickUsingJavaScriptExecutor(Admin_AlertsLocators.saveButton);
		log.log("Clicked on save button");
		putWait(ConstantsUtil.minWait);
	}

	public void verifyAlertIsSaved(String alertName) {
		isElementPresent("//*[text()='" + alertName + "']");
		log.log("Alert is verified.");
	}

	public void enterFileFolder(String file) {
		sendKeys(Admin_AlertsLocators.fileFolder, file);
		log.log("Enter file/folder");
	}

	public void enterRunScriptInAgent(String script) {
		sendKeys(Admin_AlertsLocators.runScriptInAgent, script);
		log.log("Entered run script agent");
	}

	public void selectOperation(String ope) {
		clickOn(Admin_AlertsLocators.operation);
		putWait(ConstantsUtil.minWait);
		getWebDriver().findElement(By.xpath("//li[text()='" + ope + "']")).click();
		log.log("Operation is selected asf " + ope);
	}

	public void verifySavedAlertDetails(String alertName, String[] alertDetails) {

		List<WebElement> ele = driver.findElements(By.xpath("//*[text()='" + alertName + "']/following-sibling::td/*"));
		for (int i = 0; i <= ele.size() - 1; i++) {
			String actualValues = ele.get(i).getText();

			String expectedValue = null;
			for (int j = 0; j <= i; j++) {
				expectedValue = alertDetails[j];
			}
			Assert.assertEquals(actualValues, expectedValue);
		}
	}

	public void clickonHeaderNameToSortList(String headerName) {
		String headerLoc = Admin_AlertsLocators.headerForSort.replaceAll("@override", headerName);
		clickOn(headerLoc);
		log.log("Clicked on Heade of Column : " + headerName);
		putWait(ConstantsUtil.minWait);
	}

	public void selectIntegrationType(String integrationType) {
		putWait(ConstantsUtil.oneMinWait);
//		clickOn(Admin_AlertsLocators.integrationType);
		clickUsingJavaScriptExecutor(Admin_AlertsLocators.integrationType);
		putWait(ConstantsUtil.minWait);
		getWebDriver().findElement(By.xpath("//li[text()='" + integrationType + "']")).click();

	}

	public void assertAppliedFilterInFilterButtonTooltip(String[] filterValue) {

		mouseOver(FilterDialogLocators.filterBtn);
		putWait(ConstantsUtil.mediumWait);

		List<WebElement> ele = driver.findElements(By.xpath(Admin_AlertsLocators.filterInTooltip));

		for (int i = 0; i <= ele.size() - 1; i++) {
			String actualValues = ele.get(i).getText();

			String expectedValue = null;
			for (int j = 0; j <= i; j++) {
				expectedValue = filterValue[j];
			}
			Assert.assertEquals(actualValues, expectedValue);
		}
	}

	
	public void assertValuesInColumns(String columnName, String expectedColumnValue) {
		putWait(ConstantsUtil.oneMinWait);
		String actualValue = null;
		List<WebElement> ele = null;
		String[] colNames = { "Name", "Type", "Integration", "Severity" };

		for (int i = 0; i <= colNames.length - 1; i++) {

			String colmName = colNames[i];

			if (columnName.equals(colmName)) {
				int colNo = i + 1;
				ele = driver.findElements(By.xpath("//div[@class='k-grid-container']//td[@aria-colindex='" + colNo + "']"));

				for (int j = 0; j <= ele.size() - 1; j++) {
					actualValue = ele.get(i).getText();
					Assert.assertTrue(actualValue.contains(expectedColumnValue));
					log.log("Verified that '" + actualValue + "' contains : '" + expectedColumnValue + "' under the column : '" + columnName);
				}

			}
		}
	}
	
	public boolean assertNoRecordFound() {
		boolean status = isElementPresent(Admin_AlertsLocators.noReocrdFound);
		return status;
	}

	public void editExistingAlertName(String alertExistingName, String alertNewName) {
		String existingAlert = Admin_AlertsLocators.editAlert.replaceAll("@override", alertExistingName);
		clickOn(existingAlert);
		log.log("Clicked on edit button on alert");
		putWait(ConstantsUtil.oneMinWait);
		enterAlertName(alertNewName);
		clickOnSaveButton();
		verifyAlertIsSaved(alertNewName);
	}

	public void deleteExistingAlert(String alertName) {
		String alertloc = Admin_AlertsLocators.deleteAlert.replaceAll("@override", alertName);
		clickOn(alertloc);
		waitForElementPresent(Admin_AlertsLocators.deleteOKButton, 10);
		log.log("Clicked on delete button to delete alert");
		putWait(ConstantsUtil.minWait);
		clickOn(Admin_AlertsLocators.deleteOKButton);
		putWait(ConstantsUtil.mediumWait);
	}
}
