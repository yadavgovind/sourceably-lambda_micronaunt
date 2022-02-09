package com.oms.projectbuddy.model;

import com.oms.projectbuddy.utils.CertificationType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "certification_file_details")
public class CertificationDataFileDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long certificationDocumentId;

    @Column(name = "provider_certification_data_id")
    private Long providerCertificationDataId;

    @Column(name = "certificate_id")
    private Long certificateId;

    @Column(name = "certificate_type")
    private CertificationType certificationType;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "access_by")
    private String accessBy;

    @Column(name = "service_type")
    private String serviceType;

}
