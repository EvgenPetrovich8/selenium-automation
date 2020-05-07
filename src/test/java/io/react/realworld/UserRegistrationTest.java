package io.react.realworld;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class UserRegistrationTest {

    private WebDriver driver;
 String userName = "evgenpetrovich"+new Random().nextInt(5000);
  String userNameExisting="evgenpetrovich2123";
    String email = userName+"@mail.com";
    String emailExisting = userNameExisting+"@mail.com";
    String password="test123321123";

    @BeforeClass
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    //         Регистрация юзера
    @Test (priority=1)
    public void userRegistrationPositiveCheck() {
        driver.get("https://react-redux.realworld.io");
//        #main > div > nav > div > ul > li:nth-child(3) > a



        WebElement signUpButton = driver.findElement(By.linkText("Sign up"));
        signUpButton.click();
        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl).contains("register");

        WebElement signUpForm = driver.findElement(By.cssSelector(".auth-page form"));
        assertThat(signUpForm.isDisplayed()).isTrue();

        WebElement userNameField = signUpForm.findElement(By.cssSelector("input[type='text']"));
        userNameField.clear();
        userNameField.sendKeys(userName);

        WebElement emailField = signUpForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = signUpForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement signInButton = signUpForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        WebElement userInfoCheck = driver.findElement(By.cssSelector("[href='#@" +userName+"']"));
        assertThat(userInfoCheck.isDisplayed()).isTrue();
        System.out.println(userInfoCheck);

        System.out.println(password);

    }
//         Проверка входа зарегестрированного юзера
        @Test (priority=2)
                public void loginPositiveCheck(){
            driver.get("https://react-redux.realworld.io");

            WebElement signInButton = driver.findElement(By.linkText("Sign in"));
            signInButton.click();

            WebElement signInForm = driver.findElement(By.cssSelector(".auth-page form"));
            assertThat(signInForm.isDisplayed()).isTrue();

           System.out.println(emailExisting);
           System.out.println(password);

            WebElement emailField = signInForm.findElement(By.cssSelector("input[type='email']"));
            emailField.clear();
            emailField.sendKeys(emailExisting);

            WebElement passwordField = signInForm.findElement(By.cssSelector("input[type='password']"));
            passwordField.clear();
            passwordField.sendKeys(password);

            WebElement signInButtonSubmit = signInForm.findElement(By.cssSelector("button[type='submit']"));
            signInButtonSubmit.click();

            WebElement userInfoCheck = driver.findElement(By.cssSelector("[href='#@" +userNameExisting+"']"));
            assertThat(userInfoCheck.isDisplayed()).isTrue();



        }


//        Проверка возможности навигации залогиненым юзером
    @Test (priority=3)
    public void verifiedUserNavigateCheck(){
       driver.get("https://react-redux.realworld.io");

       WebElement signInButton = driver.findElement(By.linkText("Sign in"));
       signInButton.click();

       WebElement signInForm = driver.findElement(By.cssSelector(".auth-page form"));
       assertThat(signInForm.isDisplayed()).isTrue();

       System.out.println(emailExisting);
       System.out.println(password);

      WebElement emailField = signInForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
       emailField.sendKeys(emailExisting);

        WebElement passwordField = signInForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement signInButtonSubmit = signInForm.findElement(By.cssSelector("button[type='submit']"));
       signInButtonSubmit.click();

        WebElement userNewPost = driver.findElement(By.cssSelector("[href='#editor']"));
       userNewPost.click();

        WebElement settings=driver.findElement(By.cssSelector("[href='#settings']"));
        settings.click();

        WebElement logoutButton=driver.findElement(By.cssSelector("button[class='btn btn-outline-danger']"));
        assertThat(logoutButton.isDisplayed()).isTrue();
        logoutButton.click();

        WebElement signInFormAfterLogout=driver.findElement(By.className("home-page"));
        assertThat(signInFormAfterLogout.isDisplayed()).isTrue();



    }
       // Проверка получения ошибки при пустом поле email. Аналогичная ошибка и для некорректного email
    @Test (priority=4)
    public void emptyEmailFieldNegativeCheck(){
        driver.get("https://react-redux.realworld.io");

        WebElement signInButton = driver.findElement(By.linkText("Sign in"));
        signInButton.click();

        WebElement signInForm = driver.findElement(By.cssSelector(".auth-page form"));
        assertThat(signInForm.isDisplayed()).isTrue();

        System.out.println(email);
        System.out.println(password);

        WebElement emailField = signInForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys("");

        WebElement passwordField = signInForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement signInButtonSubmit = signInForm.findElement(By.cssSelector("button[type='submit']"));
        signInButtonSubmit.click();

        WebElement errorMessage=driver.findElement(By.className("error-messages"));
        assertThat(errorMessage.isDisplayed()).isTrue();




    }
//  Проверка получения ошибки при пустом поле password. Аналогичная ошибка и для некорректного password
    @Test (priority=5)
    public void emptyPasswordFieldNegativeCheck() {
        driver.get("https://react-redux.realworld.io");

        WebElement signInButton = driver.findElement(By.linkText("Sign in"));
        signInButton.click();

        WebElement signInForm = driver.findElement(By.cssSelector(".auth-page form"));
        assertThat(signInForm.isDisplayed()).isTrue();

        System.out.println(email);
        System.out.println(password);

        WebElement emailField = signInForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = signInForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys("");

        WebElement signInButtonSubmit = signInForm.findElement(By.cssSelector("button[type='submit']"));
        signInButtonSubmit.click();

        WebElement errorMessage = driver.findElement(By.className("error-messages"));
        assertThat(errorMessage.isDisplayed()).isTrue();
    }

        @Test (priority=6)
        public void userRegistrationNegativeShortPassword() {
            driver.get("https://react-redux.realworld.io");


            WebElement signUpButton = driver.findElement(By.linkText("Sign up"));
            signUpButton.click();
            String currentUrl = driver.getCurrentUrl();
            assertThat(currentUrl).contains("register");

            WebElement signUpForm = driver.findElement(By.cssSelector(".auth-page form"));
            assertThat(signUpForm.isDisplayed()).isTrue();

            WebElement userNameField = signUpForm.findElement(By.cssSelector("input[type='text']"));
            userNameField.clear();
            userNameField.sendKeys(userNameExisting);

            WebElement emailField = signUpForm.findElement(By.cssSelector("input[type='email']"));
            emailField.clear();
            emailField.sendKeys(emailExisting);

            WebElement passwordField = signUpForm.findElement(By.cssSelector("input[type='password']"));
            passwordField.clear();
            passwordField.sendKeys("1");

            WebElement signInButton = signUpForm.findElement(By.cssSelector("button[type='submit']"));
            signInButton.click();

            WebElement errorMessage = driver.findElement(By.cssSelector("#main > div > div > div > div > div > ul > li:nth-child(2)"));
            assertThat(errorMessage.isDisplayed()).isTrue();

        }

    @Test (priority=7)
    public void userRegistrationNegativeEmailAndUserAlreadyExist() {
        driver.get("https://react-redux.realworld.io");


        WebElement signUpButton = driver.findElement(By.linkText("Sign up"));
        signUpButton.click();
        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl).contains("register");

        WebElement signUpForm = driver.findElement(By.cssSelector(".auth-page form"));
        assertThat(signUpForm.isDisplayed()).isTrue();

        WebElement userNameField = signUpForm.findElement(By.cssSelector("input[type='text']"));
        userNameField.clear();
        userNameField.sendKeys(userNameExisting);

        WebElement emailField = signUpForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(emailExisting);

        WebElement passwordField = signUpForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement signInButton = signUpForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector("#main > div > div > div > div > div > ul > li:nth-child(1)"));
        assertThat(errorMessage.isDisplayed()).isTrue();

        WebElement errorMessage2 = driver.findElement(By.cssSelector("#main > div > div > div > div > div > ul > li:nth-child(2)"));
        assertThat(errorMessage2.isDisplayed()).isTrue();

    }

    @Test (priority=8)
    public void userRegistrationNegativeInvalidEmail() {
        driver.get("https://react-redux.realworld.io");


        WebElement signUpButton = driver.findElement(By.linkText("Sign up"));
        signUpButton.click();
        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl).contains("register");

        WebElement signUpForm = driver.findElement(By.cssSelector(".auth-page form"));
        assertThat(signUpForm.isDisplayed()).isTrue();

        WebElement userNameField = signUpForm.findElement(By.cssSelector("input[type='text']"));
        userNameField.clear();
        userNameField.sendKeys(userName);

        WebElement emailField = signUpForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys("1@1");

        WebElement passwordField = signUpForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement signInButton = signUpForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector("#main > div > div > div > div > div > ul > li:nth-child(1)"));
        assertThat(errorMessage.isDisplayed()).isTrue();


    }

    @Test (priority=9)
    public void userRegistrationNegativeEmptyEmail() {
        driver.get("https://react-redux.realworld.io");


        WebElement signUpButton = driver.findElement(By.linkText("Sign up"));
        signUpButton.click();
        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl).contains("register");

        WebElement signUpForm = driver.findElement(By.cssSelector(".auth-page form"));
        assertThat(signUpForm.isDisplayed()).isTrue();

        WebElement userNameField = signUpForm.findElement(By.cssSelector("input[type='text']"));
        userNameField.clear();
        userNameField.sendKeys(userName);

        WebElement emailField = signUpForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys("");

        WebElement passwordField = signUpForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement signInButton = signUpForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector("#main > div > div > div > div > div > ul > li:nth-child(1)"));
        assertThat(errorMessage.isDisplayed()).isTrue();


    }

    @Test (priority=9)
    public void userRegistrationNegativeEmptyUserName() {
        driver.get("https://react-redux.realworld.io");


        WebElement signUpButton = driver.findElement(By.linkText("Sign up"));
        signUpButton.click();
        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl).contains("register");

        WebElement signUpForm = driver.findElement(By.cssSelector(".auth-page form"));
        assertThat(signUpForm.isDisplayed()).isTrue();

        WebElement userNameField = signUpForm.findElement(By.cssSelector("input[type='text']"));
        userNameField.clear();
        userNameField.sendKeys("");

        WebElement emailField = signUpForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = signUpForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement signInButton = signUpForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector("#main > div > div > div > div > div > ul > li:nth-child(1)"));
        assertThat(errorMessage.isDisplayed()).isTrue();


    }
    @Test (priority=10)
    public void userRegistrationNegativeEmptyPassword() {
        driver.get("https://react-redux.realworld.io");


        WebElement signUpButton = driver.findElement(By.linkText("Sign up"));
        signUpButton.click();
        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl).contains("register");

        WebElement signUpForm = driver.findElement(By.cssSelector(".auth-page form"));
        assertThat(signUpForm.isDisplayed()).isTrue();

        WebElement userNameField = signUpForm.findElement(By.cssSelector("input[type='text']"));
        userNameField.clear();
        userNameField.sendKeys(userName);

        WebElement emailField = signUpForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = signUpForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys("");

        WebElement signInButton = signUpForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector("#main > div > div > div > div > div > ul > li:nth-child(1)"));
        assertThat(errorMessage.isDisplayed()).isTrue();


    }


    }




