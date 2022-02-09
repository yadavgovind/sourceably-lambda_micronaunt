package com.oms.projectbuddy.controller;

import com.oms.projectbuddy.services.ICertificationService;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oms.projectbuddy.dto.GlobalSearchFilter;
import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.IProjectCreationService;


@ExecuteOn(TaskExecutors.IO)
@Controller("/api")
public class DashBoardController {
	@Autowired
	private IProjectCreationService iProjectCreationService;
	@Autowired
	private ICertificationService  iCertificationService;
	
	@GetMapping("/getProjectByOptionalProperty")
	public ResponseEntity<?> getProjectByOptionalProperty( GlobalSearchFilter searchFilter) {
		try {
			return new ResponseEntity<>(
					new EntityResponse(iProjectCreationService.getProjectByOptionalProperty(searchFilter), 0),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAllProjectStatusByMonth")
	public ResponseEntity<?> getAllProjectDetailsByDate(@RequestParam(name = "startDate")  String startDate, @RequestParam(name = "endDate")  String endDate) throws Exception {
		return new ResponseEntity<>(new EntityResponse(iProjectCreationService.getAllProjectCostByMonth(startDate,endDate), 0),
				HttpStatus.OK);
	}


	@GetMapping("/marketData")
	public  ResponseEntity<?> getAllMarketData(@RequestParam String startDate,@RequestParam String endDate) throws Exception{
		return new ResponseEntity<>(new EntityResponse(iCertificationService.getGlobalScoreBasedOnSupplier(stringToLocalDate(startDate),stringToLocalDate(endDate)), 0),
				HttpStatus.OK);
	}
	
	/**
	 * For Project Milestone and proposal Status graph.
	 */
	@GetMapping("/getInternalBudgetProposalStatus")
	public ResponseEntity<?> getProjectByProjectStatus(@RequestParam String status,@RequestParam(name = "startDate")  String startDate, @RequestParam(name = "endDate")  String endDate) throws Exception {
		return new ResponseEntity<>(new EntityResponse(iProjectCreationService.getInternalBudgetProposalStatus(status,startDate,endDate), 0),
				HttpStatus.OK);
	}
	
	@GetMapping("/getRevenuePerformance")
	public ResponseEntity<?> getRevenuePerformance(@RequestParam(name = "startDate")  String startDate, @RequestParam(name = "endDate")  String endDate) throws Exception {
		return new ResponseEntity<>(new EntityResponse(iProjectCreationService.getRevenuePerformance(startDate,endDate), 0),
				HttpStatus.OK);
	}

	//this
	@GetMapping("/bcertificatePercentage/{supplierId}")
	public ResponseEntity<?> bcertificatePercentage(@PathVariable("supplierId") String supplierId) throws Exception {
		return new ResponseEntity<>(new EntityResponse(iProjectCreationService.bcertificatePercentage(supplierId), 0),
				HttpStatus.OK);
	}

	@GetMapping("/globalCyberGraphData")
	public ResponseEntity<?> globalCyberGraphData(@RequestParam(name = "startDate")  String startDate, @RequestParam(name = "endDate")  String endDate, @RequestParam String supplierId) {

		try {
			return new ResponseEntity<>(new EntityResponse(iCertificationService.globalCyberGraphData(stringToLocalDate(startDate),stringToLocalDate(endDate),"",supplierId), 0),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/globalBussinessGraphData")
	public ResponseEntity<?> globalBussinessGraphData(@RequestParam(name = "startDate")  String startDate, @RequestParam(name = "endDate")  String endDate,
													  @RequestParam(required = false) String supplierId) throws Exception {

		return new ResponseEntity<>(new EntityResponse(iProjectCreationService.globalCyberGraphData(startDate,endDate,supplierId), 0),
				HttpStatus.OK);
	}

	private LocalDate stringToLocalDate(String date){
		return LocalDate.parse(date);
	}
	
}
