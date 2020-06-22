package com.hillel.selenium.auto.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends Page {

    private By profileBtn = By.xpath("//*[@class='user-pic']/..");

    public HomePage (WebDriver driver) {
        super(driver);
    }

    public boolean isUserLoggedIn(String userName) {
        WebElement userInfo = driver.findElement(By.cssSelector("[href='#@" +userName+"']"));
        return userInfo.isDisplayed();
    }

    public NewPostPage clickNewPost() {
        driver.findElement(By.cssSelector("[href='#editor']")).click();
        return new NewPostPage(driver);
    }

    public ProfilePage clickProfile() {
        driver.findElement(profileBtn).click();
        return new ProfilePage(driver);
    }


}
