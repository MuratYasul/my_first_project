package com.cydeo.utilities;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BrowsersUtils {
    public static void sleep (int second){
        second *=1000;
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
        }

    }
    /*
    This method accepts 3 arguments.
    Arg1: webdriver
    Arg2: expectedInUrl : for verifying if the url contains given String
        if condition matches, will break loop.
    Arg3: expectedTitle to be compared against actualTitle
     */
    public static void switchWindowAndVerify(String expectedInUrl,String expectedInTitle){
        Set<String> allWindowsHandles = Driver.getDriver().getWindowHandles();

        for (String each: Driver.getDriver().getWindowHandles()){

            Driver.getDriver().switchTo().window(each);

            System.out.println("Current URL: " + Driver.getDriver().getCurrentUrl());

            if (Driver.getDriver().getCurrentUrl().contains(expectedInUrl)){
                break;
            }
        }
        //5. Assert: Title contains "expectedInTitle"
        String actualTitle = Driver.getDriver().getTitle();

        Assert.assertTrue(actualTitle.contains(expectedInTitle));

    }
    /*
    This method accepts a String "expectedInTitle" and Asserts if it is true
     */
    public static void verifyTitle(String expectedInTitle){
        Assert.assertEquals(Driver.getDriver().getTitle(),expectedInTitle);


    }

    /*
    Creating a utility method  for ExplicitlyWait, so we don't have to repeat  the lines.
     */
    public static void waitForInvisibilityOf(WebElement webElement){
        Driver.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);//at driver default timeouts is 10 seconds, so we changed the duration in order not to wait.
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),10);
        wait.until(ExpectedConditions.invisibilityOf(webElement));


    }

    /**
     * This method will accept a String as expected value and verify actual URL CONTAINS the value.
     * @param expectedInUrl
     */
    public static void verifyURLContains(String expectedInUrl){
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(expectedInUrl));

    }

    /**
     * This method will accept a dropdown as a WebElement and return all the options texts
     * in a list of String
     * @param dropdownElement
     * @return List<String> actualOptionsAsString
     */
    public static List<String> dropdownOptionsAsString(WebElement dropdownElement) {
        Select select = new Select(dropdownElement);
        //List of all ACTUAL month <options> as a web element
        List<WebElement> actualOptionsAsWebElement = select.getOptions();

        //List of all ACTUAL month <options> as a string
        List<String> actualOptionsAsString = new ArrayList<String>();

        for (WebElement each: actualOptionsAsWebElement) {
            actualOptionsAsString.add(each.getText());//this is getting the list of the webelement options
        }
        return actualOptionsAsString;

}
    /**
     * This method will accept a group of radio buttons as a list of WebElement
     * it will loop through the List, and click to the radio button with provided attributeValue
     * @param radioButtons
     * @param attributeValue
     */
    public static void clickRadioButton(List<WebElement>radioButtons,String attributeValue){
         for (WebElement each : radioButtons) {
            if (each.getAttribute("value").equalsIgnoreCase(attributeValue)){//this value will check the webtableOrder.feature, will return with what is put there
                each.click();
            }
        }
//         public static void switchToWindow(String targetTitle){
//             String origin = Driver.getDriver().getWindowHandle();
//            for (String handle: Driver.getDriver().getWindowHandles()){
//                Driver.getDriver().switchTo().window(handle);
//                if (Driver.getDriver().switchTo().equals(targetTitle)){
//                    return;
//            }
//
//
//            }
//            Driver.getDriver().switchTo().window(origin);
        }
}



