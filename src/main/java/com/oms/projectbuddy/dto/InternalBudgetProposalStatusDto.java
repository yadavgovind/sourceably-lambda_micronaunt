package com.oms.projectbuddy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InternalBudgetProposalStatusDto {

	private Double internalBudget;
    private String projectName;
    private String proposalStatus;
    private String highProposedCost;
    private String lowProposedCost;
    
}
