package com.oms.projectbuddy.services.impl;

import com.oms.projectbuddy.dto.StripePaymentData;
import com.oms.projectbuddy.services.IStripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class StripeService implements IStripeService {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Override
    public String verifyCard(StripePaymentData data) throws StripeException {
        Stripe.apiKey = stripeApiKey;
        ChargeCreateParams params =
                ChargeCreateParams.builder()
                        .setAmount(1l)
                        .setCurrency("usd")
                        .setDescription("Test Transaction for Customer")
                        .setSource(data.getToken())
                        .build();
        Charge charge = Charge.create(params);
        return charge.getStatus();
    }
}
