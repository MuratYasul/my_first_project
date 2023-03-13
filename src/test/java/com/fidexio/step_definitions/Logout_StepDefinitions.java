package com.fidexio.step_definitions;

import com.fidexio.pages.HomePage;
import com.fidexio.pages.LoginPage;
import com.fidexio.pages.LogoutPage;
import com.fidexio.utilities.ConfigurationReader;
import com.fidexio.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Logout_StepDefinitions {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    LogoutPage logoutPage = new LogoutPage();
    String actualEmail, actualPassword;


////
////    //Test Case-1
////    @Given("user is on the login page")
////    public void user_is_on_the_login_page() {
////        Driver.getDriver().get(ConfigurationReader.getProperty("environment"));
////
////    }
////    @When("user enters valid {string}")
////    public void user_enters_valid(String email) {
////        //   loginPage.userNameInput.sendKeys(string);
////        loginPage.inputEmail.sendKeys(email);
////        actualEmail = email;
////
////    }
//    @When("user enters password {string}")
//    public void user_enters_password(String password) {
//        // loginPage.passwordInput.sendKeys(string);
//        loginPage.inputPassword.sendKeys(password);
//        actualPassword = password;
//    }
//    @When("user clicks on the login button")
//    public void user_clicks_on_the_login_button() {
//        loginPage.loginButton.click();
//
//    }

    @When("user clicks on the username")
    public void user_clicks_on_the_username() {
        loginPage.userName.click();

    }
    @Then("user clicks on the logout button")
    public void user_clicks_on_the_logout_button() {
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
         wait.until(ExpectedConditions.elementToBeClickable(loginPage.logoutBtn));
       loginPage.logoutBtn.click();
        String expectedMessage= "Best solution for startups";
      Assert.assertEquals(expectedMessage, logoutPage.loginPageTxt.getText());
        System.out.println(logoutPage.loginPageTxt.getText());
    }
    }

