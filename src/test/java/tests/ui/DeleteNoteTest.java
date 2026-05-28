package tests.ui;

import base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.NotesPage;

import org.apache.logging.log4j.Logger;

import utils.JsonDataReader;
import utils.LoggerUtility;

import io.qameta.allure.Feature;

@Feature("UI Note Management")
public class DeleteNoteTest extends BaseTest {

    private static final Logger logger =
            LoggerUtility.getLogger(DeleteNoteTest.class);

    @Severity(SeverityLevel.CRITICAL)

    @Description(
            "Validates note deletion through UI"
    )

    @Test
    public void validateNoteDeletion() {

        HomePage homePage =
                new HomePage();

        LoginPage loginPage =
                new LoginPage();

        NotesPage notesPage =
                new NotesPage();

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

        logger.info("Opening application");

        homePage.openApplication();

        logger.info("Navigating to login page");

        homePage.clickLoginButton();

        logger.info("Performing login");

        loginPage.login(
                email,
                password
        );

        logger.info("Deleting note");

        notesPage.deleteNote(noteTitle);

        logger.info("Validating note deletion");

        Assert.assertTrue(
                notesPage.isNoteDeleted(noteTitle),
                "Note was not deleted successfully!"
        );

        logger.info("Delete note test completed successfully");
    }
}