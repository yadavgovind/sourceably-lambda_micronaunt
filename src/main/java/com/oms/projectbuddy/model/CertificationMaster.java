package com.oms.projectbuddy.model;

import com.oms.projectbuddy.utils.CertificationType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Table(name = "pb_certification_master")
@Entity
public class CertificationMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certification_id")
    private Long certificationId;

    @Column(name = "certification_name")
    private String certificationName;

//    @ManyToOne
    @Column(name = "parent_id")
//    @JsonIgnore
    private Long parentId;

    @Lob
    @Column(name = "certification_description")
    private String certificationDescription;

    @Column(name = "yes_description")
    private String yesDescription;

    @Column(name = "no_description")
    private String noDescription;

    @Column(name = "level")
    private String level;

    @Column(name = "is_sub_category")
    private Boolean isSubCategory;

    @Column(name = "na_description")
    private String naDescription;

    @Lob
    @Column(name = "certificate_evidence_description")
    private String certificateEvidenceDescription;

    @Lob
    @Column(name = "certification_guidence_description")
    private String certificationGuidenceDescription;

    @Column(name = "certification_type")
    @Enumerated(EnumType.STRING)
    private CertificationType certificationType;

    @Column(name = "iso_mapping")
    private String isoMapping;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "created_epoch_time")
    private Long createdEpochTime;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_updated_epoch_time")
    private Long lastUpdatedEpochTime;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "control_statement")
    private String controlStatement;

    @Column(name = "fully_complited")
    private String fullyComplited;

    @Column(name = "partially_complited")
    private String partiallyComplited;

    @Column(name = "no_data_avaliable")
    private String noDataAvaliable;

    @Transient
    private Integer scoreByUser;

    @Transient
    private Long updatedEpochTimeByUser;

    @Transient
    private String updatedBy;
    @Transient
    private String userName;

    public Long getCertificationId() {
        return certificationId;
    }

    public void setCertificationId(Long certificationId) {
        this.certificationId = certificationId;
    }

    public String getCertificationName() {
        return certificationName;
    }

    public void setCertificationName(String certificationName) {
        this.certificationName = certificationName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCertificationDescription() {
        return certificationDescription;
    }

    public void setCertificationDescription(String certificationDescription) {
        this.certificationDescription = certificationDescription;
    }

    public String getYesDescription() {
        return yesDescription;
    }

    public void setYesDescription(String yesDescription) {
        this.yesDescription = yesDescription;
    }

    public String getNoDescription() {
        return noDescription;
    }

    public void setNoDescription(String noDescription) {
        this.noDescription = noDescription;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Boolean getSubCategory() {
        return isSubCategory;
    }

    public void setSubCategory(Boolean subCategory) {
        isSubCategory = subCategory;
    }

    public String getNaDescription() {
        return naDescription;
    }

    public void setNaDescription(String naDescription) {
        this.naDescription = naDescription;
    }

    public String getCertificateEvidenceDescription() {
        return certificateEvidenceDescription;
    }

    public void setCertificateEvidenceDescription(String certificateEvidenceDescription) {
        this.certificateEvidenceDescription = certificateEvidenceDescription;
    }

    public String getCertificationGuidenceDescription() {
        return certificationGuidenceDescription;
    }

    public void setCertificationGuidenceDescription(String certificationGuidenceDescription) {
        this.certificationGuidenceDescription = certificationGuidenceDescription;
    }

    public CertificationType getCertificationType() {
        return certificationType;
    }

    public void setCertificationType(CertificationType certificationType) {
        this.certificationType = certificationType;
    }

    public String getIsoMapping() {
        return isoMapping;
    }

    public void setIsoMapping(String isoMapping) {
        this.isoMapping = isoMapping;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getCreatedEpochTime() {
        return createdEpochTime;
    }

    public void setCreatedEpochTime(Long createdEpochTime) {
        this.createdEpochTime = createdEpochTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getLastUpdatedEpochTime() {
        return lastUpdatedEpochTime;
    }

    public void setLastUpdatedEpochTime(Long lastUpdatedEpochTime) {
        this.lastUpdatedEpochTime = lastUpdatedEpochTime;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getControlStatement() {
        return controlStatement;
    }

    public void setControlStatement(String controlStatement) {
        this.controlStatement = controlStatement;
    }

    public String getFullyComplited() {
        return fullyComplited;
    }

    public void setFullyComplited(String fullyComplited) {
        this.fullyComplited = fullyComplited;
    }

    public String getPartiallyComplited() {
        return partiallyComplited;
    }

    public void setPartiallyComplited(String partiallyComplited) {
        this.partiallyComplited = partiallyComplited;
    }

    public String getNoDataAvaliable() {
        return noDataAvaliable;
    }

    public void setNoDataAvaliable(String noDataAvaliable) {
        this.noDataAvaliable = noDataAvaliable;
    }

    public Integer getScoreByUser() {
        return scoreByUser;
    }

    public void setScoreByUser(Integer scoreByUser) {
        this.scoreByUser = scoreByUser;
    }

    public Long getUpdatedEpochTimeByUser() {
        return updatedEpochTimeByUser;
    }

    public void setUpdatedEpochTimeByUser(Long updatedEpochTimeByUser) {
        this.updatedEpochTimeByUser = updatedEpochTimeByUser;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
