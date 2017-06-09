package com.epam.wizzair.step.cucumber;

import com.epam.wizzair.page.LoginPage;
import cucumber.api.java.en.When;

/**
 * Created by Aliaksandr_Krutsko on 6/8/2017.
 */
public class LoginPageStepsCuc {

    @When("^User enters \"([^\\\"]*)\" in login field and \"([^\\\"]*)\" in password field$")
    public void user_enters_login_and_password(String login, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.login(login, password);
    }

}
