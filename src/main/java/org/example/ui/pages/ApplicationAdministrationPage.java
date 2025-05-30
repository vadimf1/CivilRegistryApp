package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

public class ApplicationAdministrationPage {
    private final SelenideElement INFO_TABLE = Selenide.$x("//table[contains(@class, 'MuiTable-root')]");

    @Step("Проверка загрузки таблицы заявок")
    public void checkIsLoaded() {
        INFO_TABLE.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Step("Одобрение заявки с номером: {number}")
    public void approveApplicationByNumber(String number) {
        checkIsLoaded();
        getRowByApplicationNumber(number)
                .$x(".//button[1]")
                .click();
    }

    @Step("Отклонение заявки с номером: {number}")
    public void rejectApplicationByNumber(String number) {
        checkIsLoaded();
        getRowByApplicationNumber(number)
                .$x(".//button[2]")
                .click();
    }

    @Step("Проверка, что заявка с номером {number} одобрена")
    public void checkApplicationIsApproved(String number) {
        checkIsLoaded();
        getRowByApplicationNumber(number)
                .$x("./td[last()-1]")
                .shouldHave(Condition.text("Одобрена"), Duration.ofSeconds(10));
    }

    @Step("Проверка, что заявка с номером {number} отклонена")
    public void checkApplicationIsRejected(String number) {
        checkIsLoaded();
        getRowByApplicationNumber(number)
                .$x("./td[last()-1]")
                .shouldHave(Condition.text("Отклонена"), Duration.ofSeconds(10));
    }

    @Step("Поиск строки по номеру заявки: {number}")
    private SelenideElement getRowByApplicationNumber(String number) {
        return Selenide.$x("//table//tr[td[1][normalize-space(text()) = '" + number + "']]");
    }
}
