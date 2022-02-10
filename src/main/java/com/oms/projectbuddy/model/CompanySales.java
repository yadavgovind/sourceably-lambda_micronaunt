package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "pb_user_company_sales" ,uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id"}),})
public class CompanySales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "additional_information")
    private String additionalInformation;
    @Column(name = "last_updated_by")
    private String lastUpdatedBy;
    @Column(name = "generalised_nda_document")
    private String generalisedNdaDocument;
    @Column(name = "nda_file_name")
    private String ndaFileName;
    @Column(name = "last_updated_epoch_time")
    private Long lastUpdatedEpochTime;

    @Transient
    List<AdditionalAttachment> additionalAttachments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getGeneralisedNdaDocument() {
        return generalisedNdaDocument;
    }

    public void setGeneralisedNdaDocument(String generalisedNdaDocument) {
        this.generalisedNdaDocument = generalisedNdaDocument;
    }

    public String getNdaFileName() {
        return ndaFileName;
    }

    public void setNdaFileName(String ndaFileName) {
        this.ndaFileName = ndaFileName;
    }

    public Long getLastUpdatedEpochTime() {
        return lastUpdatedEpochTime;
    }

    public void setLastUpdatedEpochTime(Long lastUpdatedEpochTime) {
        this.lastUpdatedEpochTime = lastUpdatedEpochTime;
    }

    public List<AdditionalAttachment> getAdditionalAttachments() {
        return additionalAttachments;
    }

    public void setAdditionalAttachments(List<AdditionalAttachment> additionalAttachments) {
        this.additionalAttachments = additionalAttachments;
    }
}
