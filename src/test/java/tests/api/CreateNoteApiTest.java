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

public class CreateNoteApiTest {

    private static final Logger logger =
            LoggerUtility.getLogger(
                    CreateNoteApiTest.class
            );

    @Test
    public void validateCreateNoteApi() {

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

        logger.info("Generating auth token");

        String token =
                AuthApi.getAuthToken(
                        email,
                        password
                );

        logger.info("Creating note via API");

        Response response = ApiRetryUtility.executeWithRetry
                (
                        () ->   NotesApi.createNote(
                        token,
                        "API Note",
                        "Created through API",
                        "Work"
                ));

        logger.info("Validating response status");

        response.then().statusCode(200);

        Assert.assertTrue(
                response.asString()
                        .contains("API Note")
        );

        logger.info(
                "Create Note API test completed successfully"
        );

        AllureUtils.attachJson(
                "GET Notes Response",
                response.asPrettyString()
        );
    }
}