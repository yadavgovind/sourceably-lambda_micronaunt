package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pb_membership_plan_display")
public class MembershipPlanDisplay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="plan_id")
    private String planId;
    @Column(name="plan_name")
    private String planName;
    @Column(name="plan_description")
    private String planDescription;
    @Column(name="monthly_cost")
    private String monthlyCost;
    @Column(name="yearly_cost")
    private String yearlyCost;
    @Column(name="yearly_cost_inr")
    private String yearlyCostInr;
    @Column(name="monthly_cost_inr")
    private String monthlyCostInr;
    @Column(name="trial_period_no_Cost")
    private Long trialPeriodNoCost;
    @Column(name="number_of_users")
    private String numberOfUsers;
    @Column(name="numbers_of_profile")
    private String numbersOfProfile;
    @Column(name="administer_and_manage_profile")
    private Boolean administerAndManageProfile;
    @Column(name="project_buddy_approved")
    private Boolean projectBuddyApproved;
    @Column(name="create_company_profile")
    private Boolean createCompanyProfile;
    @Column(name="service_offerrings")
    private Boolean serviceOfferrings;
    @Column(name="cyber_security_framework")
    private Boolean cyberSecurityFramework;
    @Column(name="company_product_offerrings")
    private Boolean companyProductOfferrings;
    @Column(name="search_and_respond_to_consumer_projects")
    private Boolean searchAndRespondToConsumerProjects;
    @Column(name="generate_profile_response_documents")
    private Boolean generateProfileResponseDocuments;
    @Column(name="main_projects_dashboard")
    private Boolean mainProjectsDashboard;
    @Column(name="secured_messages_with_customers")
    private Boolean securedMessagesWithCustomers;
    @Column(name="share_profile_with_consumers")
    private Boolean shareProfileWithConsumers;
    @Column(name="respond_against_consumer_invites")
    private Boolean respondAgainstConsumerInvites;
    @Column(name="certification_attest")
    private Boolean certificationAttest;
    @Column(name="validation")
    private Boolean validation;
    @Column(name="portal_support")
    private Boolean portalSupport;
    @Column(name="automated_quarterly_report")
    private Boolean automatedQuarterlyReport;
    @Column(name="quarterly_advisory")
    private Boolean quarterlyAdvisory;
    @Column(name="portal_support_num")
    private String portalSupportnum;
    @Column(name="quarterly_review")
    private String  quarterlyReview;
    @Column(name="profile_validation")
    private String profileValidation;
    @Column(name="profile_advisory")
    private String profileAdvisory;
    @Column(name="created_epoch_time")
    private Long createdEpochTime;
    @Column(name="created_by")
    private String createdBy;
    @Column(name="updated_epoch_time")
    private Long updatedEpochTime;
    @Column(name="updated_by")
    private String updatedBy;
    @Column(name="status")
    private Boolean status;
    @Column(name = "trailStatus")
    private Boolean trailStatus;

    @Transient
    private String currency;

}
