package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.example.models.User;

import java.time.Duration;

@Slf4j
public class DeathRegistrationDataPage {
    private final SelenideElement INPUT_DEATH_DATE = Selenide.$x("//div[label[text() = 'Дата смерти']]/following-sibling::input");
    private final SelenideElement INPUT_DEATH_PLACE = Selenide.$x("//div[label[text() = 'Место смерти']]/following-sibling::input");

    @Step("Проверка загрузки страницы регистрации смерти")
    private void isLoaded() {
        INPUT_DEATH_DATE.shouldBe(Condition.visible, Duration.ofSeconds(10));
        log.info("Проверка загрузки страницы регистрации смерти");
    }

    @Step("Ввод даты смерти: {deathDate}")
    private void fillDeathDate(String deathDate) {
        INPUT_DEATH_DATE.setValue(deathDate);
        log.info("Ввод даты смерти: {}", deathDate);
    }

    @Step("Ввод места смерти: {deathPlace}")
    private void fillDeathPlace(String deathPlace) {
        INPUT_DEATH_PLACE.setValue(deathPlace);
        log.info("Ввод места смерти: {}", deathPlace);
    }

    @Step("Заполнение формы регистрации смерти")
    public void fillDeathRegistrationDataForm(User user) {
        isLoaded();
        fillDeathDate(user.getDeathDate());
        fillDeathPlace(user.getDeathPlace());
        log.info("Заполнение формы регистрации смерти");
    }
}
