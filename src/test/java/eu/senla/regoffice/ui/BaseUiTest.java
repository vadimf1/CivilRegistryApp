package eu.senla.regoffice.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import eu.senla.regoffice.ui.config.SelenideConfig;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import eu.senla.regoffice.db.connection.ConnectionHolder;
import eu.senla.regoffice.ui.pages.MainPage;
import eu.senla.regoffice.ui.pages.PageAction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;

public class BaseUiTest {
    protected MainPage mainPage;
    protected PageAction action;
    protected static Connection connection;

    @BeforeAll
    public static void baseSetUp() {
        SelenideConfig.setUp();
        connection = ConnectionHolder.getConnection();
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

    @AfterAll
    static void closeDatabaseConnection() {
        ConnectionHolder.closeConnection();
    }
}
