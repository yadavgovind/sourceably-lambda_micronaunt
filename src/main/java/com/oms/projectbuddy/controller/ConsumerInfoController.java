package com.oms.projectbuddy.controller;

import com.oms.projectbuddy.model.request.*;
import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.IConsumerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ExecuteOn(TaskExecutors.IO)
@Controller("/api/consumer")
public class ConsumerInfoController {
@Autowired
private IConsumerInfoService iConsumerInfoService;

    @GetMapping("/getConsumerBasicInfoByUserId")
    public ResponseEntity<?> getConsumerBasicInfo(@RequestParam String userId) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iConsumerInfoService.getConsumerBasicInfo(userId), 0), HttpStatus.OK);
    }

    @PostMapping("/saveConsumerBasicInfo")
    public ResponseEntity<?> saveConsumerBasicInfo(@RequestBody ConsumerBusinessBasicInfoRequest basicInfoRequest) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iConsumerInfoService.saveConsumerBasicInfo(basicInfoRequest), 0), HttpStatus.OK);
    }

    @PutMapping("/updateConsumerBasicInfo")
    public ResponseEntity<?> updateConsumerBasicInfo(@RequestBody ConsumerBusinessBasicInfoRequest basicInfoRequest) {
        try {
            return new ResponseEntity<>(new CustomResponseMessage(iConsumerInfoService.updateConsumerBasicInfo(basicInfoRequest), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/getConsumerBankInfoByUserId")
    public ResponseEntity<?> getConsumerBankingInfo(@RequestParam String userId) {
        try {
            return new ResponseEntity<>(new EntityResponse(iConsumerInfoService.getConsumerBankingInformation(userId), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/saveConsumerBankingInfo")
    public ResponseEntity<?> saveConsumerBankingInfo(@RequestBody ConsumerBankingInfoRequest bankingRequest
                                                      ,@RequestParam String userId) {
        try {
            return new ResponseEntity<>(new CustomResponseMessage(iConsumerInfoService.saveConsumerBankingInformation(bankingRequest,userId), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/updateConsumerBankingInfo")
    public ResponseEntity<?> updateBankingInfo(@RequestBody ConsumerBankingInfoRequest bankingRequest,
                                               @RequestParam String userId) {
        try {
            return new ResponseEntity<>(new CustomResponseMessage(iConsumerInfoService.updateConsumerBankingInformation(bankingRequest,userId), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/saveConsumerCompanyOverview")
    public ResponseEntity<?> saveConsumerDetails(@RequestBody ConsumerDetailsRequest detailsRequest) {
        try {
            return new ResponseEntity<>(new CustomResponseMessage(iConsumerInfoService.saveConsumerDetails(detailsRequest), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateConsumerCompanyOverview")
    public ResponseEntity<?> updateConsumerDetails(@RequestBody ConsumerDetailsRequest detailsRequest) {
        try {
            return new ResponseEntity<>(new CustomResponseMessage(iConsumerInfoService.updateConsumerDetails(detailsRequest), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getConsumerCompanyOverviewByUserId")
    public ResponseEntity<?> getConsumerDetails(@RequestParam String userId) {
        try {
            return new ResponseEntity<>(new EntityResponse(iConsumerInfoService.getConsumerDetails(userId), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/saveConsumerHighlights")
    public ResponseEntity<?> saveConsumerHighlights(@RequestBody ConsumerHighlightsRequest highlightsRequest) {
        try {
            return new ResponseEntity<>(new CustomResponseMessage(iConsumerInfoService.saveConsumerHighlights(highlightsRequest), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getConsumerHighlightsByUserId")
    public ResponseEntity<?> getConsumerHighlights(@RequestParam String userId) {
        try {
            return new ResponseEntity<>(new EntityResponse(iConsumerInfoService.getConsumerHighlights(userId), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateConsumerHighlights")
    public ResponseEntity<?> updateConsumerHighlights(@RequestBody ConsumerHighlightsRequest highlightsRequest) {
        try {
            return new ResponseEntity<>(new CustomResponseMessage(iConsumerInfoService.updateConsumerHighlights(highlightsRequest), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/saveConsumerFinancials")
    public ResponseEntity<?> saveConsumerFinancials(@RequestBody ConsumerFinancialsRequest financialsRequest) {
        try {
            return new ResponseEntity<>(new CustomResponseMessage(iConsumerInfoService.saveConsumerFinancials(financialsRequest), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getConsumerFinancialByUserId")
    public ResponseEntity<?> getConsumerFinancials(@RequestParam String userId) {
        try {
            return new ResponseEntity<>(new EntityResponse(iConsumerInfoService.getConsumerFinancials(userId), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateConsumerFinancial")
    public ResponseEntity<?> updateConsumerFinancial(@RequestBody ConsumerFinancialsRequest financialsRequest) {
        try {
            return new ResponseEntity<>(new CustomResponseMessage(iConsumerInfoService.updateConsumerfinancials(financialsRequest), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/saveConsumerSales")
    public ResponseEntity<?> saveConsumerSales(@RequestBody ConsumerSalesRequest salesRequest) {
        try {
            return new ResponseEntity<>(new CustomResponseMessage(iConsumerInfoService.saveConsumerSales(salesRequest), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getConsumerSalesByUserId")
    public ResponseEntity<?> getConsumerSales(@RequestParam String userId) {
        try {
            return new ResponseEntity<>(new EntityResponse(iConsumerInfoService.getConsumerSales(userId), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateConsumerSales" )
    public ResponseEntity<?> updateCompanySales(@RequestBody ConsumerSalesRequest salesRequest) {
        try {
            return new ResponseEntity<>(new CustomResponseMessage(iConsumerInfoService.updateConsumerSales(salesRequest), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/deleteNdaDocument")
    public ResponseEntity<?> deleteNdaDocument(@RequestParam String userId) {
        try {
            return new ResponseEntity<>(new EntityResponse(iConsumerInfoService.deleteNdaDocument(userId), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
