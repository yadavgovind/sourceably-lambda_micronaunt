package com.oms.projectbuddy.dto.paypal;

import lombok.Data;

@Data
public class PaypalPaymentData {
    private String paypalReturnUrl;
    private String paypalCancelUrl;
}
