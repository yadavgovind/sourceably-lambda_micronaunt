package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pb_user_company_financials" ,uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id"}),})
public class CompanyFinancials {
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
}
