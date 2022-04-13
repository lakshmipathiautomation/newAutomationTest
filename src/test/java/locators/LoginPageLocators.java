package locators;

public class LoginPageLocators {

    public static final String userName = "//input[@type='email']";
    public static final String password = "//input[@type='password']";
    public static final String loginButton = "//button[text()='Log In']";
    public static final String logedInUser = "//div[@class='top-userEmail' and text()='@override']";
    public static final String welcomePopUpCheckbox = "//label[text()=' Do not show this window again after login']/preceding-sibling::input";
    public static final String closeButton = "//button[text()='Close']";
}
