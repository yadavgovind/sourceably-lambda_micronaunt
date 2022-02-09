package com.oms.projectbuddy.model.request;

import java.util.List;

import com.oms.projectbuddy.model.SoftwareEvaluation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoftwareEvaluationRequest {
	
	private List<SoftwareEvaluation> softEvaluations;
	
}
