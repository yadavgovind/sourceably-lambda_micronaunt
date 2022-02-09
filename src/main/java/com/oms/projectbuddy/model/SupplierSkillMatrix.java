package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Getter
@Setter
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



}
