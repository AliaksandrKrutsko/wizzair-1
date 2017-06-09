package com.epam.wizzair.step.cucumber;

import com.epam.wizzair.bean.FlightData;
import com.epam.wizzair.helper.TestData;
import com.epam.wizzair.page.MainPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

public class MainPageCuc {

    MainPage mainPage = new MainPage();
    private static String testDataWithInfant = "testDataWithInfant";
    private static String wrongReturnData = "testDataWithWrongReturnData";

    public void closeWindow(){
        mainPage.closeWindow();
    }

    @Given("^User is on main page$")
    public void user_is_on_main_page(){
        mainPage.openPage();
    }

    @When("^User signs in$")
    public void user_signs_in() {
        mainPage.signIn();
    }

    @When("^User finds flight with data from file$")
    public void find_flight_with_data_from_file() {
        FlightData data = TestData.getFlightData();
        mainPage.fillOrigin(data.getOrigin());
        mainPage.fillDestination(data.getDestination());
        mainPage.fillDepartureDate(data.getDepDate());
        mainPage.fillReturnDate(data.getRetDate());
        mainPage.setPassenger(data.getPassenger(), data.getNumberOfPassengers());
        mainPage.search();
    }

    @When("^User finds flight with infant$")
    public void user_books_flight_with_infant() {
        TestData.setPropertyFile(testDataWithInfant);
        FlightData data = TestData.getFlightData();
        mainPage.fillOrigin(data.getOrigin());
        mainPage.fillDestination(data.getDestination());
        mainPage.fillDepartureDate(data.getDepDate());
        mainPage.fillReturnDate(data.getRetDate());
        mainPage.setPassenger(data.getPassenger(), data.getNumberOfPassengers());
        mainPage.search();
    }

    @When("^User finds flight$")
    public void user_finds_flight(List<FlightData> list){
        for(FlightData data : list) {
            mainPage.fillOrigin(data.getOrigin());
            mainPage.fillDestination(data.getDestination());
            mainPage.fillDepartureDate(data.getDepDate());
            mainPage.fillReturnDate(data.getRetDate());
            mainPage.setPassenger(data.getPassenger(), data.getNumberOfPassengers());
            mainPage.search();
        }
    }

    @When("^User finds flight with wrong return data$")
    public void user_books_wrong_flight() {
        TestData.setPropertyFile(wrongReturnData);
        FlightData data = TestData.getFlightData();
        mainPage.fillOrigin(data.getOrigin());
        mainPage.fillDestination(data.getDestination());
        mainPage.fillDepartureDate(data.getDepDate());
        mainPage.fillReturnDate(data.getRetDate());
        mainPage.setPassenger(data.getPassenger(), data.getNumberOfPassengers());
        mainPage.search();
    }

    @When("^User opens timetable$")
    public void user_opens_timetable(){
        mainPage.timetableClick();
    }

    @When("^User opens map$")
    public void user_opens_map() {
        mainPage.servicesClick().mapClick();
    }

    @Then("^User returns to main page$")
    public void user_returns_to_main_page() {
        mainPage.openPage();
    }


}
