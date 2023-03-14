package com.fidexio.step_definitions;

/*
In the class we will be able to pass pre-& post-conditions to each scenario and each scenario step.
 */

import com.fidexio.utilities.BrowsersUtils;
import com.fidexio.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    //import from io.cucumber.java. not from junit.framework.
  //  @Before (order = 1)
    public void setupScenario(){
   //     System.out.println("====Setting up browser using cucumber @Before tag====");
    }
  //  @Before (value = "@login", order =2)
    public void setupScenarioForLogins(){
//    System.out.println("this will only apply to scenario with @login tag");
    }
  //  @Before (value = "@db", order = 0)
    public void setupForDatabaseScenarios(){
   //  System.out.println("this will only apply to scenario with @login tag");
    }
    @After
    public void teardownScenario(Scenario scenario){//in order to take screenshot we are stating here writing into the bracket scenario.

        //in order to get screenshot we are down-casting using interface TakeScreenshot() method.
        if (scenario.isFailed()) {
        byte [] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
       scenario.attach(screenshot,"image/png", scenario.getName());

        }

    byte [] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
       scenario.attach(screenshot,"image/png", scenario.getName());

//    System.out.println("====Closing browser using cucumber @After tag====");
//        System.out.println("====Scenario ended/take screenshot if failed====");
//

      BrowsersUtils.sleep(3);
      Driver.closeDriver();
    }
  @BeforeStep
    public void setupStep(){
    //  System.out.println("====> Applying setup using @BeforeStep===");
    }
    @AfterStep
   public void afterStep(){
  //     System.out.println("====> Applying tearDown using @afterStep");
}
}
