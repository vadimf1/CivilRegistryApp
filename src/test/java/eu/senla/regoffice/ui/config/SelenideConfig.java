package eu.senla.regoffice.ui.config;

import com.codeborne.selenide.Configuration;
import eu.senla.regoffice.utils.PropertyUtil;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class SelenideConfig {
    public static void setUp() {
        Configuration.remote = "http://host.docker.internal:4444/wd/hub";
        Configuration.browser = System.getProperty("browser.type");
        Configuration.browserSize = System.getProperty("browser.size");
        Configuration.baseUrl = PropertyUtil.getProperty("baseUri");

        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("screenResolution", "1920x1080x24");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", Configuration.browser);
        capabilities.setCapability("selenoid:options", selenoidOptions);

        Configuration.browserCapabilities = capabilities;
    }
}
