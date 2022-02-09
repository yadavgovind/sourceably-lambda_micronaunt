package com.oms.projectbuddy.model;

import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sbly_supplier_document_history")
public class ProjectBidDocumentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "supplier_id")
    private String supplierId;

    @Column(name = "consumer_id")
    private String consumerId;

    @Column(name="project_id")
    private String projectId;

    @Column(name="comment")
    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name="created_date")
    private LocalDate createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name="updated_date")
    private LocalDate updatedDate;
    @Column(name="attach_file_location")
    private String attachFile;

    @Transient
    private String supplierName;

    @Transient
    private String customerName;

}
