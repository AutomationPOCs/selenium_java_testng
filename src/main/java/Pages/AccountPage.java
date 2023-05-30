package Pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AccountPage {
    WebDriver driver;
    Random rand = new Random();
    int randomNumber = rand.nextInt(100) + 1;
    String organizationNameField = "//*[@id='organization-name']";
    String firstNameField = "//*[@id='first-name']";
    String lastNameField = "//*[@id='last-name']";
    String emailField = "//*[@id='email']";
    String passwordField = "//*[@id='password']";
    String confirmPasswordField = "//*[@id='confirm-password']";
    String submitBtn = "//*[@id='kt_sign_up_submit']";
    String continueBtn = "//*[@id='kt_sign_in_submit']";


    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void RegisterOrganisation(String organizationName, String firstName, String lastName, String email, String password, String confirmPassword) {
        WebElement newOrganizationLink = driver.findElement(By.xpath("//a[@href='/Account/RegisterOrganization']"));
        newOrganizationLink.click(); //navigates to the organization creation page

        driver.findElement(By.xpath(organizationNameField)).sendKeys(organizationName);
        driver.findElement(By.xpath(firstNameField)).sendKeys(firstName);
        driver.findElement(By.xpath(lastNameField)).sendKeys(lastName);
        driver.findElement(By.xpath(emailField)).sendKeys(email);
        driver.findElement(By.xpath(passwordField)).sendKeys(password);
        driver.findElement(By.xpath(confirmPasswordField)).sendKeys(confirmPassword);
        driver.findElement(By.xpath(submitBtn)).click();
        driver.findElement(By.xpath(continueBtn)).click();
    }
}