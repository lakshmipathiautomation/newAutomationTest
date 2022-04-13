package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import locators.FilterDialogLocators;
import utility.ConstantsUtil;
import utility.DriverPage;

public class FilterDialogPage extends DriverPage {

	public FilterDialogPage(WebDriver webdriver) {
		super(webdriver);
	}

	public void clickOnFilterButton() {
		clickOn(FilterDialogLocators.filterBtn);
		putWait(ConstantsUtil.minWait);
		log.log("Clicked on Filter Icon");
	}

	public void selectOptionFromDropdown(String fieldName, String optionName) {
		String field = FilterDialogLocators.conditionDropdown.replaceAll("@override", fieldName);
		clickOn(field);
		putWait(ConstantsUtil.oneMinWait);
		log.log("User has clicked on dropdown field: " + field);
		String options = FilterDialogLocators.dropDownOption.replaceAll("@override", optionName);
		clickOn(options);
		log.log("User has selected dropdown option: " + options);
		putWait(ConstantsUtil.oneMinWait);
	}

	public void enterValueInInputField(String fieldName, String keyword) {
		String field = FilterDialogLocators.inputFields.replaceAll("@override", fieldName);
		sendKeys(field, keyword);
		putWait(ConstantsUtil.minWait);
		log.log("User has clicked on dropdown field: " + field);
	}
	
	public void clickOnOkButton() {
		clickOn(FilterDialogLocators.okButtonOnFileterDlg);
		putWait(ConstantsUtil.minWait);
		log.log("Clicked on 'OK' button at Filter Dialog");
	}
	
	public void clickOnCancelButton() {
		clickOn(FilterDialogLocators.cancelButtonOnFileterDlg);
		putWait(ConstantsUtil.minWait);
		log.log("Clicked on 'Cancel' button at Filter Dialog");
	}
	
	public void clickOnClearButton() {
		clickOn(FilterDialogLocators.clearButtonOnFileterDlg);
		putWait(ConstantsUtil.minWait);
		log.log("Clicked on 'CLEAR' button at Filter Dialog");
		
	}
		
	public void assertNumberOnFilterButton(int numberOfFilter) {
		int noOfToolTip= Integer.parseInt(getText(FilterDialogLocators.numberOfFilterToolTip));
		Assert.assertEquals(noOfToolTip, numberOfFilter);
		putWait(ConstantsUtil.minWait);
		log.log("User verified number of applied filter : " + noOfToolTip);
	}
	
}
