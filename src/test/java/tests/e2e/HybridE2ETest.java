package tests.e2e;

import api.AuthApi;
import api.NotesApi;

import base.BaseTest;

import drivers.DriverManager;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import io.restassured.response.Response;

import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.NotesPage;

import utils.*;

@Feature("Hybrid UI/API E2E Validation")

public class HybridE2ETest
        extends BaseTest {

    private static final Logger logger =

            LoggerUtility.getLogger(
                    HybridE2ETest.class
            );

    @Severity(SeverityLevel.BLOCKER)

    @Description(
            "Validates complete hybrid UI/API synchronization flow"
    )

    @Test(
            retryAnalyzer =
                    listeners.RetryAnalyzer.class
    )

    public void validateHybridE2EFlow() {

        Allure.step(
                "Starting Hybrid UI/API E2E Flow"
        );

        // Page Objects
        HomePage homePage =
                new HomePage();

        LoginPage loginPage =
                new LoginPage();

        NotesPage notesPage =
                new NotesPage();

        // Test Data
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

        String noteTitle =

                TestDataGenerator
                        .generateNoteTitle(
                                "E2E_NOTE"
                        );

        String description =
                "Hybrid E2E Validation";

        logger.info(
                "Starting Hybrid E2E execution"
        );

        // UI Login
        logger.info(
                "Opening application"
        );

        homePage.openApplication();

        logger.info(
                "Navigating to login page"
        );

        homePage.clickLoginButton();

        logger.info(
                "Performing UI login"
        );

        loginPage.login(
                email,
                password
        );

        // UI Create
        logger.info(
                "Creating note through UI"
        );

        notesPage.createNote(
                "Work",
                noteTitle,
                description
        );

        WaitUtils.sleep(2000);

        // UI Validation
        logger.info(
                "Validating note presence in UI"
        );

        Assert.assertTrue(

                notesPage.isNotePresent(
                        noteTitle
                ),

                "Note not visible in UI"
        );

        // Generate API Token
        logger.info(
                "Generating authentication token"
        );

        String token =

                AuthApi.getAuthToken(
                        email,
                        password
                );

        // GET Notes API
        logger.info(
                "Fetching notes through API"
        );

        Response notesResponse =

                NotesApi.getAllNotes(
                        token
                );

        // Attach API Response
        AllureUtils.attachJson(

                "GET Notes API Response",

                notesResponse
                        .asPrettyString()
        );

        // API Validation
        logger.info(
                "Validating note presence in API response"
        );

        Assert.assertTrue(

                notesResponse
                        .asPrettyString()
                        .contains(noteTitle),

                "Note not visible in API"
        );

        // Extract Note ID
        logger.info(
                "Extracting note ID"
        );

        String noteId =

                NotesApi.getNoteIdByTitle(
                        token,
                        noteTitle
                );

        Assert.assertNotNull(

                noteId,

                "Note ID not found"
        );

        // DELETE API
        logger.info(
                "Deleting note through API"
        );

        Response deleteResponse =

                NotesApi.deleteNote(
                        token,
                        noteId
                );

        // Attach DELETE Response
        AllureUtils.attachJson(

                "DELETE Note API Response",

                deleteResponse
                        .asPrettyString()
        );

        // Refresh UI
        logger.info(
                "Refreshing UI"
        );

        DriverManager.getDriver()
                .navigate()
                .refresh();

        // Final Validation
        logger.info(
                "Validating note removal from UI"
        );

        Allure.step(
                "Validating note deletion from UI"
        );

        Assert.assertFalse(

                notesPage.isNotePresent(
                        noteTitle
                ),

                "Note still visible after delete"
        );

        // Final Screenshot
        AllureUtils.attachScreenshot(

                "Final UI State",

                ScreenshotUtils.captureScreenshot(
                        "HybridE2EFinalState"
                )
        );

        logger.info(
                "Hybrid E2E validation completed successfully"
        );
    }
}