import com.epam.DataProviders;
import com.epam.TestBase;
import com.epam.pageobject.LoginPanel;
import com.epam.pageobject.MainPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.lang.reflect.Method;

/**
 * Created by Mikhail_Churakov on 5/13/2017.
 */
public class LoginTests extends TestBase{
    WebDriver driver;
    private static String screenPath = "screenshots\\Login Tests\\";

    @Parameters("browser")
    @BeforeSuite
    public void setupLoginTests(String browser) {
        driver = initDriver(browser);
        navigateToMainPage();
    }

    @AfterSuite
    public void closeLoginTests() {
        closeBrowser();
    }

    @BeforeMethod
    public void initTest(Method method) {
        System.out.println(method.getName());
    }

    @AfterMethod
    public void closeTest(Method method) {
        makeScreen(method, screenPath);
    }

    @Test(dataProvider = "correctData", dataProviderClass = DataProviders.class)
    public void positiveLogin(String login, String password) {
        MainPage mainPage = MainPage.get(driver);
        LoginPanel loginPanel = LoginPanel.get(driver);
        mainPage.loginDropDown.click();
        assertTrue(loginPanel.loginLabel.isDisplayed());
        typeTextIn(login,loginPanel.loginField);
        typeTextIn(password, loginPanel.passwordField);
        loginPanel.loginButton.click();
        assertFalse(loginPanel.loginField.isDisplayed());
        assertFalse(loginPanel.passwordField.isDisplayed());
        assertTrue(loginPanel.userNameLabel.isDisplayed());
    }
    @Test(dataProvider = "incorrectDataCsv", dataProviderClass = DataProviders.class)
    public void negativeLogin(String login, String password) {
        MainPage mainPage = MainPage.get(driver);
        LoginPanel loginPanel = LoginPanel.get(driver);
        waitElement(mainPage.loginDropDown);
        mainPage.loginDropDown.click();
        assertTrue(loginPanel.loginLabel.isDisplayed());
        typeTextIn(login,loginPanel.loginField);
        typeTextIn(password, loginPanel.passwordField);
        loginPanel.loginButton.click();
        assertTrue(loginPanel.failedLoginLabel.isDisplayed());
        assertTrue(loginPanel.loginField.isDisplayed());
        assertTrue(loginPanel.passwordField.isDisplayed());
        assertFalse(loginPanel.userNameLabel.isDisplayed());
        loginPanel.loginField.clear();
        loginPanel.passwordField.clear();
        mainPage.loginDropDown.click();
        assertFalse(loginPanel.loginField.isDisplayed());
        assertFalse(loginPanel.passwordField.isDisplayed());
    }
}
