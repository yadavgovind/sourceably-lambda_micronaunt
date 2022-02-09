package com.oms.projectbuddy.services;

import com.oms.projectbuddy.dto.GlobalMarketData;
import com.oms.projectbuddy.model.ProjectBidDocumentHistory;
import com.oms.projectbuddy.model.SoftwareEvaluation;
import com.oms.projectbuddy.model.request.ProjectBidDocumentHistoryRequest;
import com.oms.projectbuddy.model.request.SoftwareEvaluationRequest;

import java.time.LocalDate;
import java.util.List;

public interface IProjectBidService {

    List<GlobalMarketData> getSpendValue(LocalDate startDate, LocalDate endDate, String supplierId) throws Exception;

    List<ProjectBidDocumentHistory> getSupplierProjectsHistoryByProjectId(String projectId, String supplierId, String consumerId) throws Exception;

    ProjectBidDocumentHistory saveDocumentHistory(ProjectBidDocumentHistoryRequest request)throws Exception;

    List<SoftwareEvaluation> saveUpdateSoftwareEvaluation(SoftwareEvaluationRequest request) throws Exception;

    List<SoftwareEvaluation> getSoftwareEvaluationBySupplierId(String supplierId) throws Exception;
}

