package com.fidexio.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Driver {

    /*
    Creating a private constructor, so we are closing
    access to the object of this class from outside the class
     */
    private Driver(){}

    /*
    We make WebDriver private, because we want to close access from outside the class.
    We make it static because we will use it in a static method.
     */
    //private static WebDriver driver; // value is null by default

    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    /*
    Create a re-usable utility method which will return same driver instance when we call it
     */
    public static WebDriver getDriver(){

        if (driverPool.get() == null){

            /*
            We read our browserType from configuration.properties.
            This way, we can control which browser is opened from outside our code, from configuration.properties.
             */
            String browserType = ConfigurationReader.getProperty("browser");


            /*
                Depending on the browserType that will be return from configuration.properties file
                switch statement will determine the case, and open the matching browser
            */
            switch (browserType){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "remote-chrome":
                    // assign your grid server address
                    String gridAdress = "http://54.208.73.102:8081"; // put your own Linux grid IP here
                    try {
                        URL url = new URL("http://"+gridAdress+":4444/wd/hub");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("chrome");
                        driverPool.set(new RemoteWebDriver(url,desiredCapabilities));
                        driverPool.get().manage().window().maximize();
                        driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "saucelab-chrome":
                    try{
                        URL url = new URL("https://oauth-sdetoscar-844c8:66e7117f-390e-4556-8105-07af96a01f7a@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("chrome");
                        driverPool.set(new RemoteWebDriver(url,desiredCapabilities));
                        driverPool.get().manage().window().maximize();
                        driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    }catch (MalformedURLException e){
                        e.printStackTrace();
                    }

                    break;
                case "saucelab-edge":
                    EdgeOptions browserOptions = new EdgeOptions();
                    browserOptions.setCapability("platformName", "Windows 11");
                    browserOptions.setCapability("browserVersion", "latest");
                    Map<String, Object> sauceOptions = new HashMap<>();
                    sauceOptions.put("build", "<your build id>");
                    sauceOptions.put("name", "<your test name>");
                    browserOptions.setCapability("sauce:options", sauceOptions);

                    URL url = null;
                    try {
                        url = new URL("https://oauth-sdetoscar-844c8:66e7117f-390e-4556-8105-07af96a01f7a@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
                        driverPool.set(new RemoteWebDriver(url,browserOptions));
                        driverPool.get().manage().window().maximize();
                        driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;

            }

        }

        return driverPool.get();

    }

    /*
    This method will make sure our driver value is always null after using quit() method
     */
    public static void closeDriver(){
        if (driverPool.get() != null){
            driverPool.get().quit(); // this line will terminate the existing session. value will not even be null
            driverPool.remove();
        }
    }






























//    static String browser;
//
//    private Driver() {
//    }
//
//    private static WebDriver driver;
//
//    public static WebDriver getDriver() {
//
//        if (driver == null) {
//            if (System.getProperty("BROWSER") == null) {
//                browser = ConfigurationReader.getProperty("browser");
//            } else {
//                browser = System.getProperty("BROWSER");
//            }
//            System.out.println("Browser: " + browser);
//            switch (browser) {
//
//                case "remote-chrome":
//
//                    try {
//                        // assign your grid server address
//                        String gridAddress = "";
//                        URL url = new URL("http://" + gridAddress + ":4444/wd/hub");
//                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//                        desiredCapabilities.setBrowserName("chrome");
//                        driver = new RemoteWebDriver(url, desiredCapabilities);
//                        driver.manage().window().maximize();
//                        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case "chrome":
//                    WebDriverManager.chromedriver().setup();
//                    driver = new ChromeDriver();
//                    driver.manage().window().maximize();
//                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                    break;
//
//                case "chrome-headless":
//                    WebDriverManager.chromedriver().setup();
//                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
//                    driver.manage().window().maximize();
//                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                    break;
//
//                case "firefox":
//                    WebDriverManager.firefoxdriver().setup();
//                    driver = new FirefoxDriver();
//                    driver.manage().window().maximize();
//                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                    break;
//
//                case "firefox-headless":
//                    WebDriverManager.firefoxdriver().setup();
//                    driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
//                    driver.manage().window().maximize();
//                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                    break;
//
//                case "ie":
//
//                    if (System.getProperty("os.name").toLowerCase().contains("mac")) {
//                        throw new WebDriverException("Your operating system does not support the requested browser");
//                    }
//                    WebDriverManager.iedriver().setup();
//                    driver = new InternetExplorerDriver();
//                    driver.manage().window().maximize();
//                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                    break;
//
//                case "edge":
//
//                    if (System.getProperty("os.name").toLowerCase().contains("mac")) {
//                        throw new WebDriverException("Your operating system does not support the requested browser");
//                    }
//                    WebDriverManager.edgedriver().setup();
//                    driver = new EdgeDriver();
//                    driver.manage().window().maximize();
//                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                    break;
//
//                case "safari":
//
//                    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
//                        throw new WebDriverException("Your operating system does not support the requested browser");
//                    }
//                    WebDriverManager.getInstance(SafariDriver.class).setup();
//                    driver = new SafariDriver();
//                    driver.manage().window().maximize();
//                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                    break;
//            }
//        }
//
//        return driver;
//    }
//  public static void closeDriver (){
//        if (driver !=null){
//            driver.quit();
//            driver=null;
//        }
//  }

        }
