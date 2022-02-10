package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "pb_consumer_company_details",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id"}),})
public class ConsumerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "company_description")
    private String companyDescription;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "service_description")
    private String serviceDescription;
    @Column(name = "history")
    private String history;
    @Column(name = "public_relation")
    private String publicRelation;
    @Column(name = "advertising")
    private String advertising;
    @Column(name = "industry_info")
    private String industryInfo;
    @Column(name = "gross_annual_sale")
    private String grossAnnualSale;
    @Column(name = "geographical_service_area")
    private String geographicalServiceArea;
    @Column(name = "legal_structure")
    private String legalStructure;
    @Column(name = "safety_policy")
    private String safetyPolicy;
    @Column(name = "insecured")
    private Boolean insecured;
    @Column(name = "bonded")
    private Boolean bonded;
    @Column(name = "liscensed")
    private Boolean liscensed;
    @Column(name = "liscense_no")
    private String liscenceNo;
    @Column(name = "additional_info")
    private String additionalInfo;
    @Column(name = "agree_to_share")
    private Boolean agreeToShare;
    @Column(name = "last_updated_by")
    private String lastUpdatedBy;
    @Column(name = "last_updated_epoch_time")
    private Long lastUpdatedEpochTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getPublicRelation() {
        return publicRelation;
    }

    public void setPublicRelation(String publicRelation) {
        this.publicRelation = publicRelation;
    }

    public String getAdvertising() {
        return advertising;
    }

    public void setAdvertising(String advertising) {
        this.advertising = advertising;
    }

    public String getIndustryInfo() {
        return industryInfo;
    }

    public void setIndustryInfo(String industryInfo) {
        this.industryInfo = industryInfo;
    }

    public String getGrossAnnualSale() {
        return grossAnnualSale;
    }

    public void setGrossAnnualSale(String grossAnnualSale) {
        this.grossAnnualSale = grossAnnualSale;
    }

    public String getGeographicalServiceArea() {
        return geographicalServiceArea;
    }

    public void setGeographicalServiceArea(String geographicalServiceArea) {
        this.geographicalServiceArea = geographicalServiceArea;
    }

    public String getLegalStructure() {
        return legalStructure;
    }

    public void setLegalStructure(String legalStructure) {
        this.legalStructure = legalStructure;
    }

    public String getSafetyPolicy() {
        return safetyPolicy;
    }

    public void setSafetyPolicy(String safetyPolicy) {
        this.safetyPolicy = safetyPolicy;
    }

    public Boolean getInsecured() {
        return insecured;
    }

    public void setInsecured(Boolean insecured) {
        this.insecured = insecured;
    }

    public Boolean getBonded() {
        return bonded;
    }

    public void setBonded(Boolean bonded) {
        this.bonded = bonded;
    }

    public Boolean getLiscensed() {
        return liscensed;
    }

    public void setLiscensed(Boolean liscensed) {
        this.liscensed = liscensed;
    }

    public String getLiscenceNo() {
        return liscenceNo;
    }

    public void setLiscenceNo(String liscenceNo) {
        this.liscenceNo = liscenceNo;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Boolean getAgreeToShare() {
        return agreeToShare;
    }

    public void setAgreeToShare(Boolean agreeToShare) {
        this.agreeToShare = agreeToShare;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Long getLastUpdatedEpochTime() {
        return lastUpdatedEpochTime;
    }

    public void setLastUpdatedEpochTime(Long lastUpdatedEpochTime) {
        this.lastUpdatedEpochTime = lastUpdatedEpochTime;
    }
}
