package com.oms.projectbuddy.model;

import javax.persistence.*;

@Entity
@Table(name = "pb_itcertification_user_history")
public class ITCertificationUserHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "certificate_id")
    private Long certificateId;

    @Column(name = "certificate_data_id")
    private Long certificateDataId;

    @Column(name = "edited_area")
    private String editedArea;

    @Column(name = "edited_By")
    private String editedBy;

    @Column(name = "edit_details")
    private String editDetails;

    @Column(name = "history_created_epoch_time")
    private Long historyCreatedEpochTime;

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
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

    public Long getCertificateDataId() {
        return certificateDataId;
    }

    public void setCertificateDataId(Long certificateDataId) {
        this.certificateDataId = certificateDataId;
    }

    public String getEditedArea() {
        return editedArea;
    }

    public void setEditedArea(String editedArea) {
        this.editedArea = editedArea;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    public String getEditDetails() {
        return editDetails;
    }

    public void setEditDetails(String editDetails) {
        this.editDetails = editDetails;
    }

    public Long getHistoryCreatedEpochTime() {
        return historyCreatedEpochTime;
    }

    public void setHistoryCreatedEpochTime(Long historyCreatedEpochTime) {
        this.historyCreatedEpochTime = historyCreatedEpochTime;
    }
}
