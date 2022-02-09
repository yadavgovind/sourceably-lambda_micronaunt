package com.oms.projectbuddy.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessBasicInfoResponse {

    private Long id;
    private String userId;
    private String companyName;
    private String addressPerLocation;
    private String phoneNumber;
    private String alternateNumber;
    private String faxNumber;
    private String email;
    private String pointOfContact;
    private String establishedDate;
    private String companyWebsite;
    private String yearsOfRegistered;
    private String companyVideo;
    private String stockPrice;
    private String alternateEmail;
    private String ceoName;
    private String founders;
   // private String headquarters;
    private String headquartersCountry;
    private String headquartersState;
    private String headquartersCity;
    private String serviceName;
    private String tagLine;
    private String companyDescription;
    private String products;
    private String subsidiary;
    private Boolean agreeToShare;
    private String lastUpdatedBy;
    private Long updatedEpochTime;
}
