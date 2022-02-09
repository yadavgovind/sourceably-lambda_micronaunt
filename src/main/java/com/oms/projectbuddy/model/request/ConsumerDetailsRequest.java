package com.oms.projectbuddy.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsumerDetailsRequest {
   // private Long id;
    private String userId;
    private String companyDescription;
    private String productDescription;
    private String serviceDescription;
    private String history;
    private String publicRelation;
    private String advertising;
    private String industryInfo;
    private String grossAnnualSale;
    private String geographicalServiceArea;
    private String legalStructure;
    private String safetyPolicy;
    private Boolean insecured;
    private Boolean bonded;
    private Boolean liscensed;
    private String liscenceNo;
    private String additionalInfo;
    private Boolean agreeToShare;
    private String lastUpdatedBy;
    private Long lastUpdatedEpochTime;
}
