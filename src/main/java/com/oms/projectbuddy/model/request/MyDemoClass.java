package com.oms.projectbuddy.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MyDemoClass {
    private List<ProjectDocumentUploadRequest> uploadRequest;
}
