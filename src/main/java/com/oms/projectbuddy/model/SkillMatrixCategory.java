package com.oms.projectbuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "pb_master_skills_matrix")
public class SkillMatrixCategory {

    @Id
    @GeneratedValue(
            generator = "assigned-sequence",
            strategy = GenerationType.SEQUENCE)
    @GenericGenerator(
            name = "assigned-sequence",
            strategy = "com.oms.projectbuddy.utils.StringSequenceIdentifier",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "sequence_prefix", value = "SKL")
            }
    )
    private String skillMatrixId;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private SkillMatrixCategory parent;

    @Column(name = "skill_matrix_name")
    private String skillMatrixName;

    @Column(name = "skill_matrix_description")
    private String skillMatricDescription;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "created_epoch_time")
    private Long createdEpochTime;

    @Column(name = "updated_epoch_time")
    private Long updatedEpochTime;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "is_subcategory")
    private Boolean isSubCategory;

    @Column(name = "level")
    private String level;

    //Level Details From Here

    @Column(name = "level_hearichy_name")
    private String level_hearichy_name;

    @Column(name = "level_1")
    private String level1;

    @Column(name = "level_2")
    private String level2;

    @Column(name = "level_3")
    private String level3;

    @Column(name = "level_4")
    private String level4;

    @Column(name = "level_5")
    private String level5;

    @Column(name = "level_6")
    private String level6;

    @Column(name = "level_7")
    private String level7;

    //parent Name
   /* public String getParentName(){
        if(parent!=null){
            return parent.getParentName();
        }else{
            return "NA";
        }
    }*/

    public String getSkillMatrixId() {
        return skillMatrixId;
    }

    public void setSkillMatrixId(String skillMatrixId) {
        this.skillMatrixId = skillMatrixId;
    }

    public SkillMatrixCategory getParent() {
        return parent;
    }

    public void setParent(SkillMatrixCategory parent) {
        this.parent = parent;
    }

    public String getSkillMatrixName() {
        return skillMatrixName;
    }

    public void setSkillMatrixName(String skillMatrixName) {
        this.skillMatrixName = skillMatrixName;
    }

    public String getSkillMatricDescription() {
        return skillMatricDescription;
    }

    public void setSkillMatricDescription(String skillMatricDescription) {
        this.skillMatricDescription = skillMatricDescription;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
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

    public Boolean getIsSubCategory() {
        return isSubCategory;
    }

    public void setIsSubCategory(Boolean subCategory) {
        isSubCategory = subCategory;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel_hearichy_name() {
        return level_hearichy_name;
    }

    public void setLevel_hearichy_name(String level_hearichy_name) {
        this.level_hearichy_name = level_hearichy_name;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    public String getLevel4() {
        return level4;
    }

    public void setLevel4(String level4) {
        this.level4 = level4;
    }

    public String getLevel5() {
        return level5;
    }

    public void setLevel5(String level5) {
        this.level5 = level5;
    }

    public String getLevel6() {
        return level6;
    }

    public void setLevel6(String level6) {
        this.level6 = level6;
    }

    public String getLevel7() {
        return level7;
    }

    public void setLevel7(String level7) {
        this.level7 = level7;
    }
}
