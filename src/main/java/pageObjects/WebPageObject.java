package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WebPageObject  {

   @FindBy(css = "#tsf input[type='search']")
    WebElement googleSearchField;

    @FindBy(css = "button.Tg7LZd ")
    WebElement searchIcon;

    @FindBy(css = "#rso div.jGGQ5e a")
    List<WebElement> results;

    public WebPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);
    }
}
