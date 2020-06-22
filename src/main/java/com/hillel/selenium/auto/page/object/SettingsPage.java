package com.hillel.selenium.auto.page.object;

import com.hillel.selenium.auto.utils.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class SettingsPage extends Page{

    public SettingsPage (WebDriver driver) {
        super(driver);
    }


    private By settingsPage = By.cssSelector(".settings-page");
    private By yourSettings = By.xpath("//*[@class='text-xs-center']/..");
    private By userNameBtn = By.cssSelector("[href='#@" + UserData.defaultUser().getUsername() + "']");


    public boolean isPageOpen() {
        return driver.findElement(settingsPage).isDisplayed();
    }

    public boolean isYourSettingsDisplayed() {
        return driver.findElement(yourSettings).isDisplayed();
    }

    public ProfilePage clickUserNameBtn() {
        driver.findElement(userNameBtn).click();
        return new ProfilePage(driver);
    }

   }
