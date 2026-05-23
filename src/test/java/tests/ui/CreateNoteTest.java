package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.NotesPage;

public class CreateNoteTest extends BaseTest {

    @Test
    public void validateNoteCreation() {

        HomePage homePage =
                new HomePage();

        LoginPage loginPage =
                new LoginPage();

        NotesPage notesPage =
                new NotesPage();

        homePage.openApplication();

        homePage.clickLoginButton();

        loginPage.login(
                "ayushaccount1@gmail.com",
                "Ayushaccount1@123"
        );

        String noteTitle =
                "Capstone Framework";

        notesPage.createNote(
                "Work",
                noteTitle,
                "Building enterprise QA framework"
        );

        Assert.assertTrue(
                notesPage.isNotePresent(noteTitle),
                "Note was not created successfully!"
        );
    }
}