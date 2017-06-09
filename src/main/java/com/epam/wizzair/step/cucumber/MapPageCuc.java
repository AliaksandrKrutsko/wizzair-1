package com.epam.wizzair.step.cucumber;

import com.epam.wizzair.page.MapPage;
import com.epam.wizzair.page.TimetablePage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class MapPageCuc {

    private String origin;
    private String destination;

    MapPage mapPage = new MapPage();

    @When("^User chooses route")
    public void user_chooses_route() {
        mapPage.chooseFirstCity().chooseSecondCity();
        this.origin = mapPage.getOriginName();
        this.destination = mapPage.getDestinationName();
    }

    @Then("^User goes to timetable to check if map search was successful$")
    public void split() {
        TimetablePage timetablePage = new TimetablePage();
        String[] origin = this.origin.split(" ");
        String[] destination = this.destination.split(" ");
        mapPage.search();
        String route = timetablePage.getTextFromAddressField();
        Assert.assertTrue(route.contains(origin[0]));
        Assert.assertTrue(route.contains(destination[0]));
    }


}
