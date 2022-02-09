package com.oms.projectbuddy.model;

import com.oms.projectbuddy.utils.CertificationType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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
}
