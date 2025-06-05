package org.example.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.example.db.connection.ConnectionHolder;
import org.example.ui.pages.MainPage;
import org.example.ui.pages.PageAction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;

public class BaseUiTest {
    protected MainPage mainPage;
    protected PageAction action;
    protected Connection connection;

    @BeforeAll
    public static void setUpAllureListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(false));
    }

    @BeforeEach
    @Step("Открытие главной страницы и инициализация объектов")
    public void openMainPage() {
        mainPage = new MainPage();
        mainPage.openMainPage();
        action = new PageAction();
    }

    @AfterEach
    @Step("Закрытие браузера после теста")
    public void closeSelenideDriver() {
        Selenide.closeWebDriver();
    }

    @BeforeEach
    void connectToDatabase() {
        connection = ConnectionHolder.getConnection();
    }

    @AfterEach
    void closeDatabaseConnection() {
        ConnectionHolder.closeConnection();
    }
}
