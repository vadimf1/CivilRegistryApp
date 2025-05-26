package org.example;

import com.codeborne.selenide.Selenide;
import org.example.ui.pages.MainPage;
import org.example.ui.pages.PageAction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseUiTest {
    protected MainPage mainPage;
    protected PageAction action;

    @BeforeEach
    public void openMainPage() {
        mainPage = new MainPage();
        mainPage.openMainPage();
        action = new PageAction();
    }

    @AfterEach
    public void closeSelenideDriver() {
        Selenide.closeWebDriver();
    }
}
