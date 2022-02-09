package com.oms.projectbuddy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.oms.projectbuddy.dto.ProjectMilestoneListResponse;
import com.oms.projectbuddy.utils.NdaStatus;
import com.oms.projectbuddy.utils.ProjectStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
//@Audited
//@AuditTable()
//@Where(clause = "is_deleted=false")
@Table(name = "sbly_consumer_project_creation")
public class ProjectCreation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "project_id")
    private Long projectId;
    @Column(name = "project_code")
    private String projectCode;
    @Column(name = "project_number")
    private String projectNumber;
    @Column(name = "company_id")
    private String companyId;
    @Column(name = "project_owner")
    private String projectOwner;
    @Column(name = "project_title")
    private String projectTitle;
    @Column(name = "project_email")
    private String projectEmail;
    @Column(name = "project_contact_num")
    private String projectContactNum;
    @Column(name = "project_category_id")
    private String projectCategoryId;
    @Column(name = "project_sub_category_id")
    private String projectSubCategoryId;

    @Column(name = "project_sub_category_Level2_id")
    private String projectSubCategoryLevel2Id;

    @Column(name = "project_sub_category_Level3_id")
    private String projectSubCategoryLevel3Id;

    @Column(name = "short_description")
    private String shortDescription;
    @Column(name = "project_background")
    private String projectBackground;
    @Column(name = "project_scope_goal")
    private String projectScopeGoal;
    @Column(name = "project_scope_goal2")
    private String projectScopeGoal2;
    @Column(name = "level_name")
    private String levelName;
    @Column(name = "currency")
    private String currency;
    @Column(name = "internal_budget")
    private Long internalBudget;
    @Column(name = "budget_range")
    private String budgetRange;
    @Column(name = "project_team_size")
    private String projectTeamSize;
    @Column(name = "project_team_size_visibility")
    private String projectTeamSizeVisibility;
    @Column(name = "project_budget_visibility")
    private String projectBudgetVisibility;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "start_date")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "end_date")
    private LocalDate endDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "is_nda")
    private Boolean isNDA;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "is_active")
    private Boolean isActive;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "created_epoch_time")
    private LocalDate createdEpochTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "updated_epoch_time")
    private LocalDate updatedEpochTime;

    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "project_status")
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;
    @Column(name = "is_marketplace")
    private  boolean marketplace;

    @Transient
    private Long totalProjects;
    @Transient
    private ProjectMilestoneListResponse mileStoneList;
    @Transient
    private List<ProjectDocumentUpload> documents;
    @Transient
    private ConsumerSales consumerNDA;
    @Transient
    @Enumerated(EnumType.STRING)
    private NdaStatus ndaStatus;
    @Transient
    private String ndaURL;
    @Transient
    private String ndaFileName;
    @Transient
    private Long bidCount;
    @Transient
    private ProjectBidPost projectBidPost;
    @Transient
    private List<String> userUplodedDocumentUrl;
    @Transient
    private String categoryName;
    @Transient
    private String subCategoryName;
    @Transient
    private String location;
    /*@Transient
    private List<String>*/

    @Transient
    private String companyName;

    @Transient
    private Integer status;

    @Transient
    private LocalDate ndaApprovedDate;

    @Transient
    private String subCategoryLevel2Name;
    @Transient
    private String subCategoryLevel3Name;



}
