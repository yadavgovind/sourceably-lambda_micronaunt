package com.oms.projectbuddy.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectBidPostRequest {

    private String providerId;
    private String systemGeneratedProjectId;
    private String coverLetter;
    private Long projectCost;
    private List<ProjectBidDocumentUploadRequest> bidDocument;


}
