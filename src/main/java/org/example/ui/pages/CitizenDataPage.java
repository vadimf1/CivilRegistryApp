package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.example.models.User;

import java.time.Duration;

@Slf4j
public class CitizenDataPage {
    private final SelenideElement INPUT_LAST_NAME = Selenide.$x("//div[label[text() = 'Фамилия']]/following-sibling::input");
    private final SelenideElement INPUT_FIRST_NAME = Selenide.$x("//div[label[text() = 'Имя']]/following-sibling::input");
    private final SelenideElement INPUT_MIDDLE_NAME = Selenide.$x("//div[label[text() = 'Отчество']]/following-sibling::input");
    private final SelenideElement INPUT_BIRTH_DATE = Selenide.$x("//div[label[text() = 'Дата рождения']]/following-sibling::input");
    private final SelenideElement INPUT_PASSPORT_NUMBER = Selenide.$x("//div[label[text() = 'Номер паспорта']]/following-sibling::input");
    private final SelenideElement INPUT_GENDER = Selenide.$x("//div[label[text() = 'Пол']]/following-sibling::input");
    private final SelenideElement INPUT_ADDRESS = Selenide.$x("//div[label[text() = 'Адрес прописки']]/following-sibling::input");

    @Step("Проверка загрузки страницы ввода данных гражданина")
    private void isLoaded() {
        INPUT_LAST_NAME.shouldBe(Condition.visible, Duration.ofSeconds(10));
        log.info("Проверка загрузки страницы ввода данных гражданина");
    }

    @Step("Ввод фамилии гражданина: {lastName}")
    private void fillLastName(String lastName) {
        INPUT_LAST_NAME.setValue(lastName);
        log.info("Ввод фамилии гражданина: {}", lastName);
    }

    @Step("Ввод имени гражданина: {firstName}")
    private void fillFirstName(String firstName) {
        INPUT_FIRST_NAME.setValue(firstName);
        log.info("Ввод имени гражданина: {}", firstName);
    }

    @Step("Ввод отчества гражданина: {middleName}")
    private void fillMiddleName(String middleName) {
        INPUT_MIDDLE_NAME.setValue(middleName);
        log.info("Ввод отчества гражданина: {}", middleName);
    }

    @Step("Ввод даты рождения: {birthDate}")
    private void fillBirthDate(String birthDate) {
        INPUT_BIRTH_DATE.setValue(birthDate);
        log.info("Ввод даты рождения: {}", birthDate);
    }

    @Step("Ввод номера паспорта: {passportNumber}")
    private void fillPassportNumber(String passportNumber) {
        INPUT_PASSPORT_NUMBER.setValue(passportNumber);
        log.info("Ввод номера паспорта: {}", passportNumber);
    }

    @Step("Ввод пола: {gender}")
    private void fillGender(String gender) {
        INPUT_GENDER.setValue(gender);
        log.info("Ввод пола: {}", gender);
    }

    @Step("Ввод адреса прописки: {address}")
    private void fillAddress(String address) {
        INPUT_ADDRESS.setValue(address);
        log.info("Ввод адреса прописки: {}", address);
    }

    @Step("Заполнение формы данных гражданина")
    public void fillCitizenDataForm(User user) {
        isLoaded();
        fillLastName(user.getCitizenLastName());
        fillFirstName(user.getCitizenFirstName());
        fillMiddleName(user.getCitizenMiddleName());
        fillBirthDate(user.getCitizenBirthDate());
        fillPassportNumber(user.getCitizenPassportNumber());
        fillGender(user.getCitizenGender());
        fillAddress(user.getCitizenAddress());
        log.info("Заполнение формы данных гражданина");
    }
}
