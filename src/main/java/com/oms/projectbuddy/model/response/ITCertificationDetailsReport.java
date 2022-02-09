package com.oms.projectbuddy.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ITCertificationDetailsReport {
    private String certificationName;
    private String certificationDescription;
    private Double score;
    private String ctrlStatement;
    private String partiallyCompleted;
    private String fullyCompleted;
    private String noData;
    private Integer countOfL2Certificate;
    private Integer countOfL3Certificate;
    private Long attemptCertL2;
    private Long attemptCertL3;
}
