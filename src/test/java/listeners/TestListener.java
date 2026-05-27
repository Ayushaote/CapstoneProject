package listeners;

import io.qameta.allure.Attachment;

import org.apache.logging.log4j.Logger;

import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.LoggerUtility;
import utils.ScreenshotUtils;

public class TestListener
        implements ITestListener {

    private static final Logger logger =
            LoggerUtility.getLogger(
                    TestListener.class
            );

    @Override
    public void onTestFailure(
            ITestResult result
    ) {

        logger.error(
                "Test Failed: "
                        + result.getName()
        );

        byte[] screenshot =
                ScreenshotUtils.captureScreenshot(
                        result.getName()
                );

        if(screenshot != null) {

            attachScreenshot(screenshot);
        }
    }

    @Attachment(
            value = "Failure Screenshot",
            type = "image/png"
    )

    public byte[] attachScreenshot(
            byte[] screenshot
    ) {

        return screenshot;
    }
}