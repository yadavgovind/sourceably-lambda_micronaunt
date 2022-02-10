package com.oms.projectbuddy.controller;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import jakarta.inject.Inject;


import com.oms.projectbuddy.model.request.ProjectBidDocumentHistoryRequest;
import com.oms.projectbuddy.model.request.SoftwareEvaluationRequest;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.IProjectBidService;

@ExecuteOn(TaskExecutors.IO)
@Controller("/api")
//@Api(value = "ProjectBidDocument Controller", description = "Project document History Controller ", tags = {"Project document History Controller"})
public class ProjectBidDocumentController {

	@Inject
	private IProjectBidService iProjectBidService;

	@Post(value = "/supplierDocumentsHistory")
	public Object registration(@Body ProjectBidDocumentHistoryRequest request) throws Exception{
		return new EntityResponse(iProjectBidService.saveDocumentHistory(request), 0);
	}

	@Get("/supplierDocumentsHistory")
	public Object getAllMarketData(  String projectId,  String supplierId,
											  String consumerId) throws Exception{
		return
				new EntityResponse(iProjectBidService.getSupplierProjectsHistoryByProjectId(projectId,supplierId,consumerId), 0);
	}


	@Post(value = "/softwareEvaluation")
	public Object saveSoftwareEvaluation(@Body SoftwareEvaluationRequest request) throws Exception{
		return new EntityResponse(iProjectBidService.saveUpdateSoftwareEvaluation(request), 0);
	}

	@Get("/softwareEvaluation")
	public Object getAllMarketData( String supplierId) throws Exception{
		return
				new EntityResponse(iProjectBidService.getSoftwareEvaluationBySupplierId(supplierId), 0);
	}

}