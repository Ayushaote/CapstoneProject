package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties configProperties =
            new Properties();

    private static final Properties locatorProperties =
            new Properties();

    static {

        try {

            FileInputStream configFis =
                    new FileInputStream(
                            "src/test/resources/config.properties"
                    );

            configProperties.load(configFis);

            FileInputStream locatorFis =
                    new FileInputStream(
                            "src/test/resources/locators.properties"
                    );

            locatorProperties.load(locatorFis);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {

        return configProperties.getProperty(key);
    }

    public static String getLocator(String key) {

        return locatorProperties.getProperty(key);
    }
}