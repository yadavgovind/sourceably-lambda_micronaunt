package com.oms.projectbuddy.dto.paypal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Subscriber{
    private Name name;
    private String email_address;
    private ShippingAddress shipping_address;
}