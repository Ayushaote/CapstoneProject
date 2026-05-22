package base;

import drivers.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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