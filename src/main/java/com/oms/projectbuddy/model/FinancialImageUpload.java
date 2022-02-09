package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pb_financial_image_upload")
public class FinancialImageUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "financial_id")
    private Long financialId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "files")
    private String files;
    @Column(name = "file_name")
    private String fileName;
}
