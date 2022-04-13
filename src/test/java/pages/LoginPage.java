package pages;

import locators.LoginPageLocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utility.ConstantsUtil;
import utility.DriverPage;
import utility.ExecutionLog;
import utility.PropertyReader;

public class LoginPage extends DriverPage {


    PropertyReader prop = new PropertyReader();

    public LoginPage(WebDriver webdriver){
        super(webdriver);
    }

    public void enterUserName(String username){
        putWait(ConstantsUtil.minWait);
        sendKeys(LoginPageLocators.userName, username);
        log.log("Entered username as "+username);
    }

    public void enterPassword(String password){
        sendKeys(LoginPageLocators.password, password);
        log.log("Entered password as "+password);
    }

    public void clickOnLoginButton(){
        clickOn(LoginPageLocators.loginButton);
        putWait(ConstantsUtil.minWait);
        log.log("Clicked on login button");
    }

    public void verifyLogin(String username){
        String sideloc = LoginPageLocators.logedInUser.replaceAll("@override", username);
        waitForElementPresent(sideloc, 20);
        Assert.assertTrue(isElementPresent(sideloc));
        putWait(ConstantsUtil.minWait);
    }
    
    public void loginIntoApplication(String username, String password) throws InterruptedException{
    	enterUserName(username);
        enterPassword(password);
        clickOnLoginButton();
        putWait(ConstantsUtil.minWait);
//       verifyLogin(username);
//        log.log("Login verified successfully.");
    }
    
    public void logoutUserFromApplication() {
    	putWait(ConstantsUtil.minWait);
		logoutFromApplication();
	}
    
	public void closeWelcomePopUp() {
		putWait(ConstantsUtil.minWait);
		clickOn(LoginPageLocators.welcomePopUpCheckbox);
		clickOn(LoginPageLocators.closeButton);
		putWait(ConstantsUtil.minWait);
	}
}
