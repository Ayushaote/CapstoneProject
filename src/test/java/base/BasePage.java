package base;

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.LocatorResolver;
import utils.WaitUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;

    public BasePage() {

        this.driver = DriverManager.getDriver();
    }

    protected WebElement getElement(String locatorKey) {

        By locator =
                LocatorResolver.getLocator(locatorKey);

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    protected void click(String locatorKey) {

        WebElement element =
                getElement(locatorKey);

        WaitUtils.waitForClickability(element);

        element.click();
    }

    protected void type(String locatorKey, String text) {

        WebElement element = getElement(locatorKey);

        element.clear();

        element.sendKeys(text);
    }

    protected String getText(String locatorKey) {

        return getElement(locatorKey).getText();
    }
}