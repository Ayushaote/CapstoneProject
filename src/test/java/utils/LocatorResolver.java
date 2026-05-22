package utils;

import org.openqa.selenium.By;

public class LocatorResolver {

    public static By getLocator(String key) {

        String locator =
                ConfigReader.getLocator(key);

        String locatorType =
                locator.split(":", 2)[0];

        String locatorValue =
                locator.split(":", 2)[1];

        switch (locatorType.toLowerCase()) {

            case "id":
                return By.id(locatorValue);

            case "xpath":
                return By.xpath(locatorValue);

            case "css":
                return By.cssSelector(locatorValue);

            case "name":
                return By.name(locatorValue);

            default:
                throw new IllegalArgumentException(
                        "Invalid locator type: " + locatorType
                );
        }
    }
}