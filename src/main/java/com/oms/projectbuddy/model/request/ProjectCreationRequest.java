package com.oms.projectbuddy.model.request;

import java.time.LocalDate;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCreationRequest {
    //private Long id;
    private String systemGeneratedProjectId;
    private String projectNumber;
    private String companyId;
    private String projectOwner;
    private String projectEmail;
    private String projectTitle;
    private String projectContactNum;
    private String catgoryId;
    private String catgorySubId;
    private String subCatLevel2Id;
    private String subCatLevel3Id;
    private String levelName;
    private String currency;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate expiryDate;
    private Boolean isNDA;
    private String shortDescription;
    private String projectBackground;
    private String projectScopeGoal;
    private String projectScopeGoal2;
    private Long internalBudget;
    private String budgetRange;
    private String projectBudgetVisibility;
    private String projectTeamSize;
    private String projectTeamSizeVisibility;
    private String projectType;
    private String milestoneType;
  
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate milestoneDate;
    private String documentlist;
    private List<ProjectDocumentUploadRequest> documents;
    private Boolean isActive;
    
    private String projectStatus;
    private boolean isMarketPlace;


}
