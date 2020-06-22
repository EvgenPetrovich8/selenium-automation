package com.hillel.selenium.auto.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ArticleDetailsPage extends Page{

    private By articlePage = By.cssSelector(".article-page");
    private By articleName = By.xpath("//*[@class='container']//*[text()='TestNewArticle']");
    private By articleNameForDel = By.xpath("//*[@class='container']//*[text()='TestNewArticleForDel']");
    private By articleAfterEdit = By.xpath("//*[text()='TestTextAfterEdit']");
    private By editArticleBtn = By.xpath("//*[@class='btn btn-outline-secondary btn-sm']");
    private By delArticleBtn = By.xpath("//*[@class='btn btn-outline-danger btn-sm']");


    public ArticleDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpen() {
        return driver.findElement(articlePage).isDisplayed();
    }

    public String getArticleName()  {
        return driver.findElement(articleName).getText();
    }

    public String getArticleNameForDel()  {
        return driver.findElement(articleNameForDel).getText();
    }

    public String getArticleAfterEdit()  {
        return driver.findElement(articleAfterEdit).getText();
    }



    public NewPostPage clickEditArticleBtn() {
        driver.findElement(editArticleBtn).click();
        return new NewPostPage(driver);
    }

    public HomePage clickDelArticleBtn() {
        waits.elementToBeClickable(delArticleBtn).click();
        return new HomePage(driver);
    }
}
