package com.fidexio.pages;

import com.fidexio.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {
    public LogoutPage(){
        PageFactory.initElements(Driver.getDriver(), this);

    }

    @FindBy(xpath = "//a[.='Best solution for startups']")
    public WebElement loginPageTxt;

    @FindBy(xpath = "//h4[.='Odoo Session Expired']")
    public WebElement sessionExpMsg;





    }


