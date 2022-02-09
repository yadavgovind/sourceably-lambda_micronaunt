package com.oms.projectbuddy.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oms.projectbuddy.dto.paypal.PaypalPaymentData;

public interface IPaypalService {

    String createProduct() throws JsonProcessingException;
    String createPaymentPlanAndSubscription(PaypalPaymentData paypalPaymentData)  throws JsonProcessingException;
}
