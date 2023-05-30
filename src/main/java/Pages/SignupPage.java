package Pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class SignupPage {
    WebDriver driver;

    String registerUserAcctText = "Register user account";
    String firstNameField = "//*[@id='first-name']";
    String lastNameField = "//*[@id='last-name']";
    String emailField = "//*[@id='email']";
    String passwordField = "//*[@id='password']";
    String confirmPasswordField = "//*[@id='confirm-password']";
    String organizationField = "//*[@id='OrganizationAccountId']";
    String submitBtn = "//*[@id='kt_sign_up_submit']";
    String continueBtn = "//*[@id='kt_sign_in_submit']";

    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    public void Signup(String firstName, String lastName, String email, String password, String confirmPassword, String organization) {
        driver.findElement(By.linkText(registerUserAcctText)).click();

        driver.findElement(By.xpath(firstNameField)).sendKeys(firstName);
        driver.findElement(By.xpath(lastNameField)).sendKeys(lastName);
        driver.findElement(By.xpath(emailField)).sendKeys(email);
        driver.findElement(By.xpath(passwordField)).sendKeys(password);
        driver.findElement(By.xpath(confirmPasswordField)).sendKeys(confirmPassword);
        WebElement dropdownElement = driver.findElement(By.xpath(organizationField));

        Select select = new Select(dropdownElement);
        select.selectByVisibleText(organization);
        driver.findElement(By.xpath(submitBtn)).click();

        driver.findElement(By.xpath(continueBtn)).click();

    }


}