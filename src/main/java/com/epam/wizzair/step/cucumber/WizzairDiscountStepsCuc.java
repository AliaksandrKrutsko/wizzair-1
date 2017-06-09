package com.epam.wizzair.step.cucumber;

import com.epam.wizzair.page.WizzDiscountPage;
import cucumber.api.java.en.When;


public class WizzairDiscountStepsCuc {

    private WizzDiscountPage discountPage = new WizzDiscountPage();

    @When("^User declines discount offer$")
    public void continueToNextPage(){
        discountPage.declineOffer();
    }



}
