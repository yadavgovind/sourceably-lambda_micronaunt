package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "pb_consumer_company_financials" ,uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id"}),})
public class ConsumerFinancials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "last_year_revenue")
    private String lastYearRevenue;
    @Column(name = "financial_targets")
    private String financialTargets;
    //@Column(name = "picture")
    //private String picture;
    @Column(name = "number_of_employees")
    private String numberOfEmployees;
    @Column(name = "partners")
    private String partners;
    @Column(name = "last_updated_by")
    private String lastUpdatedBy;
    @Column(name = "last_updated_epoch_time")
    private Long lastUpdatedEpochTime;

    @Transient
    List<FinancialImageUpload> financialImages;

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

    public String getLastYearRevenue() {
        return lastYearRevenue;
    }

    public void setLastYearRevenue(String lastYearRevenue) {
        this.lastYearRevenue = lastYearRevenue;
    }

    public String getFinancialTargets() {
        return financialTargets;
    }

    public void setFinancialTargets(String financialTargets) {
        this.financialTargets = financialTargets;
    }

    public String getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(String numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getPartners() {
        return partners;
    }

    public void setPartners(String partners) {
        this.partners = partners;
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

    public List<FinancialImageUpload> getFinancialImages() {
        return financialImages;
    }

    public void setFinancialImages(List<FinancialImageUpload> financialImages) {
        this.financialImages = financialImages;
    }
}
