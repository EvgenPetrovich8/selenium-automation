package io.react.realworld;


import com.hillel.selenium.auto.page.object.*;
import com.hillel.selenium.auto.user.User;
import com.hillel.selenium.auto.utils.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PageObjectsNavTest extends TestBase{

    private User user = UserData.defaultUser();
    private HomePage homePage;

    @Test
    public void loginTest() {
        clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);

        assertThat(loginPage.getPageTitle()).isEqualTo("Sign In");

        HomePage homePage = loginPage.login(user.getEmail(), user.getPassword());
        System.out.println("Check that user ig logged in");
        assertThat(homePage.isUserLoggedIn(user.getUsername())).isTrue();
    }

    @Test
    public void homePageToNewPostPage(){
        clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);

        assertThat(loginPage.getPageTitle()).isEqualTo("Sign In");

        HomePage homePage = loginPage.login(user.getEmail(), user.getPassword());
        System.out.println("Check that user ig logged in");
        assertThat(homePage.isUserLoggedIn(user.getUsername())).isTrue();

        NewPostPage newArticlePage = homePage.clickNewPost();
        assertThat(newArticlePage.isPageOpen()).isTrue();
        assertThat(newArticlePage.isTitleFieldDisplayed());


    }
    @Test
    public void newPostPageToSettingsPage(){
        clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);

        assertThat(loginPage.getPageTitle()).isEqualTo("Sign In");

        HomePage homePage = loginPage.login(user.getEmail(), user.getPassword());
        System.out.println("Check that user ig logged in");
        assertThat(homePage.isUserLoggedIn(user.getUsername())).isTrue();

        NewPostPage newArticlePage = homePage.clickNewPost();
        assertThat(newArticlePage.isPageOpen()).isTrue();

        SettingsPage settingsPage = newArticlePage.clickSettings();
        WebElement yourSettings = driver.findElement(By.cssSelector("h1[class='text-xs-center']"));
        assertThat(settingsPage.isPageOpen()).isTrue();
        assertThat(settingsPage.isYourSettingsDisplayed());
        assertThat(yourSettings.getText()).isEqualTo("Your Settings");
    }

    @Test
    public void SettingsPageToProfilePage(){
        clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);

        assertThat(loginPage.getPageTitle()).isEqualTo("Sign In");

        HomePage homePage = loginPage.login(user.getEmail(), user.getPassword());
        System.out.println("Check that user ig logged in");
        assertThat(homePage.isUserLoggedIn(user.getUsername())).isTrue();

        NewPostPage newArticlePage = homePage.clickNewPost();
        assertThat(newArticlePage.isPageOpen()).isTrue();

        SettingsPage settingsPage = newArticlePage.clickSettings();
        assertThat(settingsPage.isPageOpen()).isTrue();

        ProfilePage profilePage = settingsPage.clickUserNameBtn();
        assertThat(profilePage.isPageOpen()).isTrue();
        assertThat(profilePage.isEditProfileSettingsExist());

            }
}
