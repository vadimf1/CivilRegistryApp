package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class ServiceSelectionPage {
    private final SelenideElement MARRIAGE_REGISTRATION_BUTTON = Selenide.$x("//button[text() = 'Регистрация брака']");
    private final SelenideElement BIRTH_REGISTRATION_BUTTON = Selenide.$x("//button[text() = 'Регистрация рождения']");
    private final SelenideElement DEATH_REGISTRATION_BUTTON = Selenide.$x("//button[text() = 'Регистрация смерти']");

    @Step("Проверка загрузки страницы выбора услуги")
    private void isLoaded() {
        MARRIAGE_REGISTRATION_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10));
        BIRTH_REGISTRATION_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10));
        DEATH_REGISTRATION_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10));
        log.info("Проверка загрузки страницы выбора услуги");
    }

    @Step("Выбор услуги: Регистрация брака")
    public void chooseMarriageRegistration() {
        isLoaded();
        MARRIAGE_REGISTRATION_BUTTON.click();
        log.info("Выбор услуги: Регистрация брака");
    }

    @Step("Выбор услуги: Регистрация рождения")
    public void chooseBirthRegistration() {
        isLoaded();
        BIRTH_REGISTRATION_BUTTON.click();
        log.info("Выбор услуги: Регистрация рождения");
    }

    @Step("Выбор услуги: Регистрация смерти")
    public void chooseDeathRegistration() {
        isLoaded();
        DEATH_REGISTRATION_BUTTON.click();
        log.info("Выбор услуги: Регистрация смерти");
    }
}
