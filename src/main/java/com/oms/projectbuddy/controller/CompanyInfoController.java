package com.oms.projectbuddy.controller;

import com.oms.projectbuddy.model.request.*;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;

import com.oms.projectbuddy.model.request.BusinessBasicInfoRequest;
import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.ICompanyInfoService;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;

@ExecuteOn(TaskExecutors.IO)
@Controller("/api/company")
public class CompanyInfoController {
    @Inject
    private ICompanyInfoService iCompanyInfoService;

    @Get("/getBasicInfoByUserId")
    public  Object getCompanyBasicInfo( String userId) throws Exception {
    	return new EntityResponse(iCompanyInfoService.getInfoByUserId(userId), 0);
    }

    @Post("/saveCompanyBasicInfo")
    public Object saveCompanyBasicInfo(@Body BusinessBasicInfoRequest basicInfoRequest) throws Exception {
    	return new CustomResponseMessage(iCompanyInfoService.saveBasicInfo(basicInfoRequest), 0);
    }

    @Put("/updateCompanyBasicInfo")
    public Object updateCompanyBasicInfo(@Body BusinessBasicInfoRequest basicInfoRequest) throws Exception {
    	return new CustomResponseMessage(iCompanyInfoService.updateBasicInfo(basicInfoRequest), 0);
    }

    @Put("/removeCompanyVideo")
    public Object removeCompany( String userId) {
        try {
            return new CustomResponseMessage(iCompanyInfoService.removeCompanyVideo(userId), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }

    }

    @Get("/getBankInfoByUserId")
    public Object getBankingInfo( String userId) throws Exception {
    	return new EntityResponse(iCompanyInfoService.getBankingInformation(userId), 0);
    }

    @Post("/saveBankingInfo")
    public Object saveBankingInfo(@Body BankingInformationRequest bankingRequest,  String userId) throws Exception {
    	return new CustomResponseMessage(iCompanyInfoService.saveBankingInformation(bankingRequest, userId), 0);
    }

    @Put("/updateBankingInfo")
    public Object updateBankingInfo(@Body BankingInformationRequest bankingRequest,  String userId) throws Exception {
    	return new CustomResponseMessage(iCompanyInfoService.updateBankingInformation(bankingRequest, userId), 0);

    }

    @Post("/saveCompanyOverview")
    public Object saveCompanyDetails(@Body CompanyDetailsRequest detailsRequestRequest) throws Exception {
    	return new CustomResponseMessage(iCompanyInfoService.saveCompanyDetails(detailsRequestRequest), 0);
    }

    @Put("/updateCompanyOverview")
    public Object updateCompanyDetails(@Body CompanyDetailsRequest detailsRequest) throws Exception {
    	return new CustomResponseMessage(iCompanyInfoService.updateCompanyDetails(detailsRequest), 0);
    }

    @Get("/getCompanyOverviewByUserId")
    public Object getCompanyDetails( String userId) throws Exception {
    	return new EntityResponse(iCompanyInfoService.getCompanyDetails(userId), 0);
    }

    @Post("/saveCompanyHighlights")
    public Object saveCompanyHighlights(@Body CompanyHighlightsRequest highlightsRequest) throws Exception {
    	return new CustomResponseMessage(iCompanyInfoService.saveCompanyHighlights(highlightsRequest), 0);
    }

    @Get("/getCompanyHighlightsByUserId")
    public Object getCompanyHighlights( String userId) throws Exception {
    	return new EntityResponse(iCompanyInfoService.getCompanyHighlights(userId), 0);
    }

    @Put("/updateCompanyHighlights")
    public Object updateCompanyHighlights(@Body CompanyHighlightsRequest highlightsRequest) throws Exception {
    	return new CustomResponseMessage(iCompanyInfoService.updateCompanyHighlights(highlightsRequest), 0);
    }

    @Post("/saveCompanyFinancials")
    public Object saveCompanyFinancials(@Body CompanyFinancialsRequest financialsRequest) throws Exception {
    	return new CustomResponseMessage(iCompanyInfoService.saveCompanyFinancials(financialsRequest), 0);
    }

    @Get("/getCompanyFinancialByUserId")
    public Object getCompanyFinancials( String userId) throws Exception {
    	return new EntityResponse(iCompanyInfoService.getCompanyFinancials(userId), 0);
    }

    @Put("/updateCompanyFinancial")
    public Object updateCompanyFinancial(@Body CompanyFinancialsRequest financialsRequest) throws Exception {
    	return new CustomResponseMessage(iCompanyInfoService.updateCompanyfinancials(financialsRequest), 0);
    }

    @Post("/saveCompanySales")
    public Object saveCompanySales(@Body CompanySalesRequest salesRequest) throws Exception {
    	return new CustomResponseMessage(iCompanyInfoService.saveCompanySales(salesRequest), 0);
    }

    @Get("/getCompanySalesByUserId")
    public Object getCompanySales( String userId) throws Exception {
    	return new EntityResponse(iCompanyInfoService.getCompanySales(userId), 0);
    }

    @Put("/updateCompanySales")
    public Object updateCompanySales(@Body CompanySalesRequest salesRequest) throws Exception {
    	return new CustomResponseMessage(iCompanyInfoService.updateCompanySales(salesRequest), 0);
    }

    @Delete("/deleteFiles")
    public Object deleFiles( String userId,  Long id,  String type) throws Exception {
    	return new CustomResponseMessage(iCompanyInfoService.deletedUploadedFiles(userId, id, type), 0);
    }
}