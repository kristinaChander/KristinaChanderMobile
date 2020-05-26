package scenarios;

import org.testng.annotations.Test;
import setup.BaseTest;

import static consts.NativePageObjectElements.*;
import static org.testng.Assert.assertEquals;
import static utils.GetPropertyUtil.CREDENTIALS;

public class NativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "New account registration and sign in")
    public void simpleNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getPageObject().getElement(REGISTRATION_BTN).click();//click registration btn
        getPageObject().getElement(REG_FORM_EMAIL).sendKeys(CREDENTIALS.getProperty("email"));//enter email
        getPageObject().getElement(REG_FORM_USER_NAME).sendKeys(CREDENTIALS.getProperty("userName"));//enter user name
        getPageObject().getElement(REG_FORM_PASSWORD).sendKeys(CREDENTIALS.getProperty("password"));//enter password
        getPageObject().getElement(REG_FORM_CONFIRM_PASSWORD).sendKeys(CREDENTIALS.getProperty("password"));//confirm password
        getPageObject().getElement(REG_FORM_REG_NEW_ACCOUNT_BTN).click();//confirm new user registration
        getPageObject().getElement(LOG_IN_EMAIL).sendKeys(CREDENTIALS.getProperty("email"));//enter log in email
        getPageObject().getElement(LOG_IN_PASSWORD).sendKeys(CREDENTIALS.getProperty("password"));//enter password
        getPageObject().getElement(SIGN_IN_BTN).click();//click sign in btn

        assertEquals(getPageObject().getElement(BUDGET_PAGE_TITLE).getText(),"BudgetActivity");//assert we are on a Budget Activity page
    }
}
