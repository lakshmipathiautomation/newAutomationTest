package locators;

public class Admin_ApplicationLogLocators {

	public static final String appLog = "//span[@class='k-in  ']/div[text()='Application Log']";
	public static final String titleOfPage = "//div[@class='topBarTitle']";
	public static final String refreshButton = "//div/button[@class='topBarButton' and @title='Refresh Data']";
	public static final String exportButton = "//button[@class='topBarButton']/i[@class='fas fa-file-export']";
	public static final String headerForSort = "//*[@class='k-column-title' and text()='@override']";
	public static final String filterButton = "//button[@class='topBarButton']/i[contains(@class, 'filter')]";
	public static final String conditionDropdown = "//label[text()='@override']/following-sibling::div//span[@class='k-input']";
	public static final String dropdownOption = "//li[text()='@override']";
	public static final String inputField = "//label[text()='@override']/..//input";
	public static final String okButton = "//button[text()='OK']";
	public static final String cancelButton = "//button[text()='Cancel']";
	public static final String clearButton = "//button[text()='Clear']";
	public static final String noOfFilterToolitip = "//div[@id='filterTooltip']//div";
	public static final String closeExportPopup = "//button[text()='Close']";
	public static final String columnEleLoc = "//div[@class='k-grid-container']//td[@aria-colindex='@override']";
	public static final String exportIcon="//a[@title='Export' and contains(@class, 'menu-link')]";
	public static final String downloadInAnyFormat="//*[@id='trv-main-menu-export-format-list']//span[text()='@override']";

}
