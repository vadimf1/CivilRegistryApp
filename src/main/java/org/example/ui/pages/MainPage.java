package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

public class MainPage {
    private final SelenideElement USER_LOGIN_BUTTON = Selenide.$x("//button[text()='Войти как пользователь']");
    private final SelenideElement ADMIN_LOGIN_BUTTON = Selenide.$x("//button[text()='Войти как администратор']");

    public void openMainPage() {
        Selenide.open("https://regoffice.senla.eu","","user","senlatest");
    }

    private void isLoaded() {
        USER_LOGIN_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10));
        ADMIN_LOGIN_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void enterAsUser() {
        isLoaded();
        USER_LOGIN_BUTTON.click();
    }

    public void enterAsAdmin() {
        isLoaded();
        ADMIN_LOGIN_BUTTON.click();
    }
}
