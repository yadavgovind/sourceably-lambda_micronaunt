package com.oms.projectbuddy.dto.paypal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Subscription{
    private String plan_id;
    private String start_time;
    private String quantity;
    private ShippingAmount shipping_amount;
    private Subscriber subscriber;
    private ApplicationContext application_context;
}
