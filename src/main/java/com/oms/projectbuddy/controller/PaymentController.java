package com.oms.projectbuddy.controller;

import com.oms.projectbuddy.dto.StripePaymentData;
import com.oms.projectbuddy.dto.paypal.PaypalPaymentData;
import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.IPaypalService;
import com.oms.projectbuddy.services.IStripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ExecuteOn(TaskExecutors.IO)
@Controller("/api")
public class PaymentController {
    
    @Autowired
    IStripeService stripeService;

    @Autowired
    IPaypalService paypalService;

    @PostMapping("/verifyCard")
    public ResponseEntity<?> verifyCard(@RequestBody StripePaymentData token) {
        try {
            return new ResponseEntity<>(new EntityResponse(stripeService.verifyCard(token),0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createPaypalSubscription")
    public ResponseEntity<?> createPaypalSubscription(@RequestBody PaypalPaymentData paypalPaymentData) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(paypalService.createPaymentPlanAndSubscription(paypalPaymentData),0), HttpStatus.OK);
    }
}
