package com.oms.projectbuddy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GlobalBussinessGraphData {
    private String certificateName;
    private Double aquirecreations;
    private Double globalMarket;
    
    @Data
	@Builder
	public static class ChildBussinessGraphData{
		private String certificateName;
	}
}
