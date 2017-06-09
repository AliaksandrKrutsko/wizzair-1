package com.epam.wizzair.step.cucumber;

import com.epam.wizzair.bean.BillingDetailsPersonal;
import com.epam.wizzair.bean.CreditCardData;
import com.epam.wizzair.helper.TestData;
import com.epam.wizzair.page.PaymentPage;
import com.epam.wizzair.page.RejectPaymentPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

/**
 * Created by Aliaksandr_Krutsko on 6/8/2017.
 */
public class PaymentPageStepsCuc {

    PaymentPage paymentPage = new PaymentPage();

    @When("^User fills personal billing details$")
    public void fillBillingDetails() {
        BillingDetailsPersonal data = TestData.getBillingDetailsPersonal();
        paymentPage.setFirstName(data.getFirstName())
                .setLastName(data.getSecondName())
                .setEmail(data.getEmail())
                .setStreet(data.getAddress())
                .setCity(data.getCity())
                .setPhone(data.getPhone())
                .setPostcode(data.getPostIndex());
    }

    @When("^User fills credit card data$")
    public void fillCreditCard() {
        CreditCardData data = TestData.getCreditCardData();
        paymentPage.setCardName(data.getCardHolder())
                .setCardNumber(data.getCardNumber())
                .setCardCvv(String.valueOf(data.getSecCode()));
    }

    @When("^User accepts the privacy policy$")
    public void user_accepts_privacy_policy() {
        paymentPage.acceptPolicy();
    }

    @Then("^User gets message of rejection$")
    public void get_rejected_message() {
        RejectPaymentPage rejectPaymentPage = new RejectPaymentPage();
        Assert.assertEquals(rejectPaymentPage.getRejectMessage(), "It seems your bank rejected the payment");
    }



}
