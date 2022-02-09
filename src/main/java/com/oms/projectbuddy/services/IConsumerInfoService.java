package com.oms.projectbuddy.services;


import com.oms.projectbuddy.model.*;
import com.oms.projectbuddy.model.request.*;
import com.oms.projectbuddy.model.response.BusinessBasicInfoResponse;

import java.util.List;

public interface IConsumerInfoService {

    String saveConsumerBasicInfo(ConsumerBusinessBasicInfoRequest basicInfoRequest) throws Exception;

    BusinessBasicInfoResponse getConsumerBasicInfo(String userId) throws Exception;

    String updateConsumerBasicInfo(ConsumerBusinessBasicInfoRequest basicInfoRequest) throws Exception;

    String saveConsumerBankingInformation(ConsumerBankingInfoRequest bankingRequest, String userId) throws Exception;

   // List<ConsumerBankingInformation> getConsumerBankingInformation(String userId) throws Exception;

    ConsumerBankingInfoRequest getConsumerBankingInformation(String userId) throws Exception;

    String updateConsumerBankingInformation(ConsumerBankingInfoRequest bankingRequest, String userId) throws Exception;

    String saveConsumerDetails(ConsumerDetailsRequest detailsRequest) throws Exception;

    String updateConsumerDetails(ConsumerDetailsRequest detailsRequest) throws Exception;

    ConsumerDetails getConsumerDetails(String userId) throws Exception;

    String saveConsumerSales(ConsumerSalesRequest salesRequest) throws Exception;

    ConsumerSales getConsumerSales(String userId) throws Exception;

    String updateConsumerSales(ConsumerSalesRequest salesRequest) throws Exception;

    String saveConsumerHighlights(ConsumerHighlightsRequest highlightsRequest) throws Exception;

    ConsumerHighlights getConsumerHighlights(String userId) throws Exception;

    String updateConsumerHighlights(ConsumerHighlightsRequest highlightsRequest) throws Exception;

    String saveConsumerFinancials(ConsumerFinancialsRequest financialsRequest) throws Exception;

    ConsumerFinancials getConsumerFinancials(String userId) throws Exception;

    String updateConsumerfinancials(ConsumerFinancialsRequest financialsRequest) throws Exception;

    Object deleteNdaDocument(String userId) throws Exception;
}
