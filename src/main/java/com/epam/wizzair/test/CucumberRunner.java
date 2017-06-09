package com.epam.wizzair.test;

import com.epam.wizzair.step.cucumber.MainPageCuc;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterMethod;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/main/resources/features"}, glue = {"com.epam.wizzair.step.cucumber"},
        format = {"pretty", "html:target/reports/cucumber/html", "json:target/cucumber.json"})
public class CucumberRunner {

    @AfterMethod
    public void closeWindow() {
        MainPageCuc main = new MainPageCuc();
        main.closeWindow();
    }

}
