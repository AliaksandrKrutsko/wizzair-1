package com.epam.wizzair.step.cucumber;

import com.epam.wizzair.page.SelectSeatPage;
import cucumber.api.java.en.When;

/**
 * Created by Aliaksandr_Krutsko on 6/8/2017.
 */
public class SelectSeatStepsCuc {

    private SelectSeatPage selectSeatPage = new SelectSeatPage();
    private String selectedSeatNumber;



    @When("^User selects seat$")
    public void selectSeatWizzAir()
    {
        selectSeatPage.selectRandomAvailableSeat();
        selectedSeatNumber = selectSeatPage.getSelectedSeatNumber();
        selectSeatPage.clickClosePageButton();
        selectSeatPage.clickSaveResultButton();

    }




}
