package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.LocatorResolver;

import java.util.List;

public class NotesPage extends BasePage {

    public void clickAddNoteButton() {

        click("notes.add.button");
    }

    public void selectCategory(String category) {

        WebElement dropdown =
                getElement("notes.category.dropdown");

        Select select =
                new Select(dropdown);

        select.selectByVisibleText(category);
    }

    public void enterTitle(String title) {

        type("notes.title", title);
    }

    public void enterDescription(String description) {

        type("notes.description", description);
    }

    public void clickCreateButton() {

        click("notes.create.button");
    }

    public void createNote(
            String category,
            String title,
            String description
    ) {

        clickAddNoteButton();

        selectCategory(category);

        enterTitle(title);

        enterDescription(description);

        clickCreateButton();
    }

    public boolean isNotePresent(String noteTitle)
    {
        try {

            List<WebElement> notes =
                    driver.findElements(
                            LocatorResolver.getLocator(
                                    "notes.card.container"
                            )
                    );

            for(WebElement note : notes) {

                String actualTitle =
                        note.getText();

                if(actualTitle.equals(noteTitle)) {

                    return true;
                }
            }

        } catch(Exception e) {

            return false;
        }

        return false;
    }

    public void deleteNote(String noteTitle) {

        List<WebElement> notes =
                driver.findElements(
                        LocatorResolver.getLocator(
                                "notes.card.container"
                        )
                );

        for(WebElement note : notes) {

            if(note.getText().contains(noteTitle)) {

                WebElement deleteButton =
                        note.findElement(
                                LocatorResolver.getLocator(
                                        "notes.delete.button"
                                )
                        );

                deleteButton.click();

                break;
            }
        }
    }

    public boolean isNoteDeleted(String noteTitle) {

        return !isNotePresent(noteTitle);
    }
}