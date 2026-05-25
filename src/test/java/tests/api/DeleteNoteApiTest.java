package tests.api;

import api.AuthApi;
import api.NotesApi;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.apache.logging.log4j.Logger;

import utils.JsonDataReader;
import utils.LoggerUtility;

public class DeleteNoteApiTest {

    private static final Logger logger =
            LoggerUtility.getLogger(
                    DeleteNoteApiTest.class
            );

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

        Response response =
                NotesApi.deleteNote(
                        token,
                        noteId
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