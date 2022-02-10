package com.oms.projectbuddy.model;

import com.oms.projectbuddy.utils.CertificationType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pb_provider_certificate_data")
public class ProviderCertificateData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long providerCertificateId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "certificate_id")
    private Long certificateId;

    @Column(name = "certificate_type")
    @Enumerated(EnumType.STRING)
    private CertificationType certificationType;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "created_epoch_time")
    private LocalDate createdEpochTime;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_updated_epoch_time")
    private LocalDate lastUpdatedEpochTime;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "comment")
    private String comment;

    @Column(name = "comment_score")
    private int commentScore;

    //values : Y/N/NA
    @Column(name = "provider_options")
    private String providerOptions;

    @Column(name = "provider_option_score")
    private int providerOptionScore;

    @Column(name = "evidence_file_score")
    private int evidenceFileScore;

    public Integer getTotalScore() {
        return commentScore + providerOptionScore + evidenceFileScore;
    }

    @Transient
    private List<?> attachmentDetails;

    @Transient
    private List<?> history;
    @Transient
    private String certificationName;
    @Transient
    private String certificationNameDetails;

    @Transient
    private String userName;

    public Long getProviderCertificateId() {
        return providerCertificateId;
    }

    public void setProviderCertificateId(Long providerCertificateId) {
        this.providerCertificateId = providerCertificateId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDate getCreatedEpochTime() {
        return createdEpochTime;
    }

    public void setCreatedEpochTime(LocalDate createdEpochTime) {
        this.createdEpochTime = createdEpochTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getLastUpdatedEpochTime() {
        return lastUpdatedEpochTime;
    }

    public void setLastUpdatedEpochTime(LocalDate lastUpdatedEpochTime) {
        this.lastUpdatedEpochTime = lastUpdatedEpochTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(int commentScore) {
        this.commentScore = commentScore;
    }

    public String getProviderOptions() {
        return providerOptions;
    }

    public void setProviderOptions(String providerOptions) {
        this.providerOptions = providerOptions;
    }

    public int getProviderOptionScore() {
        return providerOptionScore;
    }

    public void setProviderOptionScore(int providerOptionScore) {
        this.providerOptionScore = providerOptionScore;
    }

    public int getEvidenceFileScore() {
        return evidenceFileScore;
    }

    public void setEvidenceFileScore(int evidenceFileScore) {
        this.evidenceFileScore = evidenceFileScore;
    }

    public List<?> getAttachmentDetails() {
        return attachmentDetails;
    }

    public void setAttachmentDetails(List<?> attachmentDetails) {
        this.attachmentDetails = attachmentDetails;
    }

    public List<?> getHistory() {
        return history;
    }

    public void setHistory(List<?> history) {
        this.history = history;
    }

    public String getCertificationName() {
        return certificationName;
    }

    public void setCertificationName(String certificationName) {
        this.certificationName = certificationName;
    }

    public String getCertificationNameDetails() {
        return certificationNameDetails;
    }

    public void setCertificationNameDetails(String certificationNameDetails) {
        this.certificationNameDetails = certificationNameDetails;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
