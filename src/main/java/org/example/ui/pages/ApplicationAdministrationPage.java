package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.Random;

public class ApplicationAdministrationPage {
    public final ElementsCollection APPLICATION_ROWS = Selenide.$$x("//table//tr[position() > 1]");
    private final SelenideElement INFO_TABLE = Selenide.$x("//table[contains(@class, 'MuiTable-root')]");

    @Step("Проверка загрузки таблицы заявок")
    public void checkIsLoaded() {
        INFO_TABLE.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Step("Выбор случайной заявки из таблицы")
    public String getRandomApplicationNumber() {
        checkIsLoaded();

        if (APPLICATION_ROWS.isEmpty()) {
            throw new IllegalStateException("Нет доступных заявок в таблице.");
        }

        int randomIndex = new Random().nextInt(APPLICATION_ROWS.size());
        SelenideElement randomRow = APPLICATION_ROWS.get(randomIndex);

        String number = randomRow.$x("./td[1]").getText().trim();
        return number;
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
