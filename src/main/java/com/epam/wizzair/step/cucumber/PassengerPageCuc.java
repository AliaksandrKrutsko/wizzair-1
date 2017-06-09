package com.epam.wizzair.step.cucumber;

import com.epam.wizzair.bean.Baggage;
import com.epam.wizzair.bean.PassengerData;
import com.epam.wizzair.helper.TestData;
import com.epam.wizzair.page.InfoColumnPage;
import com.epam.wizzair.page.Passenger;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.List;
import java.util.Set;

import static com.epam.wizzair.step.util.Util.*;

/**
 * Created by Aliaksandr_Krutsko on 6/8/2017.
 */
public class PassengerPageCuc {

    PassengerData expectedPassengerData;
    PassengerData actualPassengerData;
    Passenger passenger = new Passenger();

    @Then("^User compares expected passenger data with actual passenger data$")
    public void user_compares_passenger_data() {
        Assert.assertEquals(expectedPassengerData, actualPassengerData);
    }

    @When("^User fills the fields with the expected passenger information from file$")
    public void user_fills_with_passenger_information_from_file() {
        PassengerData data = TestData.getPassengerData();
        passenger.setCabinBaggage(data.getDepBaggage().getCabinBaggage(), data.getRetBaggage().getCabinBaggage());
        passenger.setCheckedInBaggage(data.getDepBaggage().getCheckedBaggage(), data.getRetBaggage().getCheckedBaggage());
        passenger.setSportEquipment(data.getDepBaggage().isSportEquipment(), data.getRetBaggage().isSportEquipment());
        passenger.setCheckInMethod(data.getDepCheckinMethod(), data.getRetCheckinMethod());
        this.expectedPassengerData = data;
    }

    @When("^User fills the fields with the passenger information$")
    public void fillPassenger(List<PassengerData> list) {
        for(PassengerData data : list) {
            passenger.setCabinBaggage(data.getDepBaggage().getCabinBaggage(), data.getRetBaggage().getCabinBaggage());
            passenger.setCheckedInBaggage(data.getDepBaggage().getCheckedBaggage(), data.getRetBaggage().getCheckedBaggage());
            passenger.setSportEquipment(data.getDepBaggage().isSportEquipment(), data.getRetBaggage().isSportEquipment());
            passenger.setCheckInMethod(data.getDepCheckinMethod(), data.getRetCheckinMethod());
        }
    }

    @When("^User gets actual passenger data$")
    public PassengerData fillColumnData() {

        PassengerData actualPassengerData = new PassengerData();
        actualPassengerData.setDepBaggage(new Baggage());
        actualPassengerData.setRetBaggage(new Baggage());//creating bean for filling data in
        String fullName = InfoColumnPage.getInstance().getPassengerFullName();
        parseAndFillPassengerName(actualPassengerData, fullName);
        Set<String> rawBookingData = InfoColumnPage.getInstance().getDepPassengerRawData();//getting raw data from page
        for (String s :
                rawBookingData) {
            parseAndFillPassenger(s, actualPassengerData, true); //true stands for departure
        }//parsing raw data and filling bean for departure
        rawBookingData = InfoColumnPage.getInstance().getRetPassengerRawData();
        for (String s :
                rawBookingData) {
            parseAndFillPassenger(s, actualPassengerData, false); //true stands for departure
        }//parsing raw data and filling bean for return
        fillNonMentionedWithDefaults(actualPassengerData);//filling non mentioned values in page with its default values
        this.actualPassengerData = actualPassengerData;
        return actualPassengerData;
    }

    @When("^User fills baggage$")
    public void user_fills_baggage() {
        Baggage baggage;
        baggage = TestData.getBaggage();
        passenger.setCabinBaggage(baggage.getCabinBaggage());
        passenger.setCheckedInBaggage(baggage.getCheckedBaggage());
        passenger.setSportEquipment(baggage.isSportEquipment());
    }

    @When("^User goes to seat selection page$")
    public void goToSeatSelectionPage() {
        passenger.gotoDepartureSeatSelection();
    }

    @When("^User returns to seat selection$")
    public void goToRetSeatSelection() {
        passenger.gotoReturnSeatSelection();
    }

    @When("^User submits selection$")
    public void submit() {
        passenger.submit();
    }

}
