package com.epam.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.openqa.selenium.support.PageFactory.initElements;

/**
 * Created by Mikhail_Churakov on 5/13/2017.
 */
public class LoginPanel{
    static LoginPanel obj;
    public static LoginPanel getLoginPanel(WebDriver driver) {
        if(obj != null)
            return obj;
        obj = new LoginPanel();
        initElements(driver, obj);
        return obj;
    }
    @FindBy(className = "profile-photo")
    public static WebElement loginLabel;
    @FindBy(id = "Login")
    public static WebElement loginField;
    @FindBy(id = "Password")
    public static WebElement passwordField;
    @FindBy(css = ".btn-login")
    public static WebElement loginButton;
    @FindBy(css = ".logout")
    public static WebElement logoutButton;
    @FindBy(css = ".profile-photo>span")
    public static WebElement userNameLabel;
    @FindBy(css = ".login-txt")
    public static WebElement failedLoginLabel;
}
