import com.epam.DataProviders;
import com.epam.TestBase;
import com.epam.pageobject.LoginPanel;
import com.epam.pageobject.MainPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.*;

import java.lang.reflect.Method;

import static ru.yandex.qatools.allure.model.SeverityLevel.BLOCKER;
import static ru.yandex.qatools.allure.model.SeverityLevel.CRITICAL;

/**
 * Created by Mikhail_Churakov on 5/13/2017.
 */
@Title("This is LOGIN TESTS!!")
public class LoginTests extends TestBase{
    WebDriver driver;
    private static String screenPath = "screenshots\\Login Tests\\";
    @Parameters("browser")
    @Step("we're executing our driver and opening the main page")
    @BeforeSuite
    public void setupLoginTests(String browser) {
        driver = setDriver(browser);
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getPageSource());
        navigateToMainPage();
    }
    @Step("we're closing the instance")
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
    @Title("This is a positive login!!")
    @Description("here we checked valid credentials")
    @Severity(BLOCKER)
    @Features("Login functionality")
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
    @Title("This is a negative login!!")
    @Description("here we checked invalid credentials")
    @Severity(CRITICAL)
    @Features("Login functionality")
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
