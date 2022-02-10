package com.oms.projectbuddy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.oms.projectbuddy.dto.ProjectMilestoneListResponse;
import com.oms.projectbuddy.utils.NdaStatus;
import com.oms.projectbuddy.utils.ProjectStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectEmail() {
        return projectEmail;
    }

    public void setProjectEmail(String projectEmail) {
        this.projectEmail = projectEmail;
    }

    public String getProjectContactNum() {
        return projectContactNum;
    }

    public void setProjectContactNum(String projectContactNum) {
        this.projectContactNum = projectContactNum;
    }

    public String getProjectCategoryId() {
        return projectCategoryId;
    }

    public void setProjectCategoryId(String projectCategoryId) {
        this.projectCategoryId = projectCategoryId;
    }

    public String getProjectSubCategoryId() {
        return projectSubCategoryId;
    }

    public void setProjectSubCategoryId(String projectSubCategoryId) {
        this.projectSubCategoryId = projectSubCategoryId;
    }

    public String getProjectSubCategoryLevel2Id() {
        return projectSubCategoryLevel2Id;
    }

    public void setProjectSubCategoryLevel2Id(String projectSubCategoryLevel2Id) {
        this.projectSubCategoryLevel2Id = projectSubCategoryLevel2Id;
    }

    public String getProjectSubCategoryLevel3Id() {
        return projectSubCategoryLevel3Id;
    }

    public void setProjectSubCategoryLevel3Id(String projectSubCategoryLevel3Id) {
        this.projectSubCategoryLevel3Id = projectSubCategoryLevel3Id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getProjectBackground() {
        return projectBackground;
    }

    public void setProjectBackground(String projectBackground) {
        this.projectBackground = projectBackground;
    }

    public String getProjectScopeGoal() {
        return projectScopeGoal;
    }

    public void setProjectScopeGoal(String projectScopeGoal) {
        this.projectScopeGoal = projectScopeGoal;
    }

    public String getProjectScopeGoal2() {
        return projectScopeGoal2;
    }

    public void setProjectScopeGoal2(String projectScopeGoal2) {
        this.projectScopeGoal2 = projectScopeGoal2;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getInternalBudget() {
        return internalBudget;
    }

    public void setInternalBudget(Long internalBudget) {
        this.internalBudget = internalBudget;
    }

    public String getBudgetRange() {
        return budgetRange;
    }

    public void setBudgetRange(String budgetRange) {
        this.budgetRange = budgetRange;
    }

    public String getProjectTeamSize() {
        return projectTeamSize;
    }

    public void setProjectTeamSize(String projectTeamSize) {
        this.projectTeamSize = projectTeamSize;
    }

    public String getProjectTeamSizeVisibility() {
        return projectTeamSizeVisibility;
    }

    public void setProjectTeamSizeVisibility(String projectTeamSizeVisibility) {
        this.projectTeamSizeVisibility = projectTeamSizeVisibility;
    }

    public String getProjectBudgetVisibility() {
        return projectBudgetVisibility;
    }

    public void setProjectBudgetVisibility(String projectBudgetVisibility) {
        this.projectBudgetVisibility = projectBudgetVisibility;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getNDA() {
        return isNDA;
    }

    public void setNDA(Boolean NDA) {
        isNDA = NDA;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDate getCreatedEpochTime() {
        return createdEpochTime;
    }

    public void setCreatedEpochTime(LocalDate createdEpochTime) {
        this.createdEpochTime = createdEpochTime;
    }

    public LocalDate getUpdatedEpochTime() {
        return updatedEpochTime;
    }

    public void setUpdatedEpochTime(LocalDate updatedEpochTime) {
        this.updatedEpochTime = updatedEpochTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public boolean isMarketplace() {
        return marketplace;
    }

    public void setMarketplace(boolean marketplace) {
        this.marketplace = marketplace;
    }

    public Long getTotalProjects() {
        return totalProjects;
    }

    public void setTotalProjects(Long totalProjects) {
        this.totalProjects = totalProjects;
    }

    public ProjectMilestoneListResponse getMileStoneList() {
        return mileStoneList;
    }

    public void setMileStoneList(ProjectMilestoneListResponse mileStoneList) {
        this.mileStoneList = mileStoneList;
    }

    public List<ProjectDocumentUpload> getDocuments() {
        return documents;
    }

    public void setDocuments(List<ProjectDocumentUpload> documents) {
        this.documents = documents;
    }

    public ConsumerSales getConsumerNDA() {
        return consumerNDA;
    }

    public void setConsumerNDA(ConsumerSales consumerNDA) {
        this.consumerNDA = consumerNDA;
    }

    public NdaStatus getNdaStatus() {
        return ndaStatus;
    }

    public void setNdaStatus(NdaStatus ndaStatus) {
        this.ndaStatus = ndaStatus;
    }

    public String getNdaURL() {
        return ndaURL;
    }

    public void setNdaURL(String ndaURL) {
        this.ndaURL = ndaURL;
    }

    public String getNdaFileName() {
        return ndaFileName;
    }

    public void setNdaFileName(String ndaFileName) {
        this.ndaFileName = ndaFileName;
    }

    public Long getBidCount() {
        return bidCount;
    }

    public void setBidCount(Long bidCount) {
        this.bidCount = bidCount;
    }

    public ProjectBidPost getProjectBidPost() {
        return projectBidPost;
    }

    public void setProjectBidPost(ProjectBidPost projectBidPost) {
        this.projectBidPost = projectBidPost;
    }

    public List<String> getUserUplodedDocumentUrl() {
        return userUplodedDocumentUrl;
    }

    public void setUserUplodedDocumentUrl(List<String> userUplodedDocumentUrl) {
        this.userUplodedDocumentUrl = userUplodedDocumentUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDate getNdaApprovedDate() {
        return ndaApprovedDate;
    }

    public void setNdaApprovedDate(LocalDate ndaApprovedDate) {
        this.ndaApprovedDate = ndaApprovedDate;
    }

    public String getSubCategoryLevel2Name() {
        return subCategoryLevel2Name;
    }

    public void setSubCategoryLevel2Name(String subCategoryLevel2Name) {
        this.subCategoryLevel2Name = subCategoryLevel2Name;
    }

    public String getSubCategoryLevel3Name() {
        return subCategoryLevel3Name;
    }

    public void setSubCategoryLevel3Name(String subCategoryLevel3Name) {
        this.subCategoryLevel3Name = subCategoryLevel3Name;
    }
}
