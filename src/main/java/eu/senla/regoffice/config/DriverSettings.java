package eu.senla.regoffice.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import eu.senla.regoffice.utils.PropertyUtil;
import io.qameta.allure.selenide.AllureSelenide;

public class DriverSettings {
    public static void setUp() {
        Configuration.browser = System.getProperty("browser.type");
        Configuration.browserSize = System.getProperty("browser.size");
        Configuration.baseUrl = PropertyUtil.getProperty("baseUri");

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(false));
    }
}
