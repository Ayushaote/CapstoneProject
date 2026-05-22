package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import utils.ConfigReader;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    public static void initDriver() {

        String browser =
                ConfigReader.getProperty("browser")
                        .toLowerCase();

        switch (browser) {

            case "chrome":

                WebDriverManager.chromedriver().setup();

                driver.set(new ChromeDriver());

                break;

            case "firefox":

                WebDriverManager.firefoxdriver().setup();

                driver.set(new FirefoxDriver());

                break;

            case "edge":

                WebDriverManager.edgedriver().setup();

                driver.set(new EdgeDriver());

                break;

            case "brave":

                WebDriverManager.chromedriver().setup();

                ChromeOptions braveOptions =
                        new ChromeOptions();

                braveOptions.setBinary(
                        "C:\\Users\\Ayush\\AppData\\Local\\BraveSoftware\\Brave-Browser\\Application\\brave.exe"
                );

                driver.set(new ChromeDriver(braveOptions));

                break;

            default:

                throw new IllegalArgumentException(
                        "Invalid browser: " + browser
                );
        }

        driver.get().manage().window().maximize();
    }

    public static WebDriver getDriver() {

        return driver.get();
    }

    public static void quitDriver() {

        if(driver.get() != null) {

            driver.get().quit();

            driver.remove();
        }
    }
}