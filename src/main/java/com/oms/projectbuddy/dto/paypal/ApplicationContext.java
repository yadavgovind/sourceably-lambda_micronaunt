package com.oms.projectbuddy.dto.paypal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationContext {
    private String brand_name;
    private String locale;
    private String shipping_preference;
    private String user_action;
    private PaymentMethod payment_method;
    private String return_url;
    private String cancel_url;
}
