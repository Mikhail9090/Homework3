package com.epam.pageobject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;

/**
 * Created by Mikhail_Churakov on 5/13/2017.
 */
public class MainPage{
    static MainPage obj;
    public static MainPage get(WebDriver driver) {
        if(obj != null)
            return obj;
        obj = new MainPage();
        initElements(driver, obj);
        return obj;
    }
    @FindBy(className="profile-photo")
    public static WebElement loginDropDown;
}
