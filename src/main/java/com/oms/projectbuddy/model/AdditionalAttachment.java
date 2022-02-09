package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "additional_attachment")
public class AdditionalAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sales_id")
    private Long salesId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "files")
    private String files;
    @Column(name="file_name")
    private String fileName;

}
