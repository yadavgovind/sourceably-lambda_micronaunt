package com.oms.projectbuddy.dto.paypal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Frequency{
    private String interval_unit;
    private int interval_count;
}
