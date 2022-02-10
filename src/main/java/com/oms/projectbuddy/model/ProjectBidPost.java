package com.oms.projectbuddy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.oms.projectbuddy.utils.NdaStatus;
import com.oms.projectbuddy.utils.StatusEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public String getSystemGeneratedProjectId() {
        return systemGeneratedProjectId;
    }

    public void setSystemGeneratedProjectId(String systemGeneratedProjectId) {
        this.systemGeneratedProjectId = systemGeneratedProjectId;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getNdaFile() {
        return ndaFile;
    }

    public void setNdaFile(String ndaFile) {
        this.ndaFile = ndaFile;
    }

    public String getNdaFileName() {
        return ndaFileName;
    }

    public void setNdaFileName(String ndaFileName) {
        this.ndaFileName = ndaFileName;
    }

    public Boolean getNdaStatus() {
        return ndaStatus;
    }

    public void setNdaStatus(Boolean ndaStatus) {
        this.ndaStatus = ndaStatus;
    }

    public StatusEnum getProposalStatus() {
        return proposalStatus;
    }

    public void setProposalStatus(StatusEnum proposalStatus) {
        this.proposalStatus = proposalStatus;
    }

    public LocalDate getNdaUploadDate() {
        return ndaUploadDate;
    }

    public void setNdaUploadDate(LocalDate ndaUploadDate) {
        this.ndaUploadDate = ndaUploadDate;
    }

    public NdaStatus getNdaApprovalStatus() {
        return ndaApprovalStatus;
    }

    public void setNdaApprovalStatus(NdaStatus ndaApprovalStatus) {
        this.ndaApprovalStatus = ndaApprovalStatus;
    }

    public String getNdaApprovedBy() {
        return ndaApprovedBy;
    }

    public void setNdaApprovedBy(String ndaApprovedBy) {
        this.ndaApprovedBy = ndaApprovedBy;
    }

    public LocalDate getNdaApprovalDate() {
        return ndaApprovalDate;
    }

    public void setNdaApprovalDate(LocalDate ndaApprovalDate) {
        this.ndaApprovalDate = ndaApprovalDate;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public Long getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(Long projectCost) {
        this.projectCost = projectCost;
    }

    public LocalDate getProjectBidDate() {
        return projectBidDate;
    }

    public void setProjectBidDate(LocalDate projectBidDate) {
        this.projectBidDate = projectBidDate;
    }

    public String getBidPostedBy() {
        return bidPostedBy;
    }

    public void setBidPostedBy(String bidPostedBy) {
        this.bidPostedBy = bidPostedBy;
    }

    public String getNdaComment() {
        return ndaComment;
    }

    public void setNdaComment(String ndaComment) {
        this.ndaComment = ndaComment;
    }

    public Integer getBidCount() {
        return bidCount;
    }

    public void setBidCount(Integer bidCount) {
        this.bidCount = bidCount;
    }

    public String getProposalStatusValue() {
        return proposalStatusValue;
    }

    public void setProposalStatusValue(String proposalStatusValue) {
        this.proposalStatusValue = proposalStatusValue;
    }

    public List<ProjectBidDocuments> getBidDocuments() {
        return bidDocuments;
    }

    public void setBidDocuments(List<ProjectBidDocuments> bidDocuments) {
        this.bidDocuments = bidDocuments;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Long getIntendedCost() {
        return intendedCost;
    }

    public void setIntendedCost(Long intendedCost) {
        this.intendedCost = intendedCost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<ProjectDocumentUpload> getDocuments() {
        return documents;
    }

    public void setDocuments(List<ProjectDocumentUpload> documents) {
        this.documents = documents;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getSupplierCompanyaddress() {
        return supplierCompanyaddress;
    }

    public void setSupplierCompanyaddress(String supplierCompanyaddress) {
        this.supplierCompanyaddress = supplierCompanyaddress;
    }

    public String getServicetagline() {
        return servicetagline;
    }

    public void setServicetagline(String servicetagline) {
        this.servicetagline = servicetagline;
    }

    public String getCompanydescription() {
        return companydescription;
    }

    public void setCompanydescription(String companydescription) {
        this.companydescription = companydescription;
    }

    public String getCompanyimage() {
        return companyimage;
    }

    public void setCompanyimage(String companyimage) {
        this.companyimage = companyimage;
    }

    public double getRanking() {
        return ranking;
    }

    public void setRanking(double ranking) {
        this.ranking = ranking;
    }
}
