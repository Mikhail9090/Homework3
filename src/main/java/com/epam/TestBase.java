package com.epam;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mikhail_Churakov on 5/13/2017.
 */
public class TestBase {
    private WebDriver driver;
    private String testHost = "https://jdi-framework.github.io/tests/";


    public WebDriver initDriver(String browser) {
        this.driver = setDriver(browser);
        this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return this.driver;
    }

    public WebDriver setDriver(String driver) {
        WebDriver setDriver = null;
        if(driver.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--kiosk");
            setDriver = new ChromeDriver(options);
        }
        if (driver.equals("firefox") || driver.equals("")) {
            System.setProperty("webdriver.gecko.driver", "C:\\GeckoDriver\\geckodriver.exe");
            setDriver = new FirefoxDriver();
        }
        return setDriver;
    }

    public void navigateToMainPage() {
        driver.navigate().to(testHost);
    }

    public void closeBrowser() {
        driver.close();
    }

    public void assertTrue(boolean value) {
        Assert.assertTrue(value);
    }

    public void assertFalse(boolean value) {
        Assert.assertFalse(value);
    }

    public void typeTextIn(String text, WebElement webElement) {
        webElement.sendKeys(text);
    }

    public void makeScreen(Method method, String screenPath) {
        TakesScreenshot sc = (TakesScreenshot)driver;
        File screenFile = sc.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenFile, new File(screenPath + method.getName() + ".png"));
        } catch (IOException ex) {
            System.out.println("The test cannot save the screen");
        }
    }

    public void waitElement(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        webElement = wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

}
