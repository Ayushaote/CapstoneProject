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

@Feature("Notes API")

public class DeleteNoteApiTest {

    private static final Logger logger =
            LoggerUtility.getLogger(
                    DeleteNoteApiTest.class
            );

    @Severity(SeverityLevel.CRITICAL)

    @Description(
            "Validates note deletion using delete API"
    )
    @Test
    public void validateDeleteNoteApi() {

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

        logger.info("Getting note ID");

        String noteId =
                NotesApi.getNoteIdByTitle(
                        token,
                        "API Note"
                );

        Assert.assertNotNull(
                noteId,
                "Note ID not found"
        );

        logger.info("Deleting note via API");

        Response response = ApiRetryUtility.executeWithRetry(
                () -> NotesApi.deleteNote(token, noteId)
        );

        AllureUtils.attachJson(
                "DELETE Note Response",
                response.asPrettyString()
        );


        logger.info("Validating delete response");

        response.then().statusCode(200);

        Assert.assertTrue(
                response.asString()
                        .contains("successfully deleted")
        );

        logger.info(
                "Delete Note API test completed successfully"
        );
    }
}