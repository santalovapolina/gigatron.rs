package helpers;


import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;


public class Attach {
    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    public static void browserConsoleLogs() {
        attachAsText(
                "Browser console logs",
                String.join("\n", Selenide.getWebDriverLogs(BROWSER))
        );
    }

    @Attachment
    public static byte[] webElementScreenshot(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("src/test/resources/", resourceName));
    }

    @Attachment(value = "{attachName}", type = "text/html", fileExtension = ".html")
    public static String addMap(String attachName, HashMap<String, String> map) {
        String table = "<table border=1>";
        for (HashMap.Entry<String, String> entry : map.entrySet()) {
            table += "<tr><td>" + entry.getKey() + "</td><td>" + entry.getValue() + "</td></tr>";
        }
        table += "</table>";
        return table;
    }

    @Attachment(value = "{attachName}", type = "text/html", fileExtension = ".html")
    public static String addListArray(String attachName, List<String[]> list) {
        String table = "<table border=1>";
        for (int i = 0; i < list.size(); i++) {
            table += "<tr><td>" + (i + 1) + "</td>";
            for (int j = 0; j < list.get(i).length; j++) {
                table += "<td>" + list.get(i)[j] + "</td>";
            }
            table += "</tr>";
        }
        table += "</table>";
        return table;
    }


}
