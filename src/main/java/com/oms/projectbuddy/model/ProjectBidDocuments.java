package com.oms.projectbuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "pb_project_bid_documents")
public class ProjectBidDocuments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="bid_id")
    private Long bidId;
    @Column(name = "document_id")
    private Long documentId;
    @Column(name = "project_id")
    private String projectId;
    @Column(name = "document")
    private String document;
    @Column(name = "document_type")
    private String documentType;
    @Column(name = "fileName")
    private String fileName;
    @Column(name = "document_status")
    private String documentStatus;
    @Column(name = "score")
    private Double score;
    @Column(name = "submitted_on")
    private LocalDate submittedOn;
}
