package com.oms.projectbuddy.dto.paypal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FixedPrice{
    private String value;
    private String currency_code;
}