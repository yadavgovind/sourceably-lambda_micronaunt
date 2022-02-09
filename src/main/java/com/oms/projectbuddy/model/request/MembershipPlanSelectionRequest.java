package com.oms.projectbuddy.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MembershipPlanSelectionRequest {
    private String userId;
    private String membershipPlanId;
    private String planName;
    private Long planCost;
    private String paymentCycle;
    private String paymentMode;
    //private String paymentTransactionId;
    //private String invoiceId;
    private String paymentStatus;
    //private Long paymentDate;
    //private Long nextBillingDate;
    //private Boolean planStatus;
    private Boolean trailStatus;
}
