package com.epam.wizzair.step.cucumber;

import com.epam.wizzair.page.SearchResult;
import com.epam.wizzair.page.TimetablePage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class TimeTableCuc {

    TimetablePage timetablePage = new TimetablePage();


    private String firstFlightPrice;
    private String summaryPrice;

    private String firstFlightPriceInSearch;
    private String summaryPriceInSearch;

    @When("^User enters \"([^\\\"]*)\" in origin field and \"([^\\\"]*)\" in destination field$")
    public void user_enters_origin_and_destination(String origin, String destination) {
        timetablePage.fillOrigin(origin);
        timetablePage.fillDestination(destination);
        timetablePage.search();
        timetablePage.chooseFirstFlight();
        this.firstFlightPrice = timetablePage.getFirstFlightPrice();
        summaryPrice = timetablePage.getSummaryPrice();
        timetablePage.startBooking();
    }

    @When("^User gets on search page and searches for flight$")
    public void user_gets_on_search_page() {
        SearchResult searchResult = new SearchResult();
        this.firstFlightPriceInSearch = searchResult.chooseFirstFlight();
        summaryPriceInSearch = searchResult.getTotalPrice();
    }

    @Then("^User asserts that flight price in timetable and flight price in search page are equal$")
    public void user_asserts_prices_are_equal() {
        Assert.assertEquals(this.firstFlightPrice, this.firstFlightPriceInSearch);
    }

    @When("^User enters \"([^\\\"]*)\" in origin field and \"([^\\\"]*)\" in destination field and \"([^\\\"]*)\" in month field$")
    public void pick_both_flights(String origin, String destination, String month) {
        timetablePage.fillOrigin(origin);
        timetablePage.fillDestination(destination);
        timetablePage.search();
        timetablePage.chooseFirstFlight();
        timetablePage.chooseRetMonthMarch(month);
        timetablePage.chooseSecondFlight();
        timetablePage.getRidOfNewsletterBar();
        firstFlightPrice = timetablePage.getFirstFlightPrice();
        summaryPrice = timetablePage.getSummaryPrice();
        timetablePage.startBooking();
    }




}
