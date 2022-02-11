package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;


@Entity
@Table(name = "pb_provider_skills_matrix")
public class SupplierSkillMatrix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long psmid;

    @Column(name="user_id")
    private String userId;
    @Column(name="skill_matrix_id")
    private String skillMatrixId;
    @Column(name="level_hearichy_name")
    private String levelHearichyName;
    @Column(name="is_active")
    private Boolean isActive;
    @Column(name="created_epoch_time")
    private Long createdEpochTime;
    @Column(name="updated_epoch_time")
    private Long updatedEpochTime;
    @Column(name="created_by")
    private String createdBy;
    @Column(name="updated_by")
    private String updatedBy;


    public Long getPsmid() {
        return psmid;
    }

    public void setPsmid(Long psmid) {
        this.psmid = psmid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSkillMatrixId() {
        return skillMatrixId;
    }

    public void setSkillMatrixId(String skillMatrixId) {
        this.skillMatrixId = skillMatrixId;
    }

    public String getLevelHearichyName() {
        return levelHearichyName;
    }

    public void setLevelHearichyName(String levelHearichyName) {
        this.levelHearichyName = levelHearichyName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Long getCreatedEpochTime() {
        return createdEpochTime;
    }

    public void setCreatedEpochTime(Long createdEpochTime) {
        this.createdEpochTime = createdEpochTime;
    }

    public Long getUpdatedEpochTime() {
        return updatedEpochTime;
    }

    public void setUpdatedEpochTime(Long updatedEpochTime) {
        this.updatedEpochTime = updatedEpochTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
