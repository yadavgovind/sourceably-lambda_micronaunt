package com.oms.projectbuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "pb_provider_bcertificate_data")
public class ProviderBCertificateData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bCertificateDataId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "b_certificate_id")
    private Long certificateId;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "start_date")
    private Long startDate;

    @Column(name = "end_date")
    private Long endDate;

    @Column(name = "self_attested")
    private Boolean selfAttested;

    @Column(name = "admin_verified")
    private Boolean adminVerified;

    @Column(name = "admin_verified_date")
    private Long adminVerifiedDate;

    @Column(name = "admin_verified_userid")
    private String adminVerifiedUserId;

    @Column(name = "admin_certified")
    private Boolean adminCertified;

    @Column(name = "admin_certified_date")
    private Long adminCertifiedDate;

    @Column(name = "admin_certified_user_id")
    private String adminCertifiedUserId;

    @Column(name = "user_comment")
    private String userComment;

    @Column(name = "admin_comment")
    private String adminComment;

    @Column(name = "admin_comment_date")
    private Long adminCommentDate;

    @Column(name = "admin_comment_by")
    private String adminCommentBy;

    @OneToOne(mappedBy = "providerBCertificateData",cascade=CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private BCertificatePercentage bCertificatePercentage;

    public Long getbCertificateDataId() {
        return bCertificateDataId;
    }

    public void setbCertificateDataId(Long bCertificateDataId) {
        this.bCertificateDataId = bCertificateDataId;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Boolean getSelfAttested() {
        return selfAttested;
    }

    public void setSelfAttested(Boolean selfAttested) {
        this.selfAttested = selfAttested;
    }

    public Boolean getAdminVerified() {
        return adminVerified;
    }

    public void setAdminVerified(Boolean adminVerified) {
        this.adminVerified = adminVerified;
    }

    public Long getAdminVerifiedDate() {
        return adminVerifiedDate;
    }

    public void setAdminVerifiedDate(Long adminVerifiedDate) {
        this.adminVerifiedDate = adminVerifiedDate;
    }

    public String getAdminVerifiedUserId() {
        return adminVerifiedUserId;
    }

    public void setAdminVerifiedUserId(String adminVerifiedUserId) {
        this.adminVerifiedUserId = adminVerifiedUserId;
    }

    public Boolean getAdminCertified() {
        return adminCertified;
    }

    public void setAdminCertified(Boolean adminCertified) {
        this.adminCertified = adminCertified;
    }

    public Long getAdminCertifiedDate() {
        return adminCertifiedDate;
    }

    public void setAdminCertifiedDate(Long adminCertifiedDate) {
        this.adminCertifiedDate = adminCertifiedDate;
    }

    public String getAdminCertifiedUserId() {
        return adminCertifiedUserId;
    }

    public void setAdminCertifiedUserId(String adminCertifiedUserId) {
        this.adminCertifiedUserId = adminCertifiedUserId;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }

    public Long getAdminCommentDate() {
        return adminCommentDate;
    }

    public void setAdminCommentDate(Long adminCommentDate) {
        this.adminCommentDate = adminCommentDate;
    }

    public String getAdminCommentBy() {
        return adminCommentBy;
    }

    public void setAdminCommentBy(String adminCommentBy) {
        this.adminCommentBy = adminCommentBy;
    }

    public BCertificatePercentage getbCertificatePercentage() {
        return bCertificatePercentage;
    }

    public void setbCertificatePercentage(BCertificatePercentage bCertificatePercentage) {
        this.bCertificatePercentage = bCertificatePercentage;
    }
}
