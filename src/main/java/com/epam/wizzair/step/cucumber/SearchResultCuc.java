package com.epam.wizzair.step.cucumber;

import com.epam.wizzair.bean.FlightData;
import com.epam.wizzair.helper.TestData;
import com.epam.wizzair.page.SearchResult;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class SearchResultCuc {

    SearchResult searchResult = new SearchResult();
    private static int priceMinusCurrencySign = 2;
    private String flightSumFromLeftWindow;
    private String flightSumFromLeftWindowWithInfant;
    private String flightSumAfterSigningIn;
    private static String wrongReturnData = "testDataWithWrongReturnData";

    @Then("^User checks sum for flight to be equal$")
    public void user_checks_sum() {
        String firstFlightPrice = searchResult.chooseFirstFlight().substring(priceMinusCurrencySign);
        String secondFlightPrice = searchResult.chooseSecondFlight().substring(priceMinusCurrencySign);
        double sum = Double.parseDouble(firstFlightPrice) + Double.parseDouble(secondFlightPrice);
        double sum2 = Double.parseDouble(searchResult.getTotalPrice().substring(priceMinusCurrencySign));
        Assert.assertEquals(sum, sum2);
    }

    @When("^User picks exact flights$")
    public void user_picks_exact_flights() {
        searchResult.chooseFirstFlight();
        searchResult.chooseSecondFlight();
        searchResult.continueToNextPage();
    }


    @When("^User gets price for current flight$")
    public void get_price_for_current_flight() {
        flightSumFromLeftWindow = searchResult.getTotalPrice().substring(priceMinusCurrencySign);
    }

    @When("^User get price for flight with infant$")
    public void get_price_for_flight_with_infant() {
        flightSumFromLeftWindowWithInfant = searchResult.getTotalPrice().substring(priceMinusCurrencySign);
    }

    @Then("^User compares flight price with infant to flight price without infant$")
    public void compare_prices() {
        Assert.assertEquals(flightSumFromLeftWindow, flightSumFromLeftWindowWithInfant);
    }

    @When("^User picks exact departure flight$")
    public void pick_exact_dep_flight(){
        searchResult.chooseFirstFlight();
    }

    @When("^User gets price for current flight after signing in$")
    public void get_price_after_logging() {
        flightSumAfterSigningIn = searchResult.getTotalPrice().substring(priceMinusCurrencySign);
    }

    @Then("^User compares prices before and after signing in$")
    public void compare_prices_before_and_after_signing_in() {
        Assert.assertEquals(flightSumFromLeftWindow, flightSumAfterSigningIn);
    }

    @Then("^User checks if button is disabled$")
    public void check_if_button_disabled() {
        Assert.assertFalse(searchResult.isButtonEnabled());
    }

    @When("^User picks wrong return flight$")
    public void pick_wrong_flight() {
        TestData.setPropertyFile(wrongReturnData);
        FlightData data = TestData.getFlightData();
        searchResult.chooseWrongFlight(data.getRetDate());
    }

}
