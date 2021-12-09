package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Singlewebdriver {

    public static WebDriver driver;


    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        return driver;
    }

    public static void QuitWebDriver() throws InterruptedException {

        Thread.sleep(10000);
        if (driver != null)
            driver.quit();
    }

}
