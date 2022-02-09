package com.oms.projectbuddy.model.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProviderNdaDocRequest {

    private String providerId;
    private String projectId;
    private String ndaFile;
    private String ndaFileName;
}
