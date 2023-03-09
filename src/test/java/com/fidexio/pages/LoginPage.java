package com.fidexio.pages;

import com.fidexio.utilities.BrowsersUtils;
import com.fidexio.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(id ="login")
    public WebElement userNameInput;

    @FindBy(id ="password")
    public WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy(id ="login")
    public WebElement inputEmail;

    @FindBy(id ="password")
    public WebElement inputPassword;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginBtn;
    @FindBy(xpath ="//p[contains(text(),'Wrong login/password')]")
    public WebElement wrongCredentials;



}
