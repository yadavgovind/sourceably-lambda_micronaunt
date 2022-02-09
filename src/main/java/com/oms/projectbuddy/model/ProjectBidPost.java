package com.oms.projectbuddy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.oms.projectbuddy.utils.NdaStatus;
import com.oms.projectbuddy.utils.StatusEnum;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "sbly_project_bid_post")
public class ProjectBidPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bid_id")
    private Long bidId;
    @Column(name="system_generated_project_id")
    private String systemGeneratedProjectId;
    @Column(name="consumer_id")
    private String consumerId;
    @Column(name="provider_id")
    private String providerId;
    @Column(name="nda_file")
    private String ndaFile;
    @Column(name="nda_file_name")
    private String ndaFileName;
    @Column(name="nda_status")
    private Boolean ndaStatus;

    @Column(name="proposal_status")
    @Enumerated(EnumType.STRING)
    private StatusEnum proposalStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name="nda_upload_date")
    private LocalDate ndaUploadDate;

    @Column(name="nda_approval_status")
    @Enumerated(EnumType.STRING)
    private NdaStatus ndaApprovalStatus;
    @Column(name = "nda_approved_by")
    private String ndaApprovedBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name="nda_approval_date")
    private LocalDate ndaApprovalDate;

    @Column(name = "cover_letter")
    private String coverLetter;
    @Column(name = "project_cost")
    private Long projectCost;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "project_bid_date")
    private LocalDate projectBidDate;

    @Column(name = "bid_posted_by")
    private String bidPostedBy;
    @Column(name = "nda_comment")
    private String ndaComment;

    @Transient
    private Integer bidCount;
    @Transient
    private String proposalStatusValue;

    @Transient
    private List<ProjectBidDocuments> bidDocuments;
    @Transient
    private String supplierName;
    @Transient
    private String phoneNo;

    @Transient
    private Long intendedCost;

    @Transient
    private String currency;

    @Transient
    private List<ProjectDocumentUpload> documents;
    @Transient
    private String countryCode;

    @Transient
    private String supplierCompanyaddress;
    @Transient
    private String servicetagline;
    @Transient
    private String companydescription;
    @Transient
    private String companyimage;

    @Transient
    private double ranking;

}
