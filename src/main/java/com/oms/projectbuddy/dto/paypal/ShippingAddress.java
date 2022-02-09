package com.oms.projectbuddy.dto.paypal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ShippingAddress{
    public Name name;
    public Address address;
}