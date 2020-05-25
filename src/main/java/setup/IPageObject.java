package setup;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface IPageObject {

    WebElement getElement(String name) throws NoSuchFieldException, IllegalAccessException, InstantiationException;

    List<WebElement> getElements(String name) throws NoSuchFieldException, IllegalAccessException, InstantiationException;
}
