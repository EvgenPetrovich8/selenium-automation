package com.hillel.selenium.auto.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.hillel.selenium.auto.WaitsExample;

public abstract class Page {

    protected WebDriver driver;
    protected WaitsExample waits;

    public Page(WebDriver driver) {
        this.driver = driver;
        this.waits = new WaitsExample(driver);;
    }

    protected void inputText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    protected void inputText(By locator, String text) {
        WebElement element = waits.visibilityOfElementLocated(locator);
        element.clear();
        element.sendKeys(text);
    }
}
