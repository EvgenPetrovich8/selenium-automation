package com.hillel.selenium.auto;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SeleniumDevTest {

    private WebDriver driver;

    @BeforeClass
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void seleniumDevSiteDownloadsCheckTitle()
    {
        WebDriver.Navigation navigate= driver.navigate();
        navigate.to("https://www.selenium.dev/downloads");
        String title = driver.getTitle();
        System.out.println(title);

        assertEquals(title,"Downloads");


    }

    @Test
    public void seleniumDevSiteProjectsCheckTitle()
    {
        WebDriver.Navigation navigate= driver.navigate();
        navigate.to("https://www.selenium.dev/projects");
        String title = driver.getTitle();
        System.out.println(title);

        assertEquals(title,"Selenium Projects");

    }

    @Test
    public void seleniumDevSiteDocumentationCheckTitle()
    {
        WebDriver.Navigation navigate= driver.navigate();
        navigate.to("https://www.selenium.dev/documentation/en");
        String title = driver.getTitle();
        System.out.println(title);

        assertEquals(title,"The Selenium Browser Automation Project :: Documentation for Selenium");

    }

    @Test
    public void seleniumDevSiteSupportCheckTitle()
    {
        WebDriver.Navigation navigate= driver.navigate();
        navigate.to("https://www.selenium.dev/support");
        String title = driver.getTitle();
        System.out.println(title);

        assertEquals(title,"Selenium Support");

    }
    @Test
    public void seleniumDevSiteBlogCheckTitle()
    {
        WebDriver.Navigation navigate= driver.navigate();
        navigate.to("https://www.selenium.dev/blog");
        String title = driver.getTitle();
        System.out.println(title);

        assertEquals(title,"Selenium Blog");

    }


}
