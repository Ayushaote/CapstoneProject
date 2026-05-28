package base;

import drivers.DriverManager;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Listeners(listeners.TestListener.class)

@Test(
        retryAnalyzer =
                listeners.RetryAnalyzer.class
)

public class BaseTest {

    @BeforeSuite
    public void setupEnvironmentFile() {

        try {

            Files.createDirectories(
                    Paths.get("allure-results")
            );

            Files.copy(
                    Paths.get(
                            "src/test/resources/environment.properties"
                    ),

                    Paths.get(
                            "allure-results/environment.properties"
                    ),

                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setup() {

        DriverManager.initDriver();
    }

    @AfterMethod
    public void tearDown() {

        DriverManager.quitDriver();
    }
}