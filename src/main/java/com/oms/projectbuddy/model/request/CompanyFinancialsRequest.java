package com.oms.projectbuddy.model.request;

import com.oms.projectbuddy.model.FinancialImageUpload;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
public class CompanyFinancialsRequest {
    //private Long id;
    private String userId;
    private String lastYearRevenue;
    private String financialTargets;
    private String[] filesUrls;
    private String numberOfEmployees;
    private String partners;
    private String lastUpdatedBy;
    private Long lastUpdatedEpochTime;


   List<FinancialImageUpload> images;




}
