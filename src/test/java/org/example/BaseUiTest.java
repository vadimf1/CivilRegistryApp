package org.example;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.example.ui.pages.MainPage;
import org.example.ui.pages.PageAction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseUiTest {
    protected MainPage mainPage;
    protected PageAction action;

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
        attachScreenshot();
        Selenide.closeWebDriver();
    }

    private void attachScreenshot() {
        try {
            byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.getLifecycle().addAttachment("Screenshot", "image/png", "png", screenshot);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
