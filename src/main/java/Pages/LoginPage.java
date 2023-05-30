package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    String emailfield = "//*[@id='email']";
    String pswdfield = "//*[@id='password']";
    String continueBtn = "//*[@id='kt_sign_in_submit']";
    String loginErrorMsg = "//*[@class='text-danger validation-summary-errors']/ul/li";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String email, String password) {
        driver.findElement(By.xpath(emailfield)).clear();
        driver.findElement(By.xpath(emailfield)).sendKeys(email);
        driver.findElement(By.xpath(pswdfield)).sendKeys(password);
        driver.findElement(By.xpath(continueBtn)).click();
    }

    public String getErrorMessage() {
        return driver.findElement(By.xpath(loginErrorMsg)).getText();
    }

    public String getEmailFieldValue() {
        return driver.findElement(By.xpath(loginErrorMsg)).getAttribute("value");
    }
}