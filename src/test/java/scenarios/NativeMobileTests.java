package scenarios;

import org.testng.annotations.Test;
import setup.BaseTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.GetPropertyUtil.CREDENTIALS;

public class NativeMobileTests extends BaseTest {

    public static final String EMAIL = "email";
    public static final String USER_NAME = "userName";
    public static final String PASSWORD = "password";

    @Test(groups = {"native"}, description = "New account registration and sign in")
    public void simpleNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getPageObject().getElement("regAccountBtn").click();//click registration btn
        getPageObject().getElement("email").sendKeys(CREDENTIALS.getProperty(EMAIL));//enter email
        getPageObject().getElement("userName").sendKeys(CREDENTIALS.getProperty(USER_NAME));//enter user name
        getPageObject().getElement("password").sendKeys(CREDENTIALS.getProperty(PASSWORD));//enter password
        getPageObject().getElement("confirmPassword").sendKeys(CREDENTIALS.getProperty(PASSWORD));//confirm password
        getPageObject().getElement("registerAccountBtn").click();//confirm new user registration
        getPageObject().getElement("emailToLogIn").sendKeys(CREDENTIALS.getProperty(EMAIL));//enter log in email
        getPageObject().getElement("passwordToLogIn").sendKeys(CREDENTIALS.getProperty(PASSWORD));//enter password
        getPageObject().getElement("signInBtn").click();//click sign in btn

        assertThat(getPageObject().getElement("pageTitle").getText().contains("Budjet"));//assert we are on a Budget Activity page
    }
}
