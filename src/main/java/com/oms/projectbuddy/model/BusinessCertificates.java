package com.oms.projectbuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "pb_business_certificates")
public class BusinessCertificates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long businessCertificateId;

    @Column(name = "certificate_name")
    private String certificateName;

    @Column(name = "status")
    private Boolean Status;

    @Column(name = "is_subcategory")
    private Boolean isSubCategory;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private BusinessCertificates parent;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "country")
    private String country;

    @Transient
    private Object providerDataDetails;

    public Long getBusinessCertificateId() {
        return businessCertificateId;
    }

    public void setBusinessCertificateId(Long businessCertificateId) {
        this.businessCertificateId = businessCertificateId;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    public Boolean getSubCategory() {
        return isSubCategory;
    }

    public void setSubCategory(Boolean subCategory) {
        isSubCategory = subCategory;
    }

    public BusinessCertificates getParent() {
        return parent;
    }

    public void setParent(BusinessCertificates parent) {
        this.parent = parent;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Object getProviderDataDetails() {
        return providerDataDetails;
    }

    public void setProviderDataDetails(Object providerDataDetails) {
        this.providerDataDetails = providerDataDetails;
    }
}
