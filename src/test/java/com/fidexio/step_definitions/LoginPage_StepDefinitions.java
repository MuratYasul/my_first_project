package com.fidexio.step_definitions;

import com.fidexio.pages.HomePage;
import com.fidexio.pages.LoginPage;
import com.fidexio.utilities.ConfigurationReader;
import com.fidexio.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPage_StepDefinitions {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("environment"));

    }
    @When("user enters valid {string}")
    public void user_enters_valid(String string) {
        loginPage.userNameInput.sendKeys(string);

       }
    @When("user enters password {string}")
    public void user_enters_password(String string) {
        loginPage.passwordInput.sendKeys(string);
       }
    @When("user clicks on the login button")
    public void user_clicks_on_the_login_button() {
        loginPage.loginButton.click();

        }
    @Then("user should be on the homepage")
    public void user_should_be_on_the_homepage() {
        Driver.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        wait.until(ExpectedConditions.titleIs("#Inbox - Odoo"));
        String expectedTitle ="#Inbox - Odoo";
        Assert.assertEquals(expectedTitle, Driver.getDriver().getTitle());

    }


    @When("user enters invalid email {string}")
    public void userEntersInvalidEmail(String string) {
       loginPage.inputEmail.sendKeys(string);
        loginPage.loginButton.click();
    }

    @And("user enters invalid password {string}")
    public void userEntersInvalidPassword(String string) {
     loginPage.inputPassword.sendKeys(string);
     //   loginPage.loginButton.click();
        loginPage.loginButton.sendKeys(Keys.ENTER);

    }

    @Then("user sees {string} message displayed for invalid credentials")
    public void userSeesMessageDisplayedForInvalidCredentials(String string) {
      //  loginPage.loginBtn.click();
       String actualMsg= loginPage.wrongCredentials.getText();
       String expectedMessage="Wrong login/password";
        System.out.println("When entering wrong credentials, User sees = " + "\""+actualMsg+"\""+ " message.");
        Assert.assertEquals(expectedMessage,actualMsg);

}


    @When("user press ENTER key on the login button")
    public void user_press_enter_key_on_the_login_button() {
       loginPage.loginButton.sendKeys(Keys.ENTER);

          }
    @Then("ENTER key is pressable")
    public void enter_key_is_pressable() {
        System.out.println("homePage.inboxText.getText() = " + homePage.inboxText.getText());
        Assert.assertTrue("User cannot log in home page",homePage.inboxText.isDisplayed());

//        String required = "required";
//        String verifiedMessage = "Please fill in this field.";
//        boolean requiredUsername = loginPage.userNameInput.getAttribute("required").equals(required);
//        boolean requiredPassword = loginPage.passwordInput.getAttribute("required").equals(required);
//        boolean validateMsgUsername = loginPage.userNameInput.getAttribute("validationMessage").equals(verifiedMessage);
//        boolean validateMsgPassword = loginPage.passwordInput.getAttribute("validationMessage").equals(verifiedMessage);
//        Assert.assertTrue(requiredUsername && requiredPassword);
//        Assert.assertTrue(validateMsgUsername && validateMsgPassword);

    }
}






