package io.react.realworld;


import com.hillel.selenium.auto.user.User;
import com.hillel.selenium.auto.page.object.HomePage;
import com.hillel.selenium.auto.page.object.LoginPage;
import com.hillel.selenium.auto.utils.UserData;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends TestBase{

    private User user = UserData.defaultUser();

    @Test
    public void loginTest() {
        clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);

        assertThat(loginPage.getPageTitle()).isEqualTo("Sign In");

        HomePage homePage = loginPage.login(user.getEmail(), user.getPassword());
        System.out.println("Check that user ig logged in");
        assertThat(homePage.isUserLoggedIn(user.getUsername())).isTrue();
    }



}
