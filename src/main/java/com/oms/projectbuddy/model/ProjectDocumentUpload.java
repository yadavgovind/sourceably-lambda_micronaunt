package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Getter
@Setter
@Entity
@Table(name = "pb_project_document_upload")
public class ProjectDocumentUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_id")
    private Long documentId;
    @Column(name="document_type")
    private String documentType;
    @Column(name="document")
    private String document;
    @Column(name="document_name")
    private String documentName;
    @Column(name="project_code")
    private String projectCode;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "requested_date")
    private LocalDate requestedDate;

    @Transient
    private String userUplodedDocumentUrl;
    @Transient
    private String userUploadedDocumentName;


}
