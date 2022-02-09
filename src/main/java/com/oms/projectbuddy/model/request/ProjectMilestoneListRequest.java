package com.oms.projectbuddy.model.request;

import com.oms.projectbuddy.utils.StatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectMilestoneListRequest {
    //private Long projectId;
    private Long milestoneId;
    private String milestoneType;
    private String date;
    //private String userId;

}
