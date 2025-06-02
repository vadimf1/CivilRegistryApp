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
        log.info("Проверка загрузки страницы выбора услуги");
        MARRIAGE_REGISTRATION_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10));
        BIRTH_REGISTRATION_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10));
        DEATH_REGISTRATION_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Step("Выбор услуги: Регистрация брака")
    public void chooseMarriageRegistration() {
        log.info("Выбор услуги: Регистрация брака");
        isLoaded();
        MARRIAGE_REGISTRATION_BUTTON.click();
    }

    @Step("Выбор услуги: Регистрация рождения")
    public void chooseBirthRegistration() {
        log.info("Выбор услуги: Регистрация рождения");
        isLoaded();
        BIRTH_REGISTRATION_BUTTON.click();
    }

    @Step("Выбор услуги: Регистрация смерти")
    public void chooseDeathRegistration() {
        log.info("Выбор услуги: Регистрация смерти");
        isLoaded();
        DEATH_REGISTRATION_BUTTON.click();
    }
}
