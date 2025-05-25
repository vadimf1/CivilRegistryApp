package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.example.ui.models.User;

import java.time.Duration;

public class MarriageRegistrationDataPage {
    private final SelenideElement INPUT_REGISTRATION_DATE = Selenide.$x("//div[label[text() = 'Дата регистрации']]/following-sibling::input");
    private final SelenideElement INPUT_NEW_LAST_NAME = Selenide.$x("//div[label[text() = 'Новая фамилия']]/following-sibling::input");
    private final SelenideElement INPUT_SPOUSE_LAST_NAME = Selenide.$x("//div[label[text() = 'Фамилия супруга/и']]/following-sibling::input");
    private final SelenideElement INPUT_SPOUSE_FIRST_NAME = Selenide.$x("//div[label[text() = 'Имя супруга/и']]/following-sibling::input");
    private final SelenideElement INPUT_SPOUSE_MIDDLE_NAME = Selenide.$x("//div[label[text() = 'Отчество супруга/и']]/following-sibling::input");
    private final SelenideElement INPUT_SPOUSE_BIRTH_DATE = Selenide.$x("//div[label[text() = 'Дата рождения супруга/и']]/following-sibling::input");
    private final SelenideElement INPUT_SPOUSE_PASSPORT_NUMBER = Selenide.$x("//div[label[text() = 'Номер паспорта супруга/и']]/following-sibling::input");

    private final PageAction action = new PageAction();

    private void isLoaded() {
        INPUT_REGISTRATION_DATE.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    private void fillRegistrationDate(String registrationDate) {
        INPUT_REGISTRATION_DATE.setValue(registrationDate);
    }

    private void fillNewLastName(String newLastName) {
        INPUT_NEW_LAST_NAME.setValue(newLastName);
    }

    private void fillSpouseLastName(String spouseLastName) {
        INPUT_SPOUSE_LAST_NAME.setValue(spouseLastName);
    }

    private void fillSpouseFirstName(String spouseFirstName) {
        INPUT_SPOUSE_FIRST_NAME.setValue(spouseFirstName);
    }

    private void fillSpouseMiddleName(String spouseMiddleName) {
        INPUT_SPOUSE_MIDDLE_NAME.setValue(spouseMiddleName);
    }

    private void fillSpouseBirthDate(String spouseBirthDate) {
        INPUT_SPOUSE_BIRTH_DATE.setValue(spouseBirthDate);
    }

    private void fillSpousePassportNumber(String spousePassportNumber) {
        INPUT_SPOUSE_PASSPORT_NUMBER.setValue(spousePassportNumber);
    }

    public void clickFinishButton() {
        action.clickFinish();
    }

    public void fillMarriageRegistrationDataForm(User user) {
        isLoaded();
        fillRegistrationDate(user.getMarriageRegistrationDate());
        fillNewLastName(user.getNewLastName());
        fillSpouseLastName(user.getSpouseLastName());
        fillSpouseFirstName(user.getSpouseFirstName());
        fillSpouseMiddleName(user.getSpouseMiddleName());
        fillSpouseBirthDate(user.getSpouseBirthDate());
        fillSpousePassportNumber(user.getSpousePassportNumber());
    }
}
