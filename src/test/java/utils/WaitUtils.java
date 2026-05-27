package utils;

import drivers.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final WebDriverWait wait =
            new WebDriverWait(
                    DriverManager.getDriver(),
                    Duration.ofSeconds(20)
            );

    public static void waitForVisibility(WebElement element) {

        wait.until(
                ExpectedConditions.visibilityOf(element)
        );
    }

    public static void waitForClickability(WebElement element) {

        wait.until(
                ExpectedConditions.elementToBeClickable(element)
        );
    }

    public static void waitForDomToLoad() {

        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver)
                        .executeScript(
                                "return document.readyState"
                        )
                        .equals("complete")
        );
    }

    public static void sleep(
            int milliseconds
    ) {

        try {

            Thread.sleep(milliseconds);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }
}