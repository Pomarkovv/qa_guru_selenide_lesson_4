package web.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class BaseUITest {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.timeout = 10000;
        Configuration.remote = System.getProperty("selenoidUrl");
        Configuration.browser = System.getProperty("browser", "chrome");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        capabilities.setCapability("browserVersion", System.getProperty("browserVersion"));
        Configuration.browserCapabilities = capabilities;
    }
}
