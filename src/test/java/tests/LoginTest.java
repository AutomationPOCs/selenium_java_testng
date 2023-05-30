package tests;

import Utils.Browser;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test()
    public void UserLoginWithValidCredential() {
        logger = extent.createTest("Login to Existing Account with valid credentials");
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login("testuser04.bh@gmail.com", "Bug@127");

        // Assert URL after successful login
        String expectedUrl = this.baseUrl + "/Dashboard";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test()
    public void UserLoginWithInvalidCredential() {
        logger = extent.createTest("Login to Existing Account with invalid credentials");
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login("testuser04.bh@gmail.com", "Bug@127898");

        // Assert URL after successful login
        String expectedErrorMsg = "It seems that something went wrong, we're working on getting!";
        String actualErrorMsg = loginPage.getErrorMessage();
        if (actualErrorMsg.contains("This account locked out until")) {
            Assert.fail(" Msg: This account locked out until 5/12/2023 2:40:54 PM UTC!");
        } else {
            Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
        }
    }
}
