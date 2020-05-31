package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.PageObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class BaseTest implements IDriver {

    private static AppiumDriver appiumDriver; // singleton
    private static IPageObject pageObject;
    private static WebDriverWait webDriverWait;

    @Override
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    public IPageObject getPageObject() {
        return pageObject;
    }

    @Parameters({"platformName", "appType", "deviceName", "browserName", "app", "udid", "appPackage", "appActivity", "bundleId"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName,
                      String appType,
                      @Optional("") String deviceName,
                      @Optional("") String browserName,
                      @Optional("") String app,
                      @Optional("") String udid,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId
    ) throws Exception {
        DesiredCapabilities capabilities = getDesiredCapabilities(platformName,
                deviceName, browserName, app, udid, appPackage, appActivity, bundleId);
        setAppiumDriver(capabilities);
        setPageObject(appType, appiumDriver);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        System.out.println("Close appium driver");
        appiumDriver.closeApp();
    }

    private DesiredCapabilities getDesiredCapabilities(
            String platformName, String deviceName, String browserName, String app,
            String udid, String appPackage, String appActivity, String bundleId) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");

        if (app.endsWith(".apk")) {
            capabilities.setCapability("app", (new File(app)).getAbsolutePath());
        }
        // Capabilities for test of Android native app on EPAM Mobile Cloud
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        // Capabilities for test of iOS native app on EPAM Mobile Cloud
        capabilities.setCapability("bundleId", bundleId);

        return capabilities;
    }

    private URL getRemoteAddress() {
        try {
            return new URL(System.getProperty("ts.appium"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setAppiumDriver(DesiredCapabilities capabilities) {
        appiumDriver = new AppiumDriver(getRemoteAddress(), capabilities);

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(appiumDriver, 15);
    }

    private static void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        pageObject = new PageObject(appType, appiumDriver);
    }

    protected WebElement waitAndGetElement(WebElement element) {
        return webDriverWait.until(visibilityOf(element));
    }

    protected List<WebElement> waitAndGetElements(List<WebElement> elements) {
        return webDriverWait.until(visibilityOfAllElements(elements));
    }

    protected void waitScriptsLoaded() {
        webDriverWait.until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
    }
}