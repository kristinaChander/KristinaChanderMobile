package scenarios;

import consts.WebPageObjectElements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import setup.BaseTest;

import java.util.List;

import static consts.WebPageObjectElements.*;

public class WebMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "Make sure that there are not null search results for EPAM in Google search")
    public void simpleWebTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getDriver().get("http://google.com"); // open Google search

        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        WebElement searchField = getPageObject().getElement(SEARCH_FIELD);
        new WebDriverWait(getDriver(),10).until(ExpectedConditions
                .visibilityOf(searchField));

        searchField.sendKeys("EPAM");

        WebElement searchIcon = getPageObject().getElement(SEARCH_ICON);

        new WebDriverWait(getDriver(),10).until(ExpectedConditions.visibilityOf(searchIcon));
        searchIcon.click();

        List<WebElement> resultsList = getPageObject().getElements(RESULT_LINKS);

        assert (resultsList.size() > 0);
    }
}
