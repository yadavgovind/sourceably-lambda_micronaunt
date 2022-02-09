package com.oms.projectbuddy.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderCriteraRequest {
    private String countryName;
    private String cityName;
    private String stateName;
    private String minInsurance;
}
