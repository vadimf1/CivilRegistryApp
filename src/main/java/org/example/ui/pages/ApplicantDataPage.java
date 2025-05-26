package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.example.models.User;

import java.time.Duration;

public class ApplicantDataPage {
    private final SelenideElement INPUT_LAST_NAME = Selenide.$x("//div[label[text() = 'Фамилия']]/following-sibling::input");
    private final SelenideElement INPUT_FIRST_NAME = Selenide.$x("//div[label[text() = 'Имя']]/following-sibling::input");
    private final SelenideElement INPUT_MIDDLE_NAME = Selenide.$x("//div[label[text() = 'Отчество']]/following-sibling::input");
    private final SelenideElement INPUT_PHONE_NUMBER = Selenide.$x("//div[label[text() = 'Телефон']]/following-sibling::input");
    private final SelenideElement INPUT_PASSPORT_NUMBER = Selenide.$x("//div[label[text() = 'Номер паспорта']]/following-sibling::input");
    private final SelenideElement INPUT_ADDRESS = Selenide.$x("//div[label[text() = 'Адрес прописки']]/following-sibling::input");
    private final SelenideElement PAGE_TITLE = Selenide.$x("//span[normalize-space(string(.)) = 'Вы вошли как Пользоватиль']");

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

    private void fillPhoneNumber(String phoneNumber) {
        INPUT_PHONE_NUMBER.setValue(phoneNumber);
    }

    private void fillPassportNumber(String passportNumber) {
        INPUT_PASSPORT_NUMBER.setValue(passportNumber);
    }

    private void fillAddress(String address) {
        INPUT_ADDRESS.setValue(address);
    }

    public void fillApplicantDataForm(User user) {
        isLoaded();
        fillLastName(user.getApplicantLastName());
        fillFirstName(user.getApplicantFirstName());
        fillMiddleName(user.getApplicantMiddleName());
        fillPhoneNumber(user.getApplicantPhoneNumber());
        fillPassportNumber(user.getApplicantPassportNumber());
        fillAddress(user.getApplicantAddress());
    }
}
