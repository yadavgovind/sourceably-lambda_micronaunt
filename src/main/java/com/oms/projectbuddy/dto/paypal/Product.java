package com.oms.projectbuddy.dto.paypal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product{
    private String name;
    private String description;
    private String type;
    private String category;
    private String image_url;
    private String home_url;
}
