package com.oms.projectbuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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
}
