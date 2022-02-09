package com.oms.projectbuddy.services;

import com.oms.projectbuddy.model.*;
import com.oms.projectbuddy.model.request.*;
import com.oms.projectbuddy.model.response.BusinessBasicInfoResponse;


public interface ICompanyInfoService {

	String saveBasicInfo(BusinessBasicInfoRequest basicInfoRequest) throws Exception;

	BusinessBasicInfoResponse getInfoByUserId(String userId) throws Exception;
	
	String updateBasicInfo(BusinessBasicInfoRequest basicInfoRequest) throws Exception;

	String saveBankingInformation(BankingInformationRequest bankingInformationRequest, String userId) throws Exception;

	BankingInformationRequest getBankingInformation(String userId) throws Exception;

	String updateBankingInformation(BankingInformationRequest bankingRequest, String userId) throws Exception;

	String saveCompanyDetails(CompanyDetailsRequest detailsRequest) throws Exception;

	String updateCompanyDetails(CompanyDetailsRequest detailsRequest) throws Exception;

	CompanyDetails getCompanyDetails(String userId) throws Exception;

	String saveCompanySales(CompanySalesRequest salesRequest) throws Exception;

	CompanySales getCompanySales(String userId) throws Exception;

	String updateCompanySales(CompanySalesRequest salesRequest) throws Exception;

	String saveCompanyHighlights(CompanyHighlightsRequest highlightsRequest) throws Exception;

	CompanyHighlights getCompanyHighlights(String userId) throws Exception;

	String updateCompanyHighlights(CompanyHighlightsRequest highlightsRequest) throws Exception;

	String saveCompanyFinancials(CompanyFinancialsRequest financialsRequest) throws Exception;

	CompanyFinancials getCompanyFinancials(String userId) throws Exception;

	String updateCompanyfinancials(CompanyFinancialsRequest financialsRequest) throws Exception;

	String removeCompanyVideo(String userId) throws Exception;

	String deletedUploadedFiles(String userId, Long id, String type) throws Exception;
}
