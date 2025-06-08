package eu.senla.regoffice.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import eu.senla.regoffice.utils.PropertyUtil;

import java.time.Duration;

@Slf4j
public class MainPage {
    private final SelenideElement USER_LOGIN_BUTTON = Selenide.$x("//button[text()='Войти как пользователь']");
    private final SelenideElement ADMIN_LOGIN_BUTTON = Selenide.$x("//button[text()='Войти как администратор']");

    @Step("Открытие главной страницы")
    public void openMainPage() {
        Selenide.open(Configuration.baseUrl, "", System.getProperty("app.login"), System.getProperty("app.password"));
        log.info("Открытие главной страницы");
    }

    @Step("Проверка, что главная страница загружена")
    private void isLoaded() {
        USER_LOGIN_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10));
        ADMIN_LOGIN_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10));
        log.info("Проверка, что главная страница загружена");
    }

    @Step("Вход как пользователь")
    public void enterAsUser() {
        isLoaded();
        USER_LOGIN_BUTTON.click();
        log.info("Вход как пользователь");
    }

    @Step("Вход как администратор")
    public void enterAsAdmin() {
        isLoaded();
        ADMIN_LOGIN_BUTTON.click();
        log.info("Вход как администратор");
    }
}
