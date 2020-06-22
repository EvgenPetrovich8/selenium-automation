package io.react.realworld.api;

import com.hillel.selenium.auto.user.UserApi;
import com.hillel.selenium.auto.user.User;
import com.hillel.selenium.auto.utils.UserData;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;



public class RegistrationTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://conduit.productionready.io";
        RestAssured.basePath = "/api";
        RestAssured.requestSpecification =
                new RequestSpecBuilder()
                        .setAccept(ContentType.JSON)
                        .setContentType(ContentType.JSON)
                        .log(LogDetail.ALL)
                        .build();
    }

    @Test
    public void registrationUserTest() {
        User user = UserData.randomUser();
        UserApi userApi = new UserApi();
        userApi.setUser(user);

        User newUser = RestAssured
                .given()
                .body(userApi)
                .when()
                .post("/users")
                .then()
                .statusCode(200)
                .extract().body()
                .as(UserApi.class)
                .getUser();

        assertThat(newUser.getEmail()).isEqualToIgnoringCase(user.getEmail());
        assertThat(newUser.getUsername()).isEqualToIgnoringCase(user.getUsername());
    }



}
