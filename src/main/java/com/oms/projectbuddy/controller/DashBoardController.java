package com.oms.projectbuddy.controller;

import com.oms.projectbuddy.services.ICertificationService;

import java.time.LocalDate;

import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;


import com.oms.projectbuddy.dto.GlobalSearchFilter;
import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.IProjectCreationService;


@ExecuteOn(TaskExecutors.IO)
@Controller("/api")
public class DashBoardController {
	@Inject
	private IProjectCreationService iProjectCreationService;
	@Inject
	private ICertificationService  iCertificationService;
	
	@Get("/getProjectByOptionalProperty")
	public Object getProjectByOptionalProperty( GlobalSearchFilter searchFilter) {
		try {
			return
					new EntityResponse(iProjectCreationService.getProjectByOptionalProperty(searchFilter), 0) ;
		} catch (Exception e) {
			return new CustomResponseMessage(e.getMessage(), -1);
		}
	}

	@Get("/getAllProjectStatusByMonth")
	public Object getAllProjectDetailsByDate( String startDate,   String endDate) throws Exception {
		return new EntityResponse(iProjectCreationService.getAllProjectCostByMonth(startDate,endDate), 0);
	}


	@Get("/marketData")
	public  Object getAllMarketData( String startDate, String endDate) throws Exception{
		return new EntityResponse(iCertificationService.getGlobalScoreBasedOnSupplier(stringToLocalDate(startDate),stringToLocalDate(endDate)), 0);
	}
	
	/**
	 * For Project Milestone and proposal Status graph.
	 */
	@Get("/getInternalBudgetProposalStatus")
	public Object getProjectByProjectStatus( String status,  String startDate,  String endDate) throws Exception {
		return new EntityResponse(iProjectCreationService.getInternalBudgetProposalStatus(status,startDate,endDate), 0);

	}
	
	@Get("/getRevenuePerformance")
	public Object getRevenuePerformance(  String startDate,   String endDate) throws Exception {
		return new EntityResponse(iProjectCreationService.getRevenuePerformance(startDate,endDate), 0);

	}

	//this
	@Get("/bcertificatePercentage/{supplierId}")
	public Object bcertificatePercentage(@PathVariable("supplierId") String supplierId) throws Exception {
		return new EntityResponse(iProjectCreationService.bcertificatePercentage(supplierId), 0);

	}

	@Get("/globalCyberGraphData")
	public Object globalCyberGraphData(  String startDate,    String endDate,  String supplierId) {

		try {
			return new EntityResponse(iCertificationService.globalCyberGraphData(stringToLocalDate(startDate),stringToLocalDate(endDate),"",supplierId), 0);
		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponseMessage(e.getMessage(), -1);
		}
	}

	@Get("/globalBussinessGraphData")
	public Object globalBussinessGraphData(  String startDate,  String endDate,
													   String supplierId) throws Exception {

		return new EntityResponse(iProjectCreationService.globalCyberGraphData(startDate,endDate,supplierId), 0);

	}

	private LocalDate stringToLocalDate(String date){
		return LocalDate.parse(date);
	}
	
}
