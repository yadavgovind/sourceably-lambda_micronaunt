package com.oms.projectbuddy.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderMailRequest {

    private String providerName;
    private String address;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private String to;

}
