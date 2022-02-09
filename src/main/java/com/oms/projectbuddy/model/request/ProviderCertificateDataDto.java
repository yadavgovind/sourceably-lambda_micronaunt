package com.oms.projectbuddy.model.request;

import com.oms.projectbuddy.utils.CertificationType;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class ProviderCertificateDataDto {
    private String comment;
    private String providerOptions;
    private Long certificateId;
    private String userId;
    private CertificationType certificationType;
    List<CertificationAttachmentDetails> attachments;
}
