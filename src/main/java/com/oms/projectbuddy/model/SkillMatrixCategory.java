package com.oms.projectbuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
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

}
