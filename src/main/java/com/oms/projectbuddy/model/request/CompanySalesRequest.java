package com.oms.projectbuddy.model.request;

import com.oms.projectbuddy.model.AdditionalAttachment;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
public class CompanySalesRequest {
    // private Long id;
    private String userId;
    private String additionalInformation;
    private String[] filesUrls;
    private String generalisedNdaDocumentUrl;
    private String orginalFileName;
    private String lastUpdatedBy;
    private Long lastUpdatedEpochTime;

    List<AdditionalAttachment> additionalAttachments;
}
