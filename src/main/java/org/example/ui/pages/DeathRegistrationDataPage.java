package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.models.User;

import java.time.Duration;

public class DeathRegistrationDataPage {
    private final SelenideElement INPUT_DEATH_DATE = Selenide.$x("//div[label[text() = 'Дата смерти']]/following-sibling::input");
    private final SelenideElement INPUT_DEATH_PLACE = Selenide.$x("//div[label[text() = 'Место смерти']]/following-sibling::input");

    @Step("Проверка загрузки страницы регистрации смерти")
    private void isLoaded() {
        INPUT_DEATH_DATE.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Step("Ввод даты смерти: {deathDate}")
    private void fillDeathDate(String deathDate) {
        INPUT_DEATH_DATE.setValue(deathDate);
    }

    @Step("Ввод места смерти: {deathPlace}")
    private void fillDeathPlace(String deathPlace) {
        INPUT_DEATH_PLACE.setValue(deathPlace);
    }

    @Step("Заполнение формы регистрации смерти")
    public void fillDeathRegistrationDataForm(User user) {
        isLoaded();
        fillDeathDate(user.getDeathDate());
        fillDeathPlace(user.getDeathPlace());
    }
}
