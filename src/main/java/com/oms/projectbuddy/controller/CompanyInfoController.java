package com.oms.projectbuddy.controller;

import com.oms.projectbuddy.model.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oms.projectbuddy.model.request.BusinessBasicInfoRequest;
import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.ICompanyInfoService;

@ExecuteOn(TaskExecutors.IO)
@Controller("/api/company")
public class CompanyInfoController {
    @Autowired
    private ICompanyInfoService iCompanyInfoService;

    @GetMapping("/getBasicInfoByUserId")
    public ResponseEntity<?> getCompanyBasicInfo(@RequestParam String userId) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iCompanyInfoService.getInfoByUserId(userId), 0), HttpStatus.OK);
    }

    @PostMapping("/saveCompanyBasicInfo")
    public ResponseEntity<?> saveCompanyBasicInfo(@RequestBody BusinessBasicInfoRequest basicInfoRequest) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iCompanyInfoService.saveBasicInfo(basicInfoRequest), 0), HttpStatus.OK);
    }

    @PutMapping("/updateCompanyBasicInfo")
    public ResponseEntity<?> updateCompanyBasicInfo(@RequestBody BusinessBasicInfoRequest basicInfoRequest) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iCompanyInfoService.updateBasicInfo(basicInfoRequest), 0), HttpStatus.OK);
    }

    @PutMapping("/removeCompanyVideo")
    public ResponseEntity<?> removeCompany(@RequestParam String userId) {
        try {
            return new ResponseEntity<>(new CustomResponseMessage(iCompanyInfoService.removeCompanyVideo(userId), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getBankInfoByUserId")
    public ResponseEntity<?> getBankingInfo(@RequestParam String userId) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iCompanyInfoService.getBankingInformation(userId), 0), HttpStatus.OK);
    }

    @PostMapping("/saveBankingInfo")
    public ResponseEntity<?> saveBankingInfo(@RequestBody BankingInformationRequest bankingRequest, @RequestParam String userId) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iCompanyInfoService.saveBankingInformation(bankingRequest, userId), 0), HttpStatus.OK);
    }

    @PutMapping("/updateBankingInfo")
    public ResponseEntity<?> updateBankingInfo(@RequestBody BankingInformationRequest bankingRequest, @RequestParam String userId) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iCompanyInfoService.updateBankingInformation(bankingRequest, userId), 0), HttpStatus.OK);

    }

    @PostMapping("/saveCompanyOverview")
    public ResponseEntity<?> saveCompanyDetails(@RequestBody CompanyDetailsRequest detailsRequestRequest) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iCompanyInfoService.saveCompanyDetails(detailsRequestRequest), 0), HttpStatus.OK);
    }

    @PutMapping("/updateCompanyOverview")
    public ResponseEntity<?> updateCompanyDetails(@RequestBody CompanyDetailsRequest detailsRequest) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iCompanyInfoService.updateCompanyDetails(detailsRequest), 0), HttpStatus.OK);
    }

    @GetMapping("/getCompanyOverviewByUserId")
    public ResponseEntity<?> getCompanyDetails(@RequestParam String userId) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iCompanyInfoService.getCompanyDetails(userId), 0), HttpStatus.OK);
    }

    @PostMapping("/saveCompanyHighlights")
    public ResponseEntity<?> saveCompanyHighlights(@RequestBody CompanyHighlightsRequest highlightsRequest) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iCompanyInfoService.saveCompanyHighlights(highlightsRequest), 0), HttpStatus.OK);
    }

    @GetMapping("/getCompanyHighlightsByUserId")
    public ResponseEntity<?> getCompanyHighlights(@RequestParam String userId) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iCompanyInfoService.getCompanyHighlights(userId), 0), HttpStatus.OK);
    }

    @PutMapping("/updateCompanyHighlights")
    public ResponseEntity<?> updateCompanyHighlights(@RequestBody CompanyHighlightsRequest highlightsRequest) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iCompanyInfoService.updateCompanyHighlights(highlightsRequest), 0), HttpStatus.OK);
    }

    @PostMapping("/saveCompanyFinancials")
    public ResponseEntity<?> saveCompanyFinancials(@RequestBody CompanyFinancialsRequest financialsRequest) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iCompanyInfoService.saveCompanyFinancials(financialsRequest), 0), HttpStatus.OK);
    }

    @GetMapping("/getCompanyFinancialByUserId")
    public ResponseEntity<?> getCompanyFinancials(@RequestParam String userId) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iCompanyInfoService.getCompanyFinancials(userId), 0), HttpStatus.OK);
    }

    @PutMapping("/updateCompanyFinancial")
    public ResponseEntity<?> updateCompanyFinancial(@RequestBody CompanyFinancialsRequest financialsRequest) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iCompanyInfoService.updateCompanyfinancials(financialsRequest), 0), HttpStatus.OK);
    }

    @PostMapping("/saveCompanySales")
    public ResponseEntity<?> saveCompanySales(@RequestBody CompanySalesRequest salesRequest) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iCompanyInfoService.saveCompanySales(salesRequest), 0), HttpStatus.OK);
    }

    @GetMapping("/getCompanySalesByUserId")
    public ResponseEntity<?> getCompanySales(@RequestParam String userId) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iCompanyInfoService.getCompanySales(userId), 0), HttpStatus.OK);
    }

    @PutMapping("/updateCompanySales")
    public ResponseEntity<?> updateCompanySales(@RequestBody CompanySalesRequest salesRequest) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iCompanyInfoService.updateCompanySales(salesRequest), 0), HttpStatus.OK);
    }

    @DeleteMapping("/deleteFiles")
    public ResponseEntity<?> deleFiles(@RequestParam String userId, @RequestParam Long id, @RequestParam String type) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iCompanyInfoService.deletedUploadedFiles(userId, id, type), 0), HttpStatus.OK);
    }
}