package tests;

import Pages.LoginPage;
import Pages.SignupPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;

public class CreateNewUserAccountTest extends BaseTest {

    @Test()
    public void createNewUserAccount() {
        logger = extent.createTest("Create a New User Account");
        SignupPage signupPage = new SignupPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        Random random = new Random();
        int randomNumber = random.nextInt(1000) + 100; // Generates a number between 10 and 99

        String firstName = "test" + randomNumber;
        String lastName = "xyz";
        String email = firstName + "@mailinator.com";
        String password = "test@1234";
        String confirmPassword = "test@1234";
        String organization = "Idealsolutions";

        signupPage.Signup(firstName, lastName, email, password, confirmPassword, organization);

        loginPage.login(email, password);

        // Assert URL after successful login
        String expectedUrl = this.baseUrl + "/Dashboard";
        String actualDashboardUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualDashboardUrl, expectedUrl);

    }
}
