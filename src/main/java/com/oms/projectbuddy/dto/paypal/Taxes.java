package com.oms.projectbuddy.dto.paypal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Taxes{
    private String percentage;
    private boolean inclusive;
}
