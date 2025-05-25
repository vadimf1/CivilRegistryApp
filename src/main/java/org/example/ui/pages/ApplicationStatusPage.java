package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationStatusPage {
    private final SelenideElement applicationInfo = Selenide.$x("//span[contains(text(), 'Ваша заявка')]");
    private final SelenideElement applicationStatusInfo = Selenide.$x("//span[contains(., 'Статус заявки:')]");

    private final PageAction action = new PageAction();

    public void checkIsLoaded() {
        applicationInfo.shouldBe(
                Condition.matchText("Ваша заявка № \\d+ отправлена на рассмотрение\\."),
                Duration.ofSeconds(10)
        );

        System.out.println(applicationInfo.getText());
    }

    public String getApplicationNumber() {
        checkIsLoaded();

        String text = applicationInfo.getText();

        Pattern pattern = Pattern.compile("№ (\\d+)");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new IllegalStateException("Не удалось извлечь номер заявки из текста: " + text);
        }
    }

    public void checkApplicationIsApproved() {
        applicationStatusInfo.shouldHave(Condition.text("Одобрена"));
    }

    public void checkApplicationIsRejected() {
        applicationStatusInfo.shouldHave(Condition.text("Отклонена"));
    }

    public void clickRefreshButton() {
        action.clickRefresh();
    }

    public void clickCloseButton() {
        action.clickClose();
    }
}
