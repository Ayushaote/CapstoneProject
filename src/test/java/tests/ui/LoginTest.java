package tests.ui;

import base.BaseTest;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;


@Feature("Login Validation")

public class LoginTest extends BaseTest {
   @Test
    public void validateSuccessfulLogin() {

        HomePage homePage =
                new HomePage();

        LoginPage loginPage =
                new LoginPage();

        homePage.openApplication();

        homePage.clickLoginButton();

        loginPage.login(
                "ayushaccount1@gmail.com",
                "Ayushaccount1@123"
        );
    }
}