package ru.netology;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.val;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(7777)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void setUpAll(AuthorizationForm authorizationForm) {
        given()
                .spec(requestSpec)
                .body(authorizationForm)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static AuthorizationForm registerActiveUser(){
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().fullName();
        String password = faker.internet().password();
        val authorizationForm = new AuthorizationForm(login, password,"active");
        setUpAll(authorizationForm);
        return authorizationForm;
    }

    public static AuthorizationForm unregisterBlockedUser(){
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().fullName();
        String password = faker.internet().password();
        val authorizationForm = new AuthorizationForm(login, password,"blocked");
        setUpAll(authorizationForm);
        return authorizationForm;
    }

    public static AuthorizationForm unregisterWithWrongLogin(){
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().fullName();
        String password = faker.internet().password();
        val authorizationForm = new AuthorizationForm(login,password,"active");
        setUpAll(authorizationForm);
        return new AuthorizationForm("Ivan", password, "active");
    }

    public static AuthorizationForm unregisterWithWrongPassword(){
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String password = faker.internet().password();
        val authorizationForm = new AuthorizationForm(login, password,"active");
        setUpAll(authorizationForm);
        return new AuthorizationForm(login, "abcde", "active");
    }
}
