package com.oms.projectbuddy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DashBoardDto {

	//For Project statics
    private String label;
    private Integer projects;
    private String currency;
    private Double revenue;
    private Double countTrend;
    private Double revenueTrend;
    private Long projectCost;
    
}
