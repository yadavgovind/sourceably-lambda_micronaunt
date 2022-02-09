package com.oms.projectbuddy.model.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MembershipPlanDisplayRequest {
    private String planId;
    private String planName;
    private String planDescription;
    private Long monthlyCost;
    private Long yearlyCost;
    private Long trialPeriodNoCost;
    private String numberOfUsers;
    private String numbersOfProfile;
    private Boolean administerAndManageProfile;
    private Boolean projectBuddyApproved;
    private Boolean createCompanyProfile;
    private Boolean serviceOfferrings;
    private Boolean cyberSecurityFramework;
    private Boolean companyProductOfferrings;
    private Boolean searchAndRespondToConsumerProjects;
    private Boolean generateProfileResponseDocuments;
    private Boolean mainProjectsDashboard;
    private Boolean securedMessagesWithCustomers;
    private Boolean shareProfileWithConsumers;
    private Boolean respondAgainstConsumerInvites;
    private Boolean certificationAttest;
    private Boolean validation;
    private Boolean portalSupport;
    private Boolean automatedQuarterlyReport;
    private Boolean quarterlyAdvisory;
    private String portalSupportnum;
    private String  quarterlyReview;
    private String profileValidation;
    private String profileAdvisory;
    private Boolean trailStatus;


}
