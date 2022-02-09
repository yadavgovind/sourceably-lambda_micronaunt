package com.oms.projectbuddy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GlobalMarketData {
    private String month;
    private Double aquirecreations;
    private Double globalMarket;
}
