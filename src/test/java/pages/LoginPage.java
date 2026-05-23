package pages;

import base.BasePage;
import utils.ConfigReader;

public class LoginPage extends BasePage {


    public void enterEmail(String email) {

        type("login.email", email);
    }

    public void enterPassword(String password) {

        type("login.password", password);
    }

    public void clickLoginButton() {

        click("login.submit");
    }

    public void login(String email, String password) {

        enterEmail(email);

        enterPassword(password);

        clickLoginButton();
    }
}