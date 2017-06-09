package com.epam.wizzair.driver;

import com.epam.wizzair.helper.DriverConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

//import com.epam.wizzair.helper.ProfilesIni;

public class DriverSingleton {



    private static JavascriptExecutor js;

    private static String driverType = DriverConfig.browser();

    private static WebDriver driver = null;

    private static Deque<String> windowStack = new LinkedList<>();

    private DriverSingleton() {

    }

    public static void setDriver(String driverType) {

        DriverSingleton.driverType = driverType;
    }


    public static WebDriver getDriver() {
        if (driver == null) {
            switch (driverType.toUpperCase()) {
                case "FIREFOX":
                    ProfilesIni profile = new ProfilesIni();
                    FirefoxProfile myprofile = profile.getProfile(DriverConfig.profile());
                    System.setProperty("webdriver.gecko.driver", DriverConfig.geckodriver());
                    driver = new FirefoxDriver(myprofile);
                    break;
                case "CHROME":
                    System.setProperty("webdriver.chrome.driver", DriverConfig.chromedriver());
                    driver = new ChromeDriver();
                    break;

                default:
                    driver = new FirefoxDriver();
                    break;

            }

            if (driver instanceof JavascriptExecutor) {
                js = (JavascriptExecutor)driver;
            } else {
                throw new IllegalStateException("This driver does not support JavaScript!");
            }

            driver.manage().window().maximize();
//            windowStack.push(driver.getWindowHandle());
            driver.manage().timeouts().implicitlyWait(DriverConfig.timeout(), TimeUnit.SECONDS);
            //driver.manage().timeouts().pageLoadTimeout(DriverConfig.timeout(), TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(DriverConfig.timeout(), TimeUnit.SECONDS);
        }
        return driver;
    }

    public static void openNewWindowJS(){

        if (windowStack.isEmpty()){
            windowStack.push(driver.getWindowHandle());
            return;
        } //if no windows was opened before, leave default window

        js.executeScript("window.open();");

        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        windowStack.push(driver.getWindowHandle());
    }

    @Deprecated
    public static void openNewWindow(){

        if (windowStack.isEmpty()){
            windowStack.push(driver.getWindowHandle());
            return;
        } //if no windows was opened before, leave default window

        WebElement body = driver.findElement(By.tagName("body"));
        body.sendKeys(Keys.CONTROL + "n");

        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        windowStack.push(driver.getWindowHandle());
    }


    public static void closeWindow(){

        if (windowStack.size() == 1){
//            windowStack.poll();
//            driver.get("about:blank");//this code open blank page when trying to close last window
            quit();
            return;
        } //if only base window left, just opening blank

        driver.close();
        windowStack.poll();
        driver.switchTo().window(windowStack.peek());
    }

    public static void excuteScript(String script){
        js.executeScript(script);
    }

    public static void open(String url){
        getDriver().get(url);
    }

    public static void quit(){

        windowStack.removeAll(windowStack);
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }

    
}
