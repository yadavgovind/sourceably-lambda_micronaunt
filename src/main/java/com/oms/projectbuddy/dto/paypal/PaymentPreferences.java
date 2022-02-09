package com.oms.projectbuddy.dto.paypal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentPreferences{
    private boolean auto_bill_outstanding;
    private SetupFee setup_fee;
    private String setup_fee_failure_action;
    private int payment_failure_threshold;
}