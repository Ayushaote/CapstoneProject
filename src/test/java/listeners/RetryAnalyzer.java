package listeners;

import org.apache.logging.log4j.Logger;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import utils.LoggerUtility;

public class RetryAnalyzer
        implements IRetryAnalyzer {

    private static final Logger logger =
            LoggerUtility.getLogger(
                    RetryAnalyzer.class
            );

    private int retryCount = 0;

    private static final int maxRetryCount = 2;

    @Override
    public boolean retry(
            ITestResult result
    ) {

        if(retryCount < maxRetryCount) {

            retryCount++;

            logger.warn(
                    "Retrying test: "
                            + result.getName()
                            + " | Attempt: "
                            + retryCount
            );

            return true;
        }

        return false;
    }
}