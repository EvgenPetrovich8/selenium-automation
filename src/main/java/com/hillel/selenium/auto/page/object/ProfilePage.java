package com.hillel.selenium.auto.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends Page{

    private By articles = By.cssSelector(".article-preview");
    private By profilePage = By.cssSelector(".profile-page");
    private By editProfileBtn = By.cssSelector("[href='#settings']");
    private By articleName = By.cssSelector("[href='#settings']");
    private By noArticles = By.xpath("//*[text()='No articles are here... yet.']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public int getArticlesSize() {
        return driver.findElements(articles).size();
    }

    public boolean isPageOpen() {
        return driver.findElement(profilePage).isDisplayed();
    }

    public boolean isEditProfileSettingsExist()  {
        return driver.findElement(editProfileBtn).isDisplayed();
    }

    public boolean areArticlesExist()  {
        return driver.findElement(noArticles).isDisplayed();
    }

//    public String getArticleName ()  {
//        return driver.findElement(articleName).getText();
//    }
}
