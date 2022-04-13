package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import locators.Admin_ApplicationLogLocators;
import utility.ConstantsUtil;
import utility.DriverPage;

public class Admin_ApplicationLogPage extends DriverPage {

	public Admin_ApplicationLogPage(WebDriver driver) {
		super(driver);
	}

	public void selectAppLogMenu() {
		selectTopMenu("Administration");
		selectSideMenu("Manage", "Application Log");
		putWait(ConstantsUtil.minWait);

	}

	public void clickOnFilterButton() {
		clickOn(Admin_ApplicationLogLocators.filterButton);
		putWait(ConstantsUtil.minWait);
		log.log("User has clicked on Filter button");
	}

	public void selectOptionFromDropdown(String fieldName, String optionName) {
		String field = Admin_ApplicationLogLocators.conditionDropdown.replaceAll("@override", fieldName);
		clickOn(field);
		putWait(ConstantsUtil.minWait);
		log.log("User has clicked on dropdown field" + field);
		String option = Admin_ApplicationLogLocators.dropdownOption.replaceAll("@override", optionName);
		clickOn(option);
		log.log("User has select dropdown option" + option);
		putWait(ConstantsUtil.minWait);

	}

	public void enterValueInInputField(String fieldName, String keyWord) {
		String field = Admin_ApplicationLogLocators.inputField.replaceAll("@override", fieldName);
		sendKeys(field, keyWord);
		putWait(ConstantsUtil.minWait);
		log.log("user has entered the keyword " + keyWord);

	}

	public void clickOnOKButton() {
		clickOn(Admin_ApplicationLogLocators.okButton);
		putWait(ConstantsUtil.minWait);
		log.log(" clicked on OK button to apply filter ");
	}

	public void clickOnCancelButton() {
		clickOn(Admin_ApplicationLogLocators.cancelButton);
		putWait(ConstantsUtil.minWait);
		log.log(" Clicked on Cancel Button to close the filter popup");
	}

	public void clickOnClearButton() {
		clickOn(Admin_ApplicationLogLocators.clearButton);
		putWait(ConstantsUtil.minWait);
		log.log("Clicked on clear button  to reset default value on filter popup ");
	}

	public void assertnumberoffilterbuttonOnTooltipicon(int noOfFilter) {
		int noOfToolTips = Integer.parseInt(getText(Admin_ApplicationLogLocators.noOfFilterToolitip));
		Assert.assertEquals(noOfToolTips, noOfFilter);
		putWait(ConstantsUtil.minWait);
		log.log("number of tool tips" + noOfToolTips);

	}

	public void verifyLogDetailsOnApplicationLogPage(String objectName, String[] logDetails) {

		String no = driver.findElement(By.xpath("//td[text()='" + objectName + "']/..")).getAttribute("aria-rowindex");

		List<WebElement> ele = driver.findElements(By.xpath("//tr[@aria-rowindex='" + no + "']/td"));

		for (int i = 1; i <= ele.size() - 1; i++) {
			String actualValues = ele.get(i).getText();

			String expectedValue = null;
			for (int j = 0; j <= i; j++) {

				if (j == i - 1) {
					expectedValue = logDetails[j];
				}
			}

			Assert.assertEquals(actualValues, expectedValue);
		}
	}

	public void assertValuesInColumns(String columnName, String expectedColumnValue) {
		putWait(ConstantsUtil.oneMinWait);
		String actualValue = null;
		List<WebElement> ele = null;
		String[] colNames = { "Date", "Object Type", "Object", "Action", "User", "Details" };

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
	

	public void clickOnRefreshButton() {
		clickOn(Admin_ApplicationLogLocators.refreshButton);
		putWait(ConstantsUtil.minWait);
		log.log("Page has been refreshed and display new one if any ");
	}

	public void clickOnExportButton() {
		clickOn(Admin_ApplicationLogLocators.exportButton);
		putWait(ConstantsUtil.minWait);
		log.log("User clicked on export button");

	}

	public void clickOnCloseButtonOnExportPopUp() {
		clickOn(Admin_ApplicationLogLocators.closeExportPopup);
		putWait(ConstantsUtil.minWait);

	}
   
	public void downloadExportedFileEventsInSelectedFormats(String formatsName) {
		clickOnExportButton();
		WaitForElementEnabled(Admin_ApplicationLogLocators.exportIcon, 50);
		mouseOver(Admin_ApplicationLogLocators.exportIcon);
		log.log("User mouse hover on the export button");
		putWait(ConstantsUtil.minWait);
		String formats = Admin_ApplicationLogLocators.downloadInAnyFormat.replaceAll("@override", formatsName);
		clickOn(formats);
		log.log("User selected the download format" + formats);
		putWait(ConstantsUtil.minWait);
		log.log("Export Page should be download in suggested formats");
	}

}
