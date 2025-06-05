package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ApplicationStatusPage {
    private final SelenideElement applicationInfo = Selenide.$x("//span[contains(text(), 'Ваша заявка')]");
    private final SelenideElement applicationStatusInfo = Selenide.$x("//span[contains(., 'Статус заявки:')]");

    @Step("Проверка, что страница со статусом заявки загружена")
    public void checkIsLoaded() {
        applicationInfo.shouldBe(
                Condition.matchText("Ваша заявка № \\d+ отправлена на рассмотрение\\."),
                Duration.ofSeconds(10)
        );
        log.info("Проверка, что страница со статусом заявки загружена");
    }

    @Step("Получение номера заявки")
    public String getApplicationNumber() {
        checkIsLoaded();
        String text = applicationInfo.getText();

        Pattern pattern = Pattern.compile("№ (\\d+)");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            String number = matcher.group(1);
            log.info("Номер заявки: {}", number);
            return number;
        } else {
            log.error("Не удалось извлечь номер заявки из текста: {}", text);
            throw new IllegalStateException("Не удалось извлечь номер заявки из текста: " + text);
        }
    }

    @Step("Проверка, что заявка одобрена")
    public void checkApplicationIsApproved() {
        applicationStatusInfo.shouldHave(Condition.text("Одобрена"));
        log.info("Проверка, что заявка одобрена");
    }

    @Step("Проверка, что заявка отклонена")
    public void checkApplicationIsRejected() {
        applicationStatusInfo.shouldHave(Condition.text("Отклонена"));
        log.info("Проверка, что заявка отклонена");
    }
}
