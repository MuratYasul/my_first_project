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
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPage_StepDefinitions {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    String actualEmail, actualPassword;



    //Test Case-1
    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("environment"));

    }
    @When("user enters valid {string}")
    public void user_enters_valid(String email) {
     //   loginPage.userNameInput.sendKeys(string);
        loginPage.inputEmail.sendKeys(email);
        actualEmail = email;


       }
    @When("user enters password {string}")
    public void user_enters_password(String password) {
       // loginPage.passwordInput.sendKeys(string);
        loginPage.inputPassword.sendKeys(password);
        actualPassword = password;
       }
    @When("user clicks on the login button")
    public void user_clicks_on_the_login_button() {
        loginPage.loginButton.click();

        }


    @Then("user should be on the homepage")
    public void user_should_be_on_the_homepage() {
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        wait.until(ExpectedConditions.titleIs("#Inbox - Odoo"));
        String expectedTitle ="#Inbox - Odoo";
        Assert.assertEquals(expectedTitle, Driver.getDriver().getTitle());

    }

    //Test case-2
    @When("user enters invalid email {string}")
    public void userEntersInvalidEmail(String email) {
       loginPage.inputEmail.sendKeys(email);
        loginPage.loginButton.click();
    }

    @And("user enters invalid password {string}")
    public void userEntersInvalidPassword(String password) {
     loginPage.inputPassword.sendKeys(password);
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

    //Test Case-3

    @When("user enters valid {string} in username box")
    public void user_enters_valid_in_username_box(String email) {
       loginPage.inputEmail.sendKeys(email);
    }
    @When("user enters valid {string} in password box")
    public void user_enters_valid_in_password_box(String password) {
        loginPage.inputPassword.sendKeys(password);
    }
    @Then("user should see {string} validation message")
    public void user_should_see_validation_message(String validationMessage) {


        if (loginPage.inputEmail.getAttribute("required").equals("required")) {
            Assert.assertEquals(loginPage.inputEmail.getAttribute("validationMessage"), "Please fill out this field.");
        }

        if (loginPage.inputPassword.getAttribute("required").equals("required")) {
            Assert.assertEquals(loginPage.inputPassword.getAttribute("validationMessage"), "Please fill out this field.");
        }

    }

//Test Case-4
@Then("user should see bullet signs")
public void user_should_see_bullet_signs() {
    Assert.assertEquals("password", loginPage.inputPassword.getAttribute("type"));

}

    //Test Case-5
    @When("user press ENTER key on the login button")
    public void user_press_enter_key_on_the_login_button() {
       loginPage.loginButton.sendKeys(Keys.ENTER);

          }
    @Then("ENTER key is pressable")
    public void enter_key_is_pressable() {
        System.out.println("homePage.inboxText.getText() = " + homePage.inboxText.getText());
        Assert.assertTrue("User cannot log in home page",homePage.inboxText.isDisplayed());



   }


}







