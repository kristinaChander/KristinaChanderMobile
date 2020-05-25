package scenarios;

import org.testng.annotations.Test;
import setup.BaseTest;

import static consts.NativePageObjectElements.*;
import static utils.GetPropertyUtil.CREDENTIALS;

public class NativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "New account registration and sign in")
    public void simpleNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getPageObject().getElement(SIGN_IN_BTN).click();
        getPageObject().getElement(REGISTRATION_BTN).click();
        getPageObject().getElement(REG_FORM_EMAIL).sendKeys(CREDENTIALS.getProperty("email"));
        getPageObject().getElement(REG_FORM_USER_NAME).sendKeys(CREDENTIALS.getProperty("userName"));
        getPageObject().getElement(REG_FORM_PASSWORD).sendKeys(CREDENTIALS.getProperty("password"));
        getPageObject().getElement(REG_FORM_CONFIRM_PASSWORD).sendKeys(CREDENTIALS.getProperty("password"));
        getPageObject().getElement(REG_FORM_REG_NEW_ACCOUNT_BTN).click();
        getPageObject().getElement(LOG_IN_EMAIL).sendKeys(CREDENTIALS.getProperty("email"));
        getPageObject().getElement(LOG_IN_PASSWORD).sendKeys(CREDENTIALS.getProperty("password"));
        getPageObject().getElement(SIGN_IN_BTN).click();

        assert(getPageObject().getElement(BUDGET_PAGE_TITLE).getText().equals("BudgetActivity"));

        System.out.println("Android native test done");
    }
}
