package com.oms.projectbuddy.services;

import com.oms.projectbuddy.dto.StripePaymentData;
import com.stripe.exception.StripeException;


public interface IStripeService {

    String verifyCard(StripePaymentData token) throws StripeException;
}
