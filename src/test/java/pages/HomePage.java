package pages;

import base.BasePage;
import utils.ConfigReader;

public class HomePage extends BasePage {

    public void openApplication() {

        driver.get(
                ConfigReader.getProperty("base.url")
        );
    }

    public void clickLoginButton() {

        click("home.login.button");
    }

    public void clickCreateAccountButton() {

        click("home.create.account.button");
    }
}