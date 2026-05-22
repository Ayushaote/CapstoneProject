package utils;

import drivers.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    public static void waitForVisibility(WebElement element) {

        WebDriverWait wait =
                new WebDriverWait(
                        DriverManager.getDriver(),
                        Duration.ofSeconds(20)
                );

        wait.until(ExpectedConditions.visibilityOf(element));
    }
}