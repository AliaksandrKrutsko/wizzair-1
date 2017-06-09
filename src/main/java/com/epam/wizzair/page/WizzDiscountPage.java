package com.epam.wizzair.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class WizzDiscountPage extends AbstractPage {

    public WizzDiscountPage() {
    }

    @FindBy(css = "[data-test=\"booking-wdc-submit\"")
    private WebElement decline;

    public WizzDiscountPage declineOffer() {
        decline.click();
        return this;
    }


}
