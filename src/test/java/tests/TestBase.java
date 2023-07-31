package tests;

import com.codeborne.selenide.Configuration;
import configs.WebDriverProvider;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;


public class TestBase {

    public static String item = "grill";

    @BeforeAll
    public static void beforeAll() {
        WebDriverProvider.config();
        Configuration.pageLoadStrategy = "eager";
        Configuration.pageLoadTimeout = 10000;
        Configuration.timeout = 15000;
    }


    @AfterEach
    public void afterEach() {
        Attach.screenshotAs("Last screenshot");
        closeWebDriver();
    }


}
