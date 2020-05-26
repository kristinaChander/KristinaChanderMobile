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

    @Parameters({"platformName", "appType", "deviceName", "browserName", "app"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName, String appType, String deviceName,
                      @Optional("") String browserName, @Optional("") String app) throws Exception {
        setAppiumDriver(platformName, deviceName, browserName, app);
        setPageObject(appType, appiumDriver);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("Close appium driver");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String deviceName, String browserName, String app) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);

        if (app.endsWith(".apk")) {
            capabilities.setCapability("app", (new File(app)).getAbsolutePath());
        }

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");

//        try {
            appiumDriver = new AppiumDriver(/*new URL(System.getProperty("ts.appium")), */capabilities);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(appiumDriver, 10);
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