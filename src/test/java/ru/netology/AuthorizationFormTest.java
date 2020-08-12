package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.DataGenerator.*;

public class RegistrationFormTest {

    @BeforeEach
    void setUp(){
        open("http://localhost:7777");
    }

    @Test
    void shouldLogInIfRegisterAndActiveUser() {
        RegistrationForm user = registerActiveUser();
        $("[data-test-id='login'] .input__control").setValue(user.getLogin());
        $("[data-test-id='password'] .input__control").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $("h2").shouldHave(exactText("Личный кабинет"));
    }

    @Test
    void shouldNotLogInIfUnregisterAndBlockedUser() {
        RegistrationForm user = unregisterBlockedUser();
        $("[data-test-id='login'] .input__control").setValue(user.getLogin());
        $("[data-test-id='password'] .input__control").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $(".notification__content").shouldHave(exactText("Ошибка! Пользователь заблокирован"));
    }

    @Test
    void shouldNotLogInIfWrongUserLogin() {
        RegistrationForm user = unregisterWithWrongLogin();
        $("[data-test-id='login'] .input__control").setValue(user.getLogin());
        $("[data-test-id='password'] .input__control").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $(".notification__content").shouldHave(exactText("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldNotLogInIfWrongPassword() {
        RegistrationForm user = unregisterWithWrongPassword();
        $("[data-test-id='login'] .input__control").setValue(user.getLogin());
        $("[data-test-id='password'] .input__control").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $(".notification__content").shouldHave(exactText("Ошибка! Неверно указан логин или пароль"));
    }
}
