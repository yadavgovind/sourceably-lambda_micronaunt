package com.oms.projectbuddy.model.request;

import com.oms.projectbuddy.model.ProjectInviteEmails;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectInviteListRequest {
    private String projectCode;
    private String companyId;
    //private String[] emailId;
    private List<ProjectInviteEmails> emailId;
    //private List<String> emailId;

}
