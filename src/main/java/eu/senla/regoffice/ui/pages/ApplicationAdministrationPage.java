package eu.senla.regoffice.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class ApplicationAdministrationPage {
    private final SelenideElement INFO_TABLE = Selenide.$x("//table[contains(@class, 'MuiTable-root')]");

    @Step("Проверка загрузки таблицы заявок")
    public void checkIsLoaded() {
        INFO_TABLE.shouldBe(Condition.visible, Duration.ofSeconds(10));
        log.info("Проверка загрузки таблицы заявок");
    }

    @Step("Одобрение заявки с номером: {number}")
    public void approveApplicationByNumber(String number) {
        checkIsLoaded();
        getRowByApplicationNumber(number)
                .$x(".//button[1]")
                .click();
        log.info("Одобрение заявки с номером: {}", number);
    }

    @Step("Отклонение заявки с номером: {number}")
    public void rejectApplicationByNumber(String number) {
        checkIsLoaded();
        getRowByApplicationNumber(number)
                .$x(".//button[2]")
                .click();
        log.info("Отклонение заявки с номером: {}", number);
    }

    @Step("Проверка, что заявка с номером {number} одобрена")
    public void checkApplicationIsApproved(String number) {
        checkIsLoaded();
        getRowByApplicationNumber(number)
                .$x("./td[last()-1]")
                .shouldHave(Condition.text("Одобрена"), Duration.ofSeconds(10));
        log.info("Проверка, что заявка с номером {} одобрена", number);
    }

    @Step("Проверка, что заявка с номером {number} отклонена")
    public void checkApplicationIsRejected(String number) {
        checkIsLoaded();
        getRowByApplicationNumber(number)
                .$x("./td[last()-1]")
                .shouldHave(Condition.text("Отклонена"), Duration.ofSeconds(10));
        log.info("Проверка, что заявка с номером {} отклонена", number);
    }

    @Step("Поиск строки по номеру заявки: {number}")
    private SelenideElement getRowByApplicationNumber(String number) {
        SelenideElement element = Selenide.$x("//table//tr[td[1][normalize-space(text()) = '" + number + "']]");
        log.info("Поиск строки по номеру заявки: {}", number);
        return element;
    }
}
