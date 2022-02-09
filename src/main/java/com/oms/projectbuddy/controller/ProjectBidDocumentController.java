package com.oms.projectbuddy.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oms.projectbuddy.model.request.ProjectBidDocumentHistoryRequest;
import com.oms.projectbuddy.model.request.SoftwareEvaluationRequest;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.IProjectBidService;

@ExecuteOn(TaskExecutors.IO)
@Controller("/api")
@Api(value = "ProjectBidDocument Controller", description = "Project document History Controller ", tags = {"Project document History Controller"})
public class ProjectBidDocumentController {

	@Autowired
	private IProjectBidService iProjectBidService;

	@PostMapping(value = "/supplierDocumentsHistory")
	public ResponseEntity<?> registration(@RequestBody ProjectBidDocumentHistoryRequest request) throws Exception{
		return new ResponseEntity<>(new EntityResponse(iProjectBidService.saveDocumentHistory(request), 0),
				HttpStatus.OK);
	}

	@GetMapping("/supplierDocumentsHistory")
	public ResponseEntity<?> getAllMarketData(@RequestParam(name = "projectId") String projectId,@RequestParam(name = "supplierId") String supplierId,
											  @RequestParam(name = "consumerId") String consumerId) throws Exception{
		return new ResponseEntity<>(
				new EntityResponse(iProjectBidService.getSupplierProjectsHistoryByProjectId(projectId,supplierId,consumerId), 0),
				HttpStatus.OK);
	}


	@PostMapping(value = "/softwareEvaluation")
	public ResponseEntity<?> saveSoftwareEvaluation(@RequestBody SoftwareEvaluationRequest request) throws Exception{
		return new ResponseEntity<>(new EntityResponse(iProjectBidService.saveUpdateSoftwareEvaluation(request), 0),
				HttpStatus.OK);
	}

	@GetMapping("/softwareEvaluation")
	public ResponseEntity<?> getAllMarketData(@RequestParam(name = "supplierId") String supplierId) throws Exception{
		return new ResponseEntity<>(
				new EntityResponse(iProjectBidService.getSoftwareEvaluationBySupplierId(supplierId), 0),
				HttpStatus.OK);
	}

}