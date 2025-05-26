package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

public class ServiceSelectionPage {
    private final SelenideElement MARRIAGE_REGISTRATION_BUTTON = Selenide.$x("//button[text() = 'Регистрация брака']");
    private final SelenideElement BIRTH_REGISTRATION_BUTTON = Selenide.$x("//button[text() = 'Регистрация рождения']");
    private final SelenideElement DEATH_REGISTRATION_BUTTON = Selenide.$x("//button[text() = 'Регистрация смерти']");

    private void isLoaded() {
        MARRIAGE_REGISTRATION_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10));
        BIRTH_REGISTRATION_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10));
        DEATH_REGISTRATION_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void chooseMarriageRegistration() {
        isLoaded();
        MARRIAGE_REGISTRATION_BUTTON.click();
    }

    public void chooseBirthRegistration() {
        isLoaded();
        BIRTH_REGISTRATION_BUTTON.click();
    }

    public void chooseDeathRegistration() {
        isLoaded();
        DEATH_REGISTRATION_BUTTON.click();
    }
}
