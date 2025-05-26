package org.example.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class PageAction {
    private final SelenideElement NEXT_BUTTON = Selenide.$x("//button[text() = 'Далее']");
    private final SelenideElement FINISH_BUTTON = Selenide.$x("//button[text() = 'Завершить']");
    private final SelenideElement REFRESH_BUTTON = Selenide.$x("//button[text() = 'Обновить']");
    private final SelenideElement CLOSE_BUTTON = Selenide.$x("//button[text() = 'Закрыть']");

    public void clickNext() {
        NEXT_BUTTON.click();
    }

    public void clickFinish() {
        FINISH_BUTTON.click();
    }

    public void clickRefresh() {
        REFRESH_BUTTON.click();
    }

    public void clickClose() {
        CLOSE_BUTTON.click();
    }
}
