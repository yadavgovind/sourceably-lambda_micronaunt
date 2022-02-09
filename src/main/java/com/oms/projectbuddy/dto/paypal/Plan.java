package com.oms.projectbuddy.dto.paypal;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Plan {
    private String product_id;
    private String name;
    private String description;
    private String status;
    private List<BillingCycle> billing_cycles;
    private PaymentPreferences payment_preferences;
    private Taxes taxes;
}