package utils;

import drivers.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    public static void captureScreenshot(String testName) {

        File src =
                ((TakesScreenshot) DriverManager.getDriver())
                        .getScreenshotAs(OutputType.FILE);

        File dest =
                new File("screenshots/" + testName + ".png");

        try {

            FileUtils.copyFile(src, dest);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}