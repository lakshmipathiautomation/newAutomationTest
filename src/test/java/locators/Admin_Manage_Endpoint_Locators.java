package locators;

public class Admin_Manage_Endpoint_Locators {
	
	public static final String pageHeaderName = "//div[@class='topBar']/child::div[text()='Endpoints']";
	public static final String refeshButton = "//button[@title='Refresh Data']";
	public static final String exportButton = "//button[@class='topBarButton']/i[@class='fas fa-file-export'] ";
	public static final String closePopup = "//button[text()='Close']";
	public static final String columnHeadeName = "//*[@class='k-column-title' and text()='@override']";
	public static final String editEndpoint = "//td[text()='@override']/following-sibling::td/button[text()='Edit']";
	public static final String deleteEndpoint = "//td[text()='@override']/following-sibling::td/button[text()='Delete']";
	public static final String aliasName = "//label[text()='Alias']/..//input";
	public static final String saveButton = "//button[text()='Save']";
	public static final String dropdownMenuRow = "//td[text()='@override']/following-sibling::td//div[@id='dropdownMenuButton']/i";
	public static final String nameOfDropdownMenueValue="//div[@class='dropdown-menu show']//button[text()='@override']";
	public static final String getButtonOnDropdownPopup = "//button[text()='Get']";
	public static final String refreshButtonOnDropdownPopup = "//button[text()='Refresh']";
	public static final String clickOnOKButton="//button[text()='OK']";
	public static final String filterBtn = "//button[@class='topBarButton']/i[contains(@class, 'filter')]";

}
