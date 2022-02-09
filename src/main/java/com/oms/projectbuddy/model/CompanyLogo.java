package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pb_company_logo")
public class CompanyLogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@Column(name = "file_type")
    private String fileType;*/
    @Column(name =" registration_type")
    private String registrationType;
    @Column(name = "user_id")
    private String userId;
    @Column(name= "file")
    private String file;
    @Column(name= "is_deleted")
    private boolean isDeleted;



  /*  @Column(name="video")
    private String video;*/
}
