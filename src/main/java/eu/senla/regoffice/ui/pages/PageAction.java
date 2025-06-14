package eu.senla.regoffice.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageAction {
    private final SelenideElement NEXT_BUTTON = Selenide.$x("//button[text() = 'Далее']");
    private final SelenideElement FINISH_BUTTON = Selenide.$x("//button[text() = 'Завершить']");
    private final SelenideElement REFRESH_BUTTON = Selenide.$x("//button[text() = 'Обновить']");
    private final SelenideElement CLOSE_BUTTON = Selenide.$x("//button[text() = 'Закрыть']");

    @Step("Нажатие кнопки 'Далее'")
    public void clickNext() {
        NEXT_BUTTON.click();
        log.info("Нажатие кнопки 'Далее'");
    }

    @Step("Нажатие кнопки 'Завершить'")
    public void clickFinish() {
        FINISH_BUTTON.click();
        log.info("Нажатие кнопки 'Завершить'");
    }

    @Step("Нажатие кнопки 'Обновить'")
    public void clickRefresh() {
        REFRESH_BUTTON.click();
        log.info("Нажатие кнопки 'Обновить'");
    }

    @Step("Нажатие кнопки 'Закрыть'")
    public void clickClose() {
        CLOSE_BUTTON.click();
        log.info("Нажатие кнопки 'Закрыть'");
    }
}
