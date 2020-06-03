package scenarios;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import setup.BaseTest;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

public class WebMobileTests extends BaseTest {

    private WebDriverWait webDriverWait() {
        return new WebDriverWait(appiumDriver, 15);
    }

    private void waitScriptsLoaded() {
        webDriverWait().until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
    }

    private WebElement waitAndGetElement(WebElement element) {
        return webDriverWait().until(visibilityOf(element));
    }

    private List<WebElement> waitAndGetElements(List<WebElement> elements) {
        return webDriverWait().until(visibilityOfAllElements(elements));
    }

    @Test(groups = {"web"}, description = "Make sure that there are not null search results for EPAM in Google search")
    public void simpleWebTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getDriver().get("http://google.com"); // open Google search

        waitScriptsLoaded();//wait until scripts are loaded

        WebElement searchField = waitAndGetElement(getPageObject().getElement("googleSearchField"));//wait until search field is visible
        searchField.sendKeys("EPAM");
        WebElement searchIcon = waitAndGetElement(getPageObject().getElement("searchIcon"));//wait until search icon is visible
        searchIcon.click();

        waitScriptsLoaded();//wait until scripts are loaded

        List<WebElement> resultList = waitAndGetElements(getPageObject().getElements("results"));//wait until search results are shown

        assertNotNull(resultList);
        assertFalse(resultList.isEmpty());
    }
}