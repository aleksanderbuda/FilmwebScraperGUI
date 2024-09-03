package imdbscraper.setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
@Getter
public class WebDriverSetup {
    private static WebDriver driver;
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

//    public static WebDriver getDriver() {
//        return driver;
//    }
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}

