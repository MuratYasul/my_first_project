package com.fidexio.pages;

import com.fidexio.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy(id ="login")
    public WebElement inputEmail;

    @FindBy(id ="password")
    public WebElement inputPassword;

    @FindBy(xpath ="//p[contains(text(),'Wrong login/password')]")
    public WebElement wrongCredentials;

    @FindBy(xpath = "//span[@class='oe_topbar_name']")
    public WebElement userName;

    @FindBy(xpath= "//a[.='Log out']")
    public WebElement logoutBtn;

}
