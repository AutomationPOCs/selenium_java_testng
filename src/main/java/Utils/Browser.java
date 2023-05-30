package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Browser {
    // Public properties
    public WebDriver _driver;

    // Private properties
    private DesiredCapabilities capabilities;
    private String browserName;
    private String baseUrl;

    public Browser(String browserName, String baseUrl) {
        setBrowser(browserName);
        setBaseUrl(baseUrl);
        Initialise(getBrowser());
    }

    private void Initialise(String browser) {
        capabilities = new DesiredCapabilities();

        switch (browser) {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chrome_options = new ChromeOptions();
                capabilities.setBrowserName("chrome");
                chrome_options.addArguments("start-maximized"); // open Browser in maximized mode
                chrome_options.addArguments("disable-infobars"); // disabling infobars
                chrome_options.addArguments("--disable-extensions"); // disabling extensions
                chrome_options.addArguments("--disable-gpu"); // applicable to windows os only
                chrome_options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                chrome_options.addArguments("--no-sandbox"); // Bypass OS security model
                chrome_options.addArguments("--disable-dev-shm-usage");
                _driver = new ChromeDriver(chrome_options);
                break;
            case "Safari":
                WebDriverManager.safaridriver().setup();
                capabilities.setBrowserName("safari");
                _driver = new SafariDriver();
                break;
            case "Edge":
                WebDriverManager.edgedriver().setup();
                capabilities.setBrowserName("edge");
                _driver = new EdgeDriver();
                break;
            default:
                System.out.println("Invalid browser passed in: " + browser);
                break;
        }

        _driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    public void navigateTo(String url) {
        _driver.get(url);
    }

    public void navigateToBaseUrl() {
        _driver.get(getBaseUrl());
    }

    public String getBrowser() {
        return this.browserName;
    }

    private void setBrowser(String browserName) {
        this.browserName = browserName;
    }

    private void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }
}
