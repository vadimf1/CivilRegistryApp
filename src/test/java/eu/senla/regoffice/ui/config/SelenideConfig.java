package eu.senla.regoffice.ui.config;

import com.codeborne.selenide.Configuration;
import eu.senla.regoffice.utils.PropertyUtil;

public class SelenideConfig {
    public static void setUp() {
        Configuration.browser = System.getProperty("browser.type");
        Configuration.browserSize = System.getProperty("browser.size");
        Configuration.baseUrl = PropertyUtil.getProperty("baseUri");
    }
}
