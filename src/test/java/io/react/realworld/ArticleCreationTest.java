package io.react.realworld;


import com.hillel.selenium.auto.user.User;
import com.hillel.selenium.auto.page.object.ArticleDetailsPage;
import com.hillel.selenium.auto.page.object.HomePage;
import com.hillel.selenium.auto.page.object.LoginPage;
import com.hillel.selenium.auto.page.object.NewPostPage;
import com.hillel.selenium.auto.page.object.ProfilePage;
import com.hillel.selenium.auto.utils.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleCreationTest extends TestBase{

    private User user = UserData.defaultUser();
    private HomePage homePage;

    @BeforeMethod
    public void login() {

        clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);

        assertThat(loginPage.getPageTitle()).isEqualTo("Sign In");

        homePage = loginPage.login(user.getEmail(), user.getPassword());
        System.out.println("Check that user ig logged in");
        assertThat(homePage.isUserLoggedIn(user.getUsername())).isTrue();
    }

    @Test
    public void checkNewArticleCreation() {
        NewPostPage newArticlePage = homePage.clickNewPost();
        newArticlePage.inputArticleTitle("TestNewArticle");
        newArticlePage.inputWhatArticleAbout("YevhenTestArticle");
        newArticlePage.inputArticle("TestText");
        newArticlePage.inputTags("TestTags");

        ArticleDetailsPage articleDetailsPage = newArticlePage.clickPublishArticleBtn();
        assertThat(articleDetailsPage.isPageOpen()).isTrue();
        assertThat(articleDetailsPage.getArticleName()).isEqualTo("TestNewArticle");

        articleDetailsPage.clickDelArticleBtn();

    }

    @Test
    public void checkEditArticle() {
        NewPostPage newArticlePage = homePage.clickNewPost();
        newArticlePage.inputArticleTitle("TestNewArticleForEdit");
        newArticlePage.inputWhatArticleAbout("YevhenTestArticle");
        newArticlePage.inputArticle("TestText");
        newArticlePage.inputTags("TestTags");

        ArticleDetailsPage articleDetailsPage = newArticlePage.clickPublishArticleBtn();
        articleDetailsPage.clickEditArticleBtn();
        newArticlePage.inputWhatArticleAbout("YevhenTestArticleAfterEdit");
        newArticlePage.inputArticle("TestTextAfterEdit");
        newArticlePage.inputTags("TestTagsAfterEdit");
        ArticleDetailsPage articleDetailsPageAfterEdit = newArticlePage.clickPublishArticleBtn();

        assertThat(articleDetailsPageAfterEdit.getArticleAfterEdit()).isEqualTo("TestTextAfterEdit");

        articleDetailsPage.clickDelArticleBtn();

    }

    @Test
    public void checkDeleteArticle() {
        NewPostPage newArticlePage = homePage.clickNewPost();
        newArticlePage.inputArticleTitle("TestNewArticleForDel");
        newArticlePage.inputWhatArticleAbout("YevhenTestArticle");
        newArticlePage.inputArticle("TestText");
        newArticlePage.inputTags("TestTags");

        ArticleDetailsPage articleDetailsPage = newArticlePage.clickPublishArticleBtn();
        assertThat(articleDetailsPage.getArticleNameForDel()).isEqualTo("TestNewArticleForDel");
        articleDetailsPage.clickDelArticleBtn();



        ProfilePage profilePage = homePage.clickProfile();
        assertThat(profilePage.areArticlesExist()).isTrue();

    }




}
