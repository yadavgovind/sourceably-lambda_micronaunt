package com.oms.projectbuddy.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CyberScoreDto {
    private String certificateName;
    private Double certificatePercentage;
    private Long certificateId;
    private Integer totalCert;
    private Long totalAttemptCert;
    private String certificationTitle;
    private Double score;
    private Double averageScore;
    private String supplierId;
}
