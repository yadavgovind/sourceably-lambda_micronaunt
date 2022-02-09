package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pb_project_invite_emails")
public class ProjectInviteEmails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email")
    private String email;
    @Column(name="project_code")
    private String projectCode;
    @Column(name="is_active")
    private Boolean isActive;
}
