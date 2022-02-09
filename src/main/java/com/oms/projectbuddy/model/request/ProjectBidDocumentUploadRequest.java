package com.oms.projectbuddy.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectBidDocumentUploadRequest {


    private String documentType;

    private String document;

    private String fileName;

    private Long documentId;
    
    private String documentStatus;
    
    private Long score;
}
