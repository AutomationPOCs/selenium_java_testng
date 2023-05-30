package tests;

import Utils.Browser;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.ITestResult;

import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest {
    public String baseUrl;
    public WebDriver driver;
    public Browser browser;
    public static ExtentSparkReporter spark;
    public static ExtentReports extent;
    public ExtentTest logger;

    @BeforeSuite
    public void startReport() {
        // Create an object of Extent Reports
        extent = new ExtentReports();

        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/IdealSolutionsReport.html");
        extent.attachReporter(spark);
        spark.config().setDocumentTitle("Automation Report");
        // Dark Theme
        spark.config().setTheme(Theme.STANDARD);
    }

    public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    @BeforeMethod()
    @Parameters({"baseUrl", "Browser"})
    public void setUp(String baseUrl, String Browser) {
        browser = new Browser(Browser, baseUrl);
        browser.navigateTo(baseUrl);
        driver = browser._driver;
        this.baseUrl = baseUrl;
    }

    @AfterMethod
    public void getResult(ITestResult result) throws Exception{
        if(result.getStatus() == ITestResult.FAILURE){
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
            String screenshotPath = getScreenShot(driver, result.getName());
            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
        }
        else if(result.getStatus() == ITestResult.SKIP){
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
            String screenshotPath = getScreenShot(driver, result.getName());
            logger.pass("Test Case Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
        }
        driver.close();
    }
    @AfterTest
    public void endReport() {
        extent.flush();
    }
}
