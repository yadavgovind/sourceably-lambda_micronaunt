package com.oms.projectbuddy.controller;

import com.oms.projectbuddy.model.request.*;
import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.IConsumerInfoService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;


@ExecuteOn(TaskExecutors.IO)
@Controller("/api/consumer")
public class ConsumerInfoController {
@Inject
private IConsumerInfoService iConsumerInfoService;

    @Get("/getConsumerBasicInfoByUserId")
    public Object getConsumerBasicInfo( String userId) throws Exception {
    	return new EntityResponse(iConsumerInfoService.getConsumerBasicInfo(userId), 0);
    }

    @Post("/saveConsumerBasicInfo")
    public Object saveConsumerBasicInfo(@Body ConsumerBusinessBasicInfoRequest basicInfoRequest) throws Exception {
    	return new CustomResponseMessage(iConsumerInfoService.saveConsumerBasicInfo(basicInfoRequest), 0);
    }

    @Put("/updateConsumerBasicInfo")
    public Object updateConsumerBasicInfo(@Body ConsumerBusinessBasicInfoRequest basicInfoRequest) {
        try {
            return new CustomResponseMessage(iConsumerInfoService.updateConsumerBasicInfo(basicInfoRequest), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }

    }
    @Get("/getConsumerBankInfoByUserId")
    public Object getConsumerBankingInfo( String userId) {
        try {
            return new EntityResponse(iConsumerInfoService.getConsumerBankingInformation(userId), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }
    }

    @Post("/saveConsumerBankingInfo")
    public Object saveConsumerBankingInfo(@Body ConsumerBankingInfoRequest bankingRequest
                                                      , String userId) {
        try {
            return new CustomResponseMessage(iConsumerInfoService.saveConsumerBankingInformation(bankingRequest,userId), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }

    }

    @Put("/updateConsumerBankingInfo")
    public Object updateBankingInfo(@Body ConsumerBankingInfoRequest bankingRequest,
                                                String userId) {
        try {
            return new CustomResponseMessage(iConsumerInfoService.updateConsumerBankingInformation(bankingRequest,userId), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }

    }
    @Post("/saveConsumerCompanyOverview")
    public Object saveConsumerDetails(@Body ConsumerDetailsRequest detailsRequest) {
        try {
            return new CustomResponseMessage(iConsumerInfoService.saveConsumerDetails(detailsRequest), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }
    }

    @Put("/updateConsumerCompanyOverview")
    public Object updateConsumerDetails(@Body ConsumerDetailsRequest detailsRequest) {
        try {
            return new CustomResponseMessage(iConsumerInfoService.updateConsumerDetails(detailsRequest), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }
    }

    @Get("/getConsumerCompanyOverviewByUserId")
    public Object getConsumerDetails( String userId) {
        try {
            return new EntityResponse(iConsumerInfoService.getConsumerDetails(userId), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }
    }

    @Post("/saveConsumerHighlights")
    public Object saveConsumerHighlights(@Body ConsumerHighlightsRequest highlightsRequest) {
        try {
            return new CustomResponseMessage(iConsumerInfoService.saveConsumerHighlights(highlightsRequest), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }
    }

    @Get("/getConsumerHighlightsByUserId")
    public Object getConsumerHighlights( String userId) {
        try {
            return new EntityResponse(iConsumerInfoService.getConsumerHighlights(userId), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }
    }
    @Put("/updateConsumerHighlights")
    public Object updateConsumerHighlights(@Body ConsumerHighlightsRequest highlightsRequest) {
        try {
            return new CustomResponseMessage(iConsumerInfoService.updateConsumerHighlights(highlightsRequest), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }
    }

    @Post("/saveConsumerFinancials")
    public Object saveConsumerFinancials(@Body ConsumerFinancialsRequest financialsRequest) {
        try {
            return new CustomResponseMessage(iConsumerInfoService.saveConsumerFinancials(financialsRequest), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }
    }

    @Get("/getConsumerFinancialByUserId")
    public Object getConsumerFinancials( String userId) {
        try {
            return new EntityResponse(iConsumerInfoService.getConsumerFinancials(userId), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }
    }

    @Put("/updateConsumerFinancial")
    public Object updateConsumerFinancial(@Body ConsumerFinancialsRequest financialsRequest) {
        try {
            return new CustomResponseMessage(iConsumerInfoService.updateConsumerfinancials(financialsRequest), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }
    }

    @Post("/saveConsumerSales")
    public Object saveConsumerSales(@Body ConsumerSalesRequest salesRequest) {
        try {
            return new CustomResponseMessage(iConsumerInfoService.saveConsumerSales(salesRequest), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }
    }

    @Get("/getConsumerSalesByUserId")
    public Object getConsumerSales( String userId) {
        try {
            return new EntityResponse(iConsumerInfoService.getConsumerSales(userId), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }
    }

    @Put(value = "/updateConsumerSales" )
    public Object updateCompanySales(@Body ConsumerSalesRequest salesRequest) {
        try {
            return new CustomResponseMessage(iConsumerInfoService.updateConsumerSales(salesRequest), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);

        }
    }

    @Get("/deleteNdaDocument")
    public Object deleteNdaDocument( String userId) {
        try {
            return new EntityResponse(iConsumerInfoService.deleteNdaDocument(userId), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }
    }


}
