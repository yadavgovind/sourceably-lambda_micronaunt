package com.oms.projectbuddy.dto.paypal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethod{
    private String payer_selected;
    private String payee_preferred;
}