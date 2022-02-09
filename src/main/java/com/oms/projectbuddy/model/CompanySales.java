package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pb_user_company_sales" ,uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id"}),})
public class CompanySales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "additional_information")
    private String additionalInformation;
    @Column(name = "last_updated_by")
    private String lastUpdatedBy;
    @Column(name = "generalised_nda_document")
    private String generalisedNdaDocument;
    @Column(name = "nda_file_name")
    private String ndaFileName;
    @Column(name = "last_updated_epoch_time")
    private Long lastUpdatedEpochTime;

    @Transient
    List<AdditionalAttachment> additionalAttachments;

}
