package com.oms.projectbuddy.model.request;

import com.oms.projectbuddy.model.AdditionalAttachment;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class ConsumerSalesRequest {
    // private Long id;
    private String userId;
    private String additionalInformation;
    private String[] files;
    private String orginalFileName;
    private String generalisedNdaDocumentUrl;
    private String lastUpdatedBy;
    private Long lastUpdatedEpochTime;


    List<AdditionalAttachment> additionalAttachments;
}
