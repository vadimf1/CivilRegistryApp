package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.example.ui.models.Admin;

import java.time.Duration;

public class AdminRegistrationDataPage {
    private final SelenideElement INPUT_LAST_NAME = Selenide.$x("//div[label[text() = 'Фамилия']]/following-sibling::input");
    private final SelenideElement INPUT_FIRST_NAME = Selenide.$x("//div[label[text() = 'Имя']]/following-sibling::input");
    private final SelenideElement INPUT_MIDDLE_NAME = Selenide.$x("//div[label[text() = 'Отчество']]/following-sibling::input");
    private final SelenideElement INPUT_PHONE_NUMBER = Selenide.$x("//div[label[text() = 'Телефон']]/following-sibling::input");
    private final SelenideElement INPUT_PASSPORT_NUMBER = Selenide.$x("//div[label[text() = 'Номер паспорта']]/following-sibling::input");
    private final SelenideElement INPUT_BIRTH_DATE = Selenide.$x("//div[label[text() = 'Дата рождения']]/following-sibling::input");
    private final SelenideElement PAGE_TITLE = Selenide.$x(
            "//h2[contains(@class, 'MuiTypography-root')]//span[normalize-space(string(.)) = 'Вы вошли как Aдминистратор']"
    );

    private final PageAction action = new PageAction();

    private void isLoaded() {
        PAGE_TITLE.shouldBe(Condition.visible, Duration.ofSeconds(10));
        INPUT_LAST_NAME.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    private void fillLastName(String lastName) {
        INPUT_LAST_NAME.setValue(lastName);
    }

    private void fillFirstName(String firstName) {
        INPUT_FIRST_NAME.setValue(firstName);
    }

    private void fillMiddleName(String middleName) {
        INPUT_MIDDLE_NAME.setValue(middleName);
    }

    public void fillPhoneNumber(String phoneNumber) {
        INPUT_PHONE_NUMBER.setValue(phoneNumber);
    }

    private void fillPassportNumber(String passportNumber) {
        INPUT_PASSPORT_NUMBER.setValue(passportNumber);
    }

    private void fillBirthDate(String birthDate) {
        INPUT_BIRTH_DATE.setValue(birthDate);
    }

    public void clickNextButton() {
        action.clickNext();
    }

    public void fillAdminRegistrationDataForm(Admin admin) {
        isLoaded();
        fillLastName(admin.getLastName());
        fillFirstName(admin.getFirstName());
        fillMiddleName(admin.getMiddleName());
        fillPhoneNumber(admin.getPhoneNumber());
        fillPassportNumber(admin.getPassportNumber());
        fillBirthDate(admin.getBirthDate());
    }
}
