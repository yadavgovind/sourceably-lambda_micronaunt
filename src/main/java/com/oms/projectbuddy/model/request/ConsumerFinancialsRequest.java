package com.oms.projectbuddy.model.request;

import com.oms.projectbuddy.model.FinancialImageUpload;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class ConsumerFinancialsRequest {
    //private Long id;
    private String userId;
    private String lastYearRevenue;
    private String financialTargets;
    private String[] files;
    private String numberOfEmployees;
    private String partners;
    private String lastUpdatedBy;
    private Long lastUpdatedEpochTime;

    List<FinancialImageUpload> images;
}
