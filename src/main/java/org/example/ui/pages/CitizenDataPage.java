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
        log.info("Проверка загрузки страницы ввода данных гражданина");
        INPUT_LAST_NAME.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Step("Ввод фамилии гражданина: {lastName}")
    private void fillLastName(String lastName) {
        log.info("Ввод фамилии гражданина: {}", lastName);
        INPUT_LAST_NAME.setValue(lastName);
    }

    @Step("Ввод имени гражданина: {firstName}")
    private void fillFirstName(String firstName) {
        log.info("Ввод имени гражданина: {}", firstName);
        INPUT_FIRST_NAME.setValue(firstName);
    }

    @Step("Ввод отчества гражданина: {middleName}")
    private void fillMiddleName(String middleName) {
        log.info("Ввод отчества гражданина: {}", middleName);
        INPUT_MIDDLE_NAME.setValue(middleName);
    }

    @Step("Ввод даты рождения: {birthDate}")
    private void fillBirthDate(String birthDate) {
        log.info("Ввод даты рождения: {}", birthDate);
        INPUT_BIRTH_DATE.setValue(birthDate);
    }

    @Step("Ввод номера паспорта: {passportNumber}")
    private void fillPassportNumber(String passportNumber) {
        log.info("Ввод номера паспорта: {}", passportNumber);
        INPUT_PASSPORT_NUMBER.setValue(passportNumber);
    }

    @Step("Ввод пола: {gender}")
    private void fillGender(String gender) {
        log.info("Ввод пола: {}", gender);
        INPUT_GENDER.setValue(gender);
    }

    @Step("Ввод адреса прописки: {address}")
    private void fillAddress(String address) {
        log.info("Ввод адреса прописки: {}", address);
        INPUT_ADDRESS.setValue(address);
    }

    @Step("Заполнение формы данных гражданина")
    public void fillCitizenDataForm(User user) {
        log.info("Заполнение формы данных гражданина");
        isLoaded();
        fillLastName(user.getCitizenLastName());
        fillFirstName(user.getCitizenFirstName());
        fillMiddleName(user.getCitizenMiddleName());
        fillBirthDate(user.getCitizenBirthDate());
        fillPassportNumber(user.getCitizenPassportNumber());
        fillGender(user.getCitizenGender());
        fillAddress(user.getCitizenAddress());
    }
}
