package com.oms.projectbuddy.model;

import javax.persistence.*;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanDescription() {
        return planDescription;
    }

    public void setPlanDescription(String planDescription) {
        this.planDescription = planDescription;
    }

    public String getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(String monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    public String getYearlyCost() {
        return yearlyCost;
    }

    public void setYearlyCost(String yearlyCost) {
        this.yearlyCost = yearlyCost;
    }

    public String getYearlyCostInr() {
        return yearlyCostInr;
    }

    public void setYearlyCostInr(String yearlyCostInr) {
        this.yearlyCostInr = yearlyCostInr;
    }

    public String getMonthlyCostInr() {
        return monthlyCostInr;
    }

    public void setMonthlyCostInr(String monthlyCostInr) {
        this.monthlyCostInr = monthlyCostInr;
    }

    public Long getTrialPeriodNoCost() {
        return trialPeriodNoCost;
    }

    public void setTrialPeriodNoCost(Long trialPeriodNoCost) {
        this.trialPeriodNoCost = trialPeriodNoCost;
    }

    public String getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(String numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public String getNumbersOfProfile() {
        return numbersOfProfile;
    }

    public void setNumbersOfProfile(String numbersOfProfile) {
        this.numbersOfProfile = numbersOfProfile;
    }

    public Boolean getAdministerAndManageProfile() {
        return administerAndManageProfile;
    }

    public void setAdministerAndManageProfile(Boolean administerAndManageProfile) {
        this.administerAndManageProfile = administerAndManageProfile;
    }

    public Boolean getProjectBuddyApproved() {
        return projectBuddyApproved;
    }

    public void setProjectBuddyApproved(Boolean projectBuddyApproved) {
        this.projectBuddyApproved = projectBuddyApproved;
    }

    public Boolean getCreateCompanyProfile() {
        return createCompanyProfile;
    }

    public void setCreateCompanyProfile(Boolean createCompanyProfile) {
        this.createCompanyProfile = createCompanyProfile;
    }

    public Boolean getServiceOfferrings() {
        return serviceOfferrings;
    }

    public void setServiceOfferrings(Boolean serviceOfferrings) {
        this.serviceOfferrings = serviceOfferrings;
    }

    public Boolean getCyberSecurityFramework() {
        return cyberSecurityFramework;
    }

    public void setCyberSecurityFramework(Boolean cyberSecurityFramework) {
        this.cyberSecurityFramework = cyberSecurityFramework;
    }

    public Boolean getCompanyProductOfferrings() {
        return companyProductOfferrings;
    }

    public void setCompanyProductOfferrings(Boolean companyProductOfferrings) {
        this.companyProductOfferrings = companyProductOfferrings;
    }

    public Boolean getSearchAndRespondToConsumerProjects() {
        return searchAndRespondToConsumerProjects;
    }

    public void setSearchAndRespondToConsumerProjects(Boolean searchAndRespondToConsumerProjects) {
        this.searchAndRespondToConsumerProjects = searchAndRespondToConsumerProjects;
    }

    public Boolean getGenerateProfileResponseDocuments() {
        return generateProfileResponseDocuments;
    }

    public void setGenerateProfileResponseDocuments(Boolean generateProfileResponseDocuments) {
        this.generateProfileResponseDocuments = generateProfileResponseDocuments;
    }

    public Boolean getMainProjectsDashboard() {
        return mainProjectsDashboard;
    }

    public void setMainProjectsDashboard(Boolean mainProjectsDashboard) {
        this.mainProjectsDashboard = mainProjectsDashboard;
    }

    public Boolean getSecuredMessagesWithCustomers() {
        return securedMessagesWithCustomers;
    }

    public void setSecuredMessagesWithCustomers(Boolean securedMessagesWithCustomers) {
        this.securedMessagesWithCustomers = securedMessagesWithCustomers;
    }

    public Boolean getShareProfileWithConsumers() {
        return shareProfileWithConsumers;
    }

    public void setShareProfileWithConsumers(Boolean shareProfileWithConsumers) {
        this.shareProfileWithConsumers = shareProfileWithConsumers;
    }

    public Boolean getRespondAgainstConsumerInvites() {
        return respondAgainstConsumerInvites;
    }

    public void setRespondAgainstConsumerInvites(Boolean respondAgainstConsumerInvites) {
        this.respondAgainstConsumerInvites = respondAgainstConsumerInvites;
    }

    public Boolean getCertificationAttest() {
        return certificationAttest;
    }

    public void setCertificationAttest(Boolean certificationAttest) {
        this.certificationAttest = certificationAttest;
    }

    public Boolean getValidation() {
        return validation;
    }

    public void setValidation(Boolean validation) {
        this.validation = validation;
    }

    public Boolean getPortalSupport() {
        return portalSupport;
    }

    public void setPortalSupport(Boolean portalSupport) {
        this.portalSupport = portalSupport;
    }

    public Boolean getAutomatedQuarterlyReport() {
        return automatedQuarterlyReport;
    }

    public void setAutomatedQuarterlyReport(Boolean automatedQuarterlyReport) {
        this.automatedQuarterlyReport = automatedQuarterlyReport;
    }

    public Boolean getQuarterlyAdvisory() {
        return quarterlyAdvisory;
    }

    public void setQuarterlyAdvisory(Boolean quarterlyAdvisory) {
        this.quarterlyAdvisory = quarterlyAdvisory;
    }

    public String getPortalSupportnum() {
        return portalSupportnum;
    }

    public void setPortalSupportnum(String portalSupportnum) {
        this.portalSupportnum = portalSupportnum;
    }

    public String getQuarterlyReview() {
        return quarterlyReview;
    }

    public void setQuarterlyReview(String quarterlyReview) {
        this.quarterlyReview = quarterlyReview;
    }

    public String getProfileValidation() {
        return profileValidation;
    }

    public void setProfileValidation(String profileValidation) {
        this.profileValidation = profileValidation;
    }

    public String getProfileAdvisory() {
        return profileAdvisory;
    }

    public void setProfileAdvisory(String profileAdvisory) {
        this.profileAdvisory = profileAdvisory;
    }

    public Long getCreatedEpochTime() {
        return createdEpochTime;
    }

    public void setCreatedEpochTime(Long createdEpochTime) {
        this.createdEpochTime = createdEpochTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedEpochTime() {
        return updatedEpochTime;
    }

    public void setUpdatedEpochTime(Long updatedEpochTime) {
        this.updatedEpochTime = updatedEpochTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getTrailStatus() {
        return trailStatus;
    }

    public void setTrailStatus(Boolean trailStatus) {
        this.trailStatus = trailStatus;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
