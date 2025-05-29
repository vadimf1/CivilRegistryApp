package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.models.User;

import java.time.Duration;

public class MarriageRegistrationDataPage {
    private final SelenideElement INPUT_REGISTRATION_DATE = Selenide.$x("//div[label[text() = 'Дата регистрации']]/following-sibling::input");
    private final SelenideElement INPUT_NEW_LAST_NAME = Selenide.$x("//div[label[text() = 'Новая фамилия']]/following-sibling::input");
    private final SelenideElement INPUT_SPOUSE_LAST_NAME = Selenide.$x("//div[label[text() = 'Фамилия супруга/и']]/following-sibling::input");
    private final SelenideElement INPUT_SPOUSE_FIRST_NAME = Selenide.$x("//div[label[text() = 'Имя супруга/и']]/following-sibling::input");
    private final SelenideElement INPUT_SPOUSE_MIDDLE_NAME = Selenide.$x("//div[label[text() = 'Отчество супруга/и']]/following-sibling::input");
    private final SelenideElement INPUT_SPOUSE_BIRTH_DATE = Selenide.$x("//div[label[text() = 'Дата рождения супруга/и']]/following-sibling::input");
    private final SelenideElement INPUT_SPOUSE_PASSPORT_NUMBER = Selenide.$x("//div[label[text() = 'Номер паспорта супруга/и']]/following-sibling::input");

    @Step("Проверка загрузки страницы регистрации брака")
    private void isLoaded() {
        INPUT_REGISTRATION_DATE.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Step("Ввод даты регистрации: {registrationDate}")
    private void fillRegistrationDate(String registrationDate) {
        INPUT_REGISTRATION_DATE.setValue(registrationDate);
    }

    @Step("Ввод новой фамилии: {newLastName}")
    private void fillNewLastName(String newLastName) {
        INPUT_NEW_LAST_NAME.setValue(newLastName);
    }

    @Step("Ввод фамилии супруга/и: {spouseLastName}")
    private void fillSpouseLastName(String spouseLastName) {
        INPUT_SPOUSE_LAST_NAME.setValue(spouseLastName);
    }

    @Step("Ввод имени супруга/и: {spouseFirstName}")
    private void fillSpouseFirstName(String spouseFirstName) {
        INPUT_SPOUSE_FIRST_NAME.setValue(spouseFirstName);
    }

    @Step("Ввод отчества супруга/и: {spouseMiddleName}")
    private void fillSpouseMiddleName(String spouseMiddleName) {
        INPUT_SPOUSE_MIDDLE_NAME.setValue(spouseMiddleName);
    }

    @Step("Ввод даты рождения супруга/и: {spouseBirthDate}")
    private void fillSpouseBirthDate(String spouseBirthDate) {
        INPUT_SPOUSE_BIRTH_DATE.setValue(spouseBirthDate);
    }

    @Step("Ввод номера паспорта супруга/и: {spousePassportNumber}")
    private void fillSpousePassportNumber(String spousePassportNumber) {
        INPUT_SPOUSE_PASSPORT_NUMBER.setValue(spousePassportNumber);
    }

    @Step("Заполнение формы регистрации брака")
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
