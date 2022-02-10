package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "pb_project_invite_list")
public class ProjectInviteList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="project_code")
    private String projectCode;
    @Column(name="company_id")
    private String companyId;
  /*  @Column(name="email_id")
    private String emailId;*/
    /*@Column(name="is_active")
    private Boolean isActive;*/
    @Transient
    private List<ProjectInviteEmails> emails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public List<ProjectInviteEmails> getEmails() {
        return emails;
    }

    public void setEmails(List<ProjectInviteEmails> emails) {
        this.emails = emails;
    }
}
