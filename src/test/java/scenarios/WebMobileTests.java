package scenarios;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import setup.BaseTest;

import java.util.List;

import static consts.WebPageObjectElements.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

public class WebMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "Make sure that there are not null search results for EPAM in Google search")
    public void simpleWebTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getDriver().get("http://google.com"); // open Google search

        waitScriptsLoaded();//wait until scripts are loaded

        WebElement searchField = waitAndGetElement(getPageObject().getElement(SEARCH_FIELD));//wait until search field is visible
        searchField.sendKeys("EPAM");
        WebElement searchIcon = waitAndGetElement(getPageObject().getElement(SEARCH_ICON));//wait until search icon is visible
        searchIcon.click();

        waitScriptsLoaded();//wait until scripts are loaded

        List<WebElement> resultList = waitAndGetElements(getPageObject().getElements(RESULT_LINKS));//wait until search results are shown

        System.out.println(resultList.size());
        assertNotNull(resultList);
        assertFalse(resultList.isEmpty());
    }
}