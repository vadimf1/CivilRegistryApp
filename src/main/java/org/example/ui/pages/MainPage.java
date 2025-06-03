package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.PropertyUtil;

import java.time.Duration;

@Slf4j
public class MainPage {
    private static final String BASE_URI_KEY = "baseUri";
    private static final String LOGIN_KEY = "app.login";
    private static final String PASSWORD_KEY = "app.password";

    private final SelenideElement USER_LOGIN_BUTTON = Selenide.$x("//button[text()='Войти как пользователь']");
    private final SelenideElement ADMIN_LOGIN_BUTTON = Selenide.$x("//button[text()='Войти как администратор']");

    @Step("Открытие главной страницы")
    public void openMainPage() {
        log.info("Открытие главной страницы");
        Selenide.open(PropertyUtil.getProperty(BASE_URI_KEY), "", System.getProperty(LOGIN_KEY), System.getProperty(PASSWORD_KEY));
    }

    @Step("Проверка, что главная страница загружена")
    private void isLoaded() {
        log.info("Проверка, что главная страница загружена");
        USER_LOGIN_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10));
        ADMIN_LOGIN_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Step("Вход как пользователь")
    public void enterAsUser() {
        log.info("Вход как пользователь");
        isLoaded();
        USER_LOGIN_BUTTON.click();
    }

    @Step("Вход как администратор")
    public void enterAsAdmin() {
        log.info("Вход как администратор");
        isLoaded();
        ADMIN_LOGIN_BUTTON.click();
    }
}
