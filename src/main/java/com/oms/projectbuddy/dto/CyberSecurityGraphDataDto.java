package com.oms.projectbuddy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface CyberSecurityGraphDataDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class Response {
        private GraphData globalGraph;
        private GraphData specificGrapgh;
        private GraphData supplierGraph;
        private String certificationName;
        private Long certificationId;
        private String certificate;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class GraphData {
        private double low;
        private double high;
    }
}
