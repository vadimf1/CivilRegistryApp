package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.utils.PropertyUtil;

import java.time.Duration;

public class MainPage {
    private final String BASE_URI_KEY = "baseUri";
    private static final String LOGIN_KEY = "login";
    private static final String PASSWORD_KEY = "password";
    private final SelenideElement USER_LOGIN_BUTTON = Selenide.$x("//button[text()='Войти как пользователь']");
    private final SelenideElement ADMIN_LOGIN_BUTTON = Selenide.$x("//button[text()='Войти как администратор']");

    @Step("Открытие главной страницы")
    public void openMainPage() {
        Selenide.open(PropertyUtil.getProperty(BASE_URI_KEY), "", System.getProperty(LOGIN_KEY), System.getProperty(PASSWORD_KEY));
    }

    @Step("Проверка, что главная страница загружена")
    private void isLoaded() {
        USER_LOGIN_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10));
        ADMIN_LOGIN_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Step("Вход как пользователь")
    public void enterAsUser() {
        isLoaded();
        USER_LOGIN_BUTTON.click();
    }

    @Step("Вход как администратор")
    public void enterAsAdmin() {
        isLoaded();
        ADMIN_LOGIN_BUTTON.click();
    }
}
