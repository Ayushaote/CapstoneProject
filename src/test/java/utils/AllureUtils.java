package utils;

import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

public class AllureUtils {

    public static void attachJson(
            String name,
            String json
    ) {

        Allure.addAttachment(
                name,
                "application/json",
                new ByteArrayInputStream(
                        json.getBytes()
                ),
                ".json"
        );
    }

    public static void attachScreenshot(
            String attachmentName,
            byte[] screenshot
    ) {

        Allure.addAttachment(
                attachmentName,
                "image/png",
                new ByteArrayInputStream(
                        screenshot
                ),
                ".png"
        );
    }
}