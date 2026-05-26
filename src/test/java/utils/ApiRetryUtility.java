package utils;

import io.restassured.response.Response;

import java.util.function.Supplier;

public class ApiRetryUtility {

    public static Response executeWithRetry(
            Supplier<Response> apiCall
    ) {

        int maxRetries = 2;

        int attempt = 0;

        while(attempt <= maxRetries) {

            Response response =
                    apiCall.get();

            int statusCode =
                    response.getStatusCode();

            if(statusCode < 500) {

                return response;
            }

            attempt++;

            System.out.println(
                    "Retrying API call... Attempt: "
                            + attempt
            );
        }

        return apiCall.get();
    }
}