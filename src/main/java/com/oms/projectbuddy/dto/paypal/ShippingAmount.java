package com.oms.projectbuddy.dto.paypal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingAmount{
    private String currency_code;
    private String value;
}