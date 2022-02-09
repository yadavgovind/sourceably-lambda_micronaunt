package com.oms.projectbuddy.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyHighlightsRequest {
   // private Long id;
    private String userId;
    private String awards;
    private String specialProgramProjects;
    private String testimonials;
    private String newsAndMedia;
    private String lastUpdatedBy;
    private Long lastUpdatedEpochTime;
}
