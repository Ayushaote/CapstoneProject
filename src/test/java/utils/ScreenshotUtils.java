package utils;

import drivers.DriverManager;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    public static byte[] captureScreenshot(
            String testName
    ) {

        TakesScreenshot ts =
                (TakesScreenshot)
                        DriverManager.getDriver();

        // For Allure attachment
        byte[] screenshotBytes =
                ts.getScreenshotAs(
                        OutputType.BYTES
                );

        // For local storage
        File src =
                ts.getScreenshotAs(
                        OutputType.FILE
                );

        File dest =
                new File(
                        "screenshots/"
                                + testName
                                + ".png"
                );

        try {

            FileUtils.copyFile(src, dest);

        } catch (IOException e) {

            e.printStackTrace();
        }

        return screenshotBytes;
    }
}