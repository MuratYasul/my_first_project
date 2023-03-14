package com.fidexio.step_definitions;


import com.fidexio.pages.LoginPage;
import com.fidexio.pages.LogoutPage;
import com.fidexio.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Logout_StepDefinitions {

    LoginPage loginPage = new LoginPage();


    LogoutPage logoutPage = new LogoutPage();



    @When("user clicks on the username")
    public void user_clicks_on_the_username() {
        loginPage.userName.click();

    }
    @Then("user clicks on the logout button")
    public void user_clicks_on_the_logout_button() {
    Driver.getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 3);
    wait.until(ExpectedConditions.elementToBeClickable(loginPage.logoutBtn));
      loginPage.logoutBtn.click();
      String expectedMessage= "Best solution for startups";
      Assert.assertEquals(expectedMessage, logoutPage.loginPageTxt.getText());
        System.out.println(logoutPage.loginPageTxt.getText());
        Driver.getDriver().navigate().back();

        String sessionExpMsg= "Odoo Session Expired";
        String actualSessionMsg= logoutPage.sessionExpMsg.getText();
        System.out.println(actualSessionMsg);
        Assert.assertEquals(sessionExpMsg, actualSessionMsg);

    }
    }

