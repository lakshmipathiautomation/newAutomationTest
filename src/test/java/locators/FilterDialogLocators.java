package locators;

public class FilterDialogLocators {
	
	public static final String filterBtn = "//button[@class='topBarButton']/i[contains(@class, 'filter')]";
	public static final String conditionDropdown = "//label[text()='@override']/following-sibling::div//span[@class='k-input']";
	public static final String dropDownOption = "//li[text()='@override']";
	public static final String inputFields = "//label[text()='@override']/..//input";
	public static final String okButtonOnFileterDlg = "//button[text()='OK']";
	public static final String cancelButtonOnFileterDlg = "//button[text()='Cancel']";
	public static final String clearButtonOnFileterDlg = "//button[text()='Clear']";
	public static final String numberOfFilterToolTip = "//div[@id='filterTooltip']//div";
		

}
