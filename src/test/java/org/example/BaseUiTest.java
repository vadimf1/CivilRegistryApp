package org.example;

import com.codeborne.selenide.Selenide;
import org.example.ui.pages.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseUiTest {
    protected MainPage mainPage;

    @BeforeEach
    public void openMainPage() {
        mainPage = new MainPage();
        mainPage.openMainPage();
    }

    @AfterEach
    public void closeSelenideDriver() {
        Selenide.closeWebDriver();
    }
}
