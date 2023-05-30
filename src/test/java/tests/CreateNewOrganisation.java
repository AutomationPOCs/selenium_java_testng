package tests;

import Pages.LoginPage;
import Utils.Browser;
import Pages.AccountPage;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class CreateNewOrganisation extends BaseTest {

    @Test()
    public void createOrganization() {
        logger = extent.createTest("Create a New Organization");
        AccountPage createNewOrganisation = new AccountPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        Random random = new Random();

        String orgName = "testorg" + random.nextInt(1000) + 100;
        String firstName = "testUser" + random.nextInt(1000) + 100;
        String lastName = "xyz";
        String email = orgName + "@mailinator.com";
        String password = "test@1234";
        String confirmPassword = "test@1234";

        createNewOrganisation.RegisterOrganisation(orgName, firstName, lastName, email, password, confirmPassword);
        loginPage.login(email, password);

        // Assert URL after successful login
        String expectedUrl = this.baseUrl + "/Dashboard";
        String actualDashboardUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualDashboardUrl, expectedUrl);
    }


}
