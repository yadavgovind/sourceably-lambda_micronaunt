package com.oms.projectbuddy.dto.paypal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payer {
    private String payment_method;
    Payer_info payer_info;
}
