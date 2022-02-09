package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pb_user_company_details",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id"}),})
public class CompanyDetails {
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

    @Column(name = "insured_amount")
    private String insuredAmount;
}
