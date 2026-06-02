package tests.ui;

import base.BaseTest;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.JsonDataReader;


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

       loginPage.login(
               email,
               password
       );
    }
}