package eu.senla.regoffice.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import eu.senla.regoffice.models.User;

import java.time.Duration;

@Slf4j
public class ApplicantDataPage {
    private final SelenideElement INPUT_LAST_NAME = Selenide.$x("//div[label[text() = 'Фамилия']]/following-sibling::input");
    private final SelenideElement INPUT_FIRST_NAME = Selenide.$x("//div[label[text() = 'Имя']]/following-sibling::input");
    private final SelenideElement INPUT_MIDDLE_NAME = Selenide.$x("//div[label[text() = 'Отчество']]/following-sibling::input");
    private final SelenideElement INPUT_PHONE_NUMBER = Selenide.$x("//div[label[text() = 'Телефон']]/following-sibling::input");
    private final SelenideElement INPUT_PASSPORT_NUMBER = Selenide.$x("//div[label[text() = 'Номер паспорта']]/following-sibling::input");
    private final SelenideElement INPUT_ADDRESS = Selenide.$x("//div[label[text() = 'Адрес прописки']]/following-sibling::input");
    private final SelenideElement PAGE_TITLE = Selenide.$x("//span[normalize-space(string(.)) = 'Вы вошли как Пользоватиль']");

    @Step("Проверка загрузки страницы ввода данных заявителя")
    private void isLoaded() {
        PAGE_TITLE.shouldBe(Condition.visible, Duration.ofSeconds(10));
        INPUT_LAST_NAME.shouldBe(Condition.visible, Duration.ofSeconds(10));
        log.info("Проверка загрузки страницы ввода данных заявителя");
    }

    @Step("Ввод фамилии: {lastName}")
    private void fillLastName(String lastName) {
        INPUT_LAST_NAME.setValue(lastName);
        log.info("Ввод фамилии: {}", lastName);
    }

    @Step("Ввод имени: {firstName}")
    private void fillFirstName(String firstName) {
        INPUT_FIRST_NAME.setValue(firstName);
        log.info("Ввод имени: {}", firstName);
    }

    @Step("Ввод отчества: {middleName}")
    private void fillMiddleName(String middleName) {
        INPUT_MIDDLE_NAME.setValue(middleName);
        log.info("Ввод отчества: {}", middleName);
    }

    @Step("Ввод телефона: {phoneNumber}")
    private void fillPhoneNumber(String phoneNumber) {
        INPUT_PHONE_NUMBER.setValue(phoneNumber);
        log.info("Ввод телефона: {}", phoneNumber);
    }

    @Step("Ввод номера паспорта: {passportNumber}")
    private void fillPassportNumber(String passportNumber) {
        INPUT_PASSPORT_NUMBER.setValue(passportNumber);
        log.info("Ввод номера паспорта: {}", passportNumber);
    }

    @Step("Ввод адреса: {address}")
    private void fillAddress(String address) {
        INPUT_ADDRESS.setValue(address);
        log.info("Ввод адреса: {}", address);
    }

    @Step("Заполнение формы заявителя")
    public void fillApplicantDataForm(User user) {
        isLoaded();
        fillLastName(user.getApplicantLastName());
        fillFirstName(user.getApplicantFirstName());
        fillMiddleName(user.getApplicantMiddleName());
        fillPhoneNumber(user.getApplicantPhoneNumber());
        fillPassportNumber(user.getApplicantPassportNumber());
        fillAddress(user.getApplicantAddress());
        log.info("Заполнение формы заявителя");
    }
}
