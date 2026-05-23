package tests.ui;

import base.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.NotesPage;

import org.apache.logging.log4j.Logger;

import utils.JsonDataReader;
import utils.LoggerUtility;

public class CreateNoteTest extends BaseTest {

    private static final Logger logger =
            LoggerUtility.getLogger(CreateNoteTest.class);

    @Test
    public void validateNoteCreation() {

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
                JsonDataReader.getData(
                        "note",
                        "title"
                );

        String description =
                JsonDataReader.getData(
                        "note",
                        "description"
                );

        String category =
                JsonDataReader.getData(
                        "note",
                        "category"
                );

        logger.info("Opening application");

        homePage.openApplication();

        logger.info("Navigating to login page");

        homePage.clickLoginButton();

        logger.info("Performing login");

        loginPage.login(
                email,
                password
        );

        logger.info("Creating note");

        notesPage.createNote(
                category,
                noteTitle,
                description
        );

        logger.info("Validating created note");

        Assert.assertTrue(
                notesPage.isNotePresent(noteTitle),
                "Note was not created successfully!"
        );

        logger.info("Note creation test completed successfully");
    }
}