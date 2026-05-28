package tests.api;

import api.AuthApi;
import api.NotesApi;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.apache.logging.log4j.Logger;

import utils.AllureUtils;
import utils.ApiRetryUtility;
import utils.JsonDataReader;
import utils.LoggerUtility;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Feature("Notes API")
public class GetNotesApiTest {

    private static final Logger logger =
            LoggerUtility.getLogger(GetNotesApiTest.class);

    @Severity(SeverityLevel.CRITICAL)

    @Description(
            "Validates notes using GET API"
    )

    @Test
    public void validateGetNotesApi() {

        logger.info("Reading test data");

        String email =
                JsonDataReader.getData(
                        "login",
                        "email"
                );

        String password =
                JsonDataReader.getData(
                        "login",
                        "password"
                );

        logger.info("Generating authentication token");

        String token =
                AuthApi.getAuthToken(
                        email,
                        password
                );

        logger.info("Calling GET /notes API");

        Response response = ApiRetryUtility.executeWithRetry(
                () -> NotesApi.getAllNotes(token)
        );

        AllureUtils.attachJson(
                "GET Notes Response",
                response.asPrettyString()
        );

        logger.info("Validating response status code");

        response.then().statusCode(200);

        logger.info("Validating JSON schema");

        response.then().assertThat()
                .body(
                        matchesJsonSchemaInClasspath(
                                "schemas/notes-schema.json"
                        )
                );

        logger.info("Validating response contains expected note");

        Assert.assertTrue(
                response.asString()
                        .contains("Capstone Framework"),
                "Expected note not found in API response"
        );

        AllureUtils.attachJson(
                "GET Notes Response",
                response.asPrettyString()
        );

        long responseTime =
                response.time();

        logger.info(
                "API Response Time: " + responseTime + " ms"
        );

        Assert.assertTrue(
                responseTime < 2000,
                "API response exceeded 2 seconds"
        );

        logger.info("GET Notes API test completed successfully");
    }
}