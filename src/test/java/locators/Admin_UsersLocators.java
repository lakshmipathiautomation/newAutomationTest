package locators;

public class Admin_UsersLocators {

    public static final String createUserBtn = "//button[text()='Create']";
    public static final String nameField = "//input[@id='form-name-field']";
    public static final String emailField = "//input[@id='form-email-field']";
    public static final String roleDropdownField = "//label[text()='Role']/../div//span[contains(@class, 'k-dropdown-wrap')]";
    public static final String roleDropdownOption = "//li[text()='@override']";
    public static final String passwordOption = "//label[text()='@override']/../input";
    public static final String saveBtn = "//button[text()='Save']";
    public static final String cancelBtn = "//button[text()='Cancel']";
    public static final String deleteUser = "//td[text()='@override']/following-sibling::td//button[text()='Delete']";
    public static final String deleteOKButton = "//button[text()='OK']";
    public static final String editUser = "//td[text()='@override']/following-sibling::td//button[text()='Edit']";
    public static final String managerOfCheckbox = "//label[text()='Manager of']/following-sibling::div//label[contains(text(), '@override')]/input";
    public static final String setPassword = "//td[text()='@override']/following-sibling::td//button[text()='Set Password']";
    public static final String passwordField = "//input[@id='P']";
    public static final String confPasswordField = "//input[@id='CP']";
    public static final String passNotMetMsg = "//span[@class='custom-error' and text()='Password complexity requirements not met.']";
    public static final String disabledCheckBox="//input[@type='checkbox']";
    public static final String newUserPasswordField = "//label[text()='Password']/following-sibling::div/input";
    public static final String newUserConfPasswordField = "//label[text()='Confirm Password']/following-sibling::div/input";
    public static final String userDisabledCross = "//*[@aria-rowindex='@override']//i[contains(@class, 'fa-times')]";
    public static final String userEnabledCross = "//*[@aria-rowindex='@override']//i[contains(@class, 'fa-check')]";
}
