package com.oms.projectbuddy.model.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProjectDocumentUploadRequest {

    private String documentType;
    private String document;
    private String fileName;
}
