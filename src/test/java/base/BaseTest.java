package base;

import drivers.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(listeners.TestListener.class)

@Test(
        retryAnalyzer =
                listeners.RetryAnalyzer.class
)

public class BaseTest {

    @BeforeMethod
    public void setup() {

        DriverManager.initDriver();
    }

    @AfterMethod
    public void tearDown() {

        DriverManager.quitDriver();
    }
}