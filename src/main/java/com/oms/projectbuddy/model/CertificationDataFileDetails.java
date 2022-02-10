package com.oms.projectbuddy.model;

import com.oms.projectbuddy.utils.CertificationType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


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

    public Long getCertificationDocumentId() {
        return certificationDocumentId;
    }

    public void setCertificationDocumentId(Long certificationDocumentId) {
        this.certificationDocumentId = certificationDocumentId;
    }

    public Long getProviderCertificationDataId() {
        return providerCertificationDataId;
    }

    public void setProviderCertificationDataId(Long providerCertificationDataId) {
        this.providerCertificationDataId = providerCertificationDataId;
    }

    public Long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Long certificateId) {
        this.certificateId = certificateId;
    }

    public CertificationType getCertificationType() {
        return certificationType;
    }

    public void setCertificationType(CertificationType certificationType) {
        this.certificationType = certificationType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAccessBy() {
        return accessBy;
    }

    public void setAccessBy(String accessBy) {
        this.accessBy = accessBy;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
