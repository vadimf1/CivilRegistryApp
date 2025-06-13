package eu.senla.regoffice.ui;

import com.codeborne.selenide.Selenide;
import eu.senla.regoffice.config.DriverSettings;
import io.qameta.allure.Step;
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
        DriverSettings.setUp();
        connection = ConnectionHolder.getConnection();
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
