package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import locators.Admin_AlertsLocators;
import locators.Admin_Manage_Endpoint_Locators;
import utility.ConstantsUtil;
import utility.DriverPage;

public class Admin_Manage_EndpointPage extends DriverPage {

	public Admin_Manage_EndpointPage(WebDriver webdriver) {
		super(webdriver);
	}

	public void selectAdminEndpointMenu() {
		putWait(ConstantsUtil.minWait);
		selectTopMenu("Administration");
		selectSideMenu("Manage", "Endpoints");
		putWait(ConstantsUtil.minWait);

	}

	public void verifyHeaderName() {
		putWait(ConstantsUtil.minWait);
		clickOn(Admin_Manage_Endpoint_Locators.pageHeaderName);
		log.log("Hader name is displaying on top left corner of page ");

	}
	public void getButtonNameOnPage() {
		String filter=Admin_Manage_Endpoint_Locators.filterBtn;
		log.log("filter Button is present " + isElementPresent(filter));
		String refresh=Admin_Manage_Endpoint_Locators.refeshButton;
		log.log("refresh button is present " + isElementPresent(refresh));
		String export=Admin_Manage_Endpoint_Locators.exportButton;
		log.log("export button is present" + isElementPresent(export));
		
	}

	public void clikOnRefreshButton() {
		clickOn(Admin_Manage_Endpoint_Locators.refeshButton);
		putWait(ConstantsUtil.minWait);
		

	}

	public void clickOnExportButton() {
		clickOn(Admin_Manage_Endpoint_Locators.exportButton);
		putWait(ConstantsUtil.minWait);
		clickOn(Admin_Manage_Endpoint_Locators.closePopup);
		log.log("Export poup has been opened");
	}

	public void clickOnDropdownArrowAndSelectOptionName(String machineName, String optionName) {
		String clickonArrow = Admin_Manage_Endpoint_Locators.dropdownMenuRow.replaceAll("@override", machineName);
		clickOn(clickonArrow);
		String menuItem=Admin_Manage_Endpoint_Locators.nameOfDropdownMenueValue.replaceAll("@override", optionName);
		clickOn(menuItem);
		putWait(ConstantsUtil.minWait);
		log.log("select option from dropdown menue");
	}

	public void clickOnGetButtonOnDropdownMenuPopup() {
		putWait(ConstantsUtil.minWait);
		clickOn(Admin_Manage_Endpoint_Locators.getButtonOnDropdownPopup);
		putWait(ConstantsUtil.oneMinWait);
		clickOn(Admin_Manage_Endpoint_Locators.closePopup);
		log.log("Get button has been clicked and click on close button to close the popup");
	}

	public void clickOnRefeshButtonOnDropdownMenuPopup() {
		putWait(ConstantsUtil.minWait);
		clickOn(Admin_Manage_Endpoint_Locators.refreshButtonOnDropdownPopup);
		putWait(ConstantsUtil.oneMinWait);
		clickOn(Admin_Manage_Endpoint_Locators.closePopup);
		log.log("Refesh button has been clicked and click on close button to close the popup ");
	}

	public void clikOnEditButton(String machineName) {
		String editButton = Admin_Manage_Endpoint_Locators.editEndpoint.replaceAll("@override", machineName);
		clickOn(machineName);

	}

	public void enterValueInAliasInputField(String fieldValue) {
		String aName = Admin_Manage_Endpoint_Locators.aliasName;
		sendKeys(aName, fieldValue);
		putWait(ConstantsUtil.minWait);
		clickOn(Admin_Manage_Endpoint_Locators.saveButton);
		log.log("Alias name has been Edited ");
	}

	public void clickOnDeleteButton(String machineName) {
		String deletemachine = Admin_Manage_Endpoint_Locators.deleteEndpoint.replaceAll("@override", machineName);
		clickOn(deletemachine);
		clickOn(Admin_Manage_Endpoint_Locators.clickOnOKButton);
		putWait(ConstantsUtil.minWait);
		log.log("machine name will be deleted");
	}

	public void verifyAliasNameIsSaved(String alisName) {
		isElementPresent("//td[text()='" + alisName + "']");
		log.log("yes alias name is present");
	}
	public void verifyColumnHeaderName(String headerName) {
		String hName=Admin_Manage_Endpoint_Locators.columnHeadeName.replaceAll("@override", headerName);
		putWait(ConstantsUtil.minWait);
		log.log("Column Name are " + getText(hName));
		
	}
	
	public void assertNameOfColumns(String columnName, String expectedColumnValue) {
		putWait(ConstantsUtil.oneMinWait);
		String actualValue = null;
		List<WebElement> ele = null;
		String[] colNames = { "Machine Name", "Alias", "Public IP", "Local IP","Version","Last Connection","Actions" };

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
	
	public void clickonHeaderNameToSortList(String headerName) {
		String headerLoc = Admin_Manage_Endpoint_Locators.columnHeadeName.replaceAll("@override", headerName);
		clickOn(headerLoc);
		log.log("Clicked on Heade of Column : " + headerName);
		putWait(ConstantsUtil.minWait);
	}


}
