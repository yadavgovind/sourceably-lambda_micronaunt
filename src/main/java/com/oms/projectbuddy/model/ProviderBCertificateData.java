package com.oms.projectbuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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
}
