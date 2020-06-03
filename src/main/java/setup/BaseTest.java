package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.PageObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest implements IDriver {

    protected static AppiumDriver appiumDriver; // singleton
    private static IPageObject pageObject;

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
        DesiredCapabilities capabilities = getDesiredCapabilities(platformName, deviceName, browserName, app);
        initAppiumDriver(capabilities);
        setPageObject(appType, appiumDriver);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        System.out.println("Close appium driver");
        appiumDriver.closeApp();
    }

    private DesiredCapabilities getDesiredCapabilities(String platformName, String deviceName, String browserName, String app) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);

        if (app.endsWith(".apk")) {
            capabilities.setCapability("app", (new File(app)).getAbsolutePath());
        }

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");
        return capabilities;
    }

    private URL getRemoteAddress() {
        try {
            return new URL(System.getProperty("ts.appium"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initAppiumDriver(DesiredCapabilities capabilities) {
        appiumDriver = new AppiumDriver(getRemoteAddress(), capabilities);
        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private static void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        pageObject = new PageObject(appType, appiumDriver);
    }
}