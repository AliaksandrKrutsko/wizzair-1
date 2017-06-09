package com.epam.wizzair.step;

import com.epam.wizzair.page.SearchResult;
import com.epam.wizzair.page.TimetablePage;

/**
 * Created by Nadzeya_Parkhimovich on 13-Mar-17.
 */
public class TimeTableSteps {

    private String firstFlightPrice;
    private String summaryPrice;

    private String firstFlightPriceInSearch;
    private String summaryPriceInSearch;
    TimetablePage timetablePage = new TimetablePage();

    public TimeTableSteps findFlight(String origin, String destination) {

        timetablePage.fillOrigin(origin);
        timetablePage.fillDestination(destination);
        timetablePage.search();
        timetablePage.chooseFirstFlight();
        firstFlightPrice = timetablePage.getFirstFlightPrice();
        summaryPrice = timetablePage.getSummaryPrice();
        timetablePage.startBooking();
        return this;
    }

    public TimeTableSteps findFlightInSearchPage() {
        SearchResult searchResult = new SearchResult();
        firstFlightPriceInSearch = searchResult.chooseFirstFlight();
        summaryPriceInSearch = searchResult.getTotalPrice();
        return this;
    }

    public String getFirstFlightPrice() {
        return firstFlightPrice;
    }

    public String getFirstFlightPriceInSearch() {
        return firstFlightPriceInSearch;
    }

    public TimeTableSteps closePopUp() {
        timetablePage.getRidOfNewsletterBar();
        return this;
    }


    public StepsForSearchResult findBothFlights(String origin, String destination, String month) {
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
        return new StepsForSearchResult();
    }


    public String getTextFromAddressField() {
        return timetablePage.getTextFromAddressField();
    }


//    This methods will be used soon :)
//    public String getSummaryPriceInSearch() {
//        return summaryPriceInSearch;
//    }
//
//    public String getSummaryPrice() {
//        return summaryPrice;
//    }
//
//    public String getSecondFlightPrice() {
//        return secondFlightPrice;
//    }
//
//    public String getSecondFlightPriceInSearch() {
//        return secondFlightPriceInSearch;
//    }
}
