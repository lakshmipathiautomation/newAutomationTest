package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import utility.ConstantsUtil;
import utility.DriverTestCase;
import utility.ExecutionLog;
import utility.PropertyReader;

public class LoginTest  extends DriverTestCase {

    LoginPage loginPage;
    PropertyReader prop = new PropertyReader();
    ExecutionLog reporter = new ExecutionLog();

    @BeforeTest
    public void init() throws Exception {
        setup();
        loginPage = new LoginPage(getWebDriver());
    }

    @Test(description = "")
    public void loginTest(){
        loginPage.enterUserName(prop.readProperty("username"));
        loginPage.enterPassword(prop.readProperty("password"));
        loginPage.clickOnLoginButton();
        loginPage.verifyLogin(prop.readProperty("username"));
        log.log("Login verified successfully.");
    }
}
