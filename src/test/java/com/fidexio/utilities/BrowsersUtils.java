package com.fidexio.utilities;

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


    public static void switchWindowAndVerify(String expectedInUrl,String expectedInTitle){
        Set<String> allWindowsHandles = Driver.getDriver().getWindowHandles();

        for (String each: Driver.getDriver().getWindowHandles()){

            Driver.getDriver().switchTo().window(each);

            System.out.println("Current URL: " + Driver.getDriver().getCurrentUrl());

            if (Driver.getDriver().getCurrentUrl().contains(expectedInUrl)){
                break;
            }
        }

        String actualTitle = Driver.getDriver().getTitle();

        Assert.assertTrue(actualTitle.contains(expectedInTitle));

    }

    public static void verifyTitle(String expectedInTitle){
        Assert.assertEquals(Driver.getDriver().getTitle(),expectedInTitle);


    }


    public static void waitForInvisibilityOf(WebElement webElement){
        Driver.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);//at driver default timeouts is 10 seconds, so we changed the duration in order not to wait.
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),10);
        wait.until(ExpectedConditions.invisibilityOf(webElement));


    }


    public static void verifyURLContains(String expectedInUrl){
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(expectedInUrl));

    }


    public static List<String> dropdownOptionsAsString(WebElement dropdownElement) {
        Select select = new Select(dropdownElement);
        //List of all ACTUAL month <options> as a web element
        List<WebElement> actualOptionsAsWebElement = select.getOptions();

        //List of all ACTUAL month <options> as a string
        List<String> actualOptionsAsString = new ArrayList<String>();

        for (WebElement each: actualOptionsAsWebElement) {
            actualOptionsAsString.add(each.getText());//this is getting the list of the element options
        }
        return actualOptionsAsString;

}

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



