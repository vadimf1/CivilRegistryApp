package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.Random;

public class ApplicationAdministrationPage {
    public final ElementsCollection APPLICATION_ROWS = Selenide.$$x("//table//tr[position() > 1]");
    private final SelenideElement INFO_TABLE = Selenide.$x("//table[contains(@class, 'MuiTable-root')]");

    public void checkIsLoaded() {
        INFO_TABLE.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public String getRandomApplicationNumber() {
        checkIsLoaded();

        if (APPLICATION_ROWS.isEmpty()) {
            throw new IllegalStateException("Нет доступных заявок в таблице.");
        }

        int randomIndex = new Random().nextInt(APPLICATION_ROWS.size());
        SelenideElement randomRow = APPLICATION_ROWS.get(randomIndex);

        return randomRow.$x("./td[1]").getText().trim();
    }

    public void approveApplicationByNumber(String number) {
        checkIsLoaded();
        getRowByApplicationNumber(number)
                .$x(".//button[1]")
                .click();
    }

    public void rejectApplicationByNumber(String number) {
        checkIsLoaded();
        getRowByApplicationNumber(number)
                .$x(".//button[2]")
                .click();
    }

    public void checkApplicationIsApproved(String number) {
        checkIsLoaded();
        getRowByApplicationNumber(number)
                .$x("./td[last()-1]")
                .shouldHave(Condition.text("Одобрена"), Duration.ofSeconds(10));
    }

    public void checkApplicationIsRejected(String number) {
        checkIsLoaded();
        getRowByApplicationNumber(number)
                .$x("./td[last()-1]")
                .shouldHave(Condition.text("Отклонена"), Duration.ofSeconds(10));
    }

    private SelenideElement getRowByApplicationNumber(String number) {
        return Selenide.$x("//table//tr[td[1][normalize-space(text()) = '" + number + "']]");
    }
}
