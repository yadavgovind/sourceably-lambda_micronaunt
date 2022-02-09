package com.oms.projectbuddy.dto.paypal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillingCycle{
    private Frequency frequency;
    private String tenure_type;
    private int sequence;
    private int total_cycles;
    private PricingScheme pricing_scheme;
}