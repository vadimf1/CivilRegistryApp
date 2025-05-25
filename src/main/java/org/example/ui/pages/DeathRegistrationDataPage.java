package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.example.ui.models.User;

import java.time.Duration;

public class DeathRegistrationDataPage {
    private final SelenideElement INPUT_DEATH_DATE = Selenide.$x("//div[label[text() = 'Дата смерти']]/following-sibling::input");
    private final SelenideElement INPUT_DEATH_PLACE = Selenide.$x("//div[label[text() = 'Место смерти']]/following-sibling::input");

    private final PageAction action = new PageAction();

    private void isLoaded() {
        INPUT_DEATH_DATE.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    private void fillDeathDate(String deathDate) {
        INPUT_DEATH_DATE.setValue(deathDate);
    }

    private void fillDeathPlace(String deathPlace) {
        INPUT_DEATH_PLACE.setValue(deathPlace);
    }

    public void clickFinishButton() {
        action.clickFinish();
    }

    public void fillDeathRegistrationDataForm(User user) {
        isLoaded();
        fillDeathDate(user.getDeathDate());
        fillDeathPlace(user.getDeathPlace());
    }
}
