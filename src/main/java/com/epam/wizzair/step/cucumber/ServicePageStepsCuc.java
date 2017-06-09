package com.epam.wizzair.step.cucumber;

import com.epam.wizzair.page.ServicesPage;
import cucumber.api.java.en.When;

/**
 * Created by Aliaksandr_Krutsko on 6/8/2017.
 */
public class ServicePageStepsCuc {
    private ServicesPage servicesPage = new ServicesPage();

    @When("^User passes on services$")
    public void submitServices(){
        servicesPage.continueToNextPage();
    }

    @When("^User declines insurance offer$")
    public void user_declines_insurance() {
        servicesPage.declineInsurance();
    }

}
