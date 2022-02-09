package com.oms.projectbuddy.model;

import com.oms.projectbuddy.utils.CertificationType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pb_provider_certificate_data")
@Getter
@Setter
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

}
