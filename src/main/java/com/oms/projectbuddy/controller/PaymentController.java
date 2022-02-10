package com.oms.projectbuddy.controller;

import com.oms.projectbuddy.dto.StripePaymentData;
import com.oms.projectbuddy.dto.paypal.PaypalPaymentData;
import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.IPaypalService;
import com.oms.projectbuddy.services.IStripeService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;


@ExecuteOn(TaskExecutors.IO)
@Controller("/api")
public class PaymentController {
    
    @Inject
    IStripeService stripeService;

    @Inject
    IPaypalService paypalService;

    @Post("/verifyCard")
    public Object verifyCard(@Body StripePaymentData token) {
        try {
            return new EntityResponse(stripeService.verifyCard(token),0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }
    }

    @Post("/createPaypalSubscription")
    public Object createPaypalSubscription(@Body PaypalPaymentData paypalPaymentData) throws Exception {
    	return new EntityResponse(paypalService.createPaymentPlanAndSubscription(paypalPaymentData),0);
    }
}
