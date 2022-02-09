package com.oms.projectbuddy.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.oms.projectbuddy.utils.ExceptionUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.oms.projectbuddy.dto.GlobalMarketData;
import com.oms.projectbuddy.exception.SourceablyCustomeException;
import com.oms.projectbuddy.model.CompanyRegistration;
import com.oms.projectbuddy.model.ProjectBidDocumentHistory;
import com.oms.projectbuddy.model.ProjectBidPost;
import com.oms.projectbuddy.model.SoftwareEvaluation;
import com.oms.projectbuddy.model.request.ProjectBidDocumentHistoryRequest;
import com.oms.projectbuddy.model.request.SoftwareEvaluationRequest;
import com.oms.projectbuddy.repository.CompanyRepository;
import com.oms.projectbuddy.repository.ProjectBidDocumentHistoryRepository;
import com.oms.projectbuddy.repository.ProjectBidPostRepository;
import com.oms.projectbuddy.repository.SoftwareEvaluationRepository;
import com.oms.projectbuddy.services.IProjectBidService;

@Service
public class ProjectBidService implements IProjectBidService {

    @Autowired
    private ProjectBidPostRepository projectBidPostRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ProjectBidDocumentHistoryRepository projectBidDocumentHistoryRepository;

    @Autowired
    private SoftwareEvaluationRepository softwareEvaluationRepository;

    @Override
    public List<GlobalMarketData> getSpendValue(LocalDate startDate, LocalDate endDate, String supplierId) throws Exception {

        // todo need to discuss with Murali if its related to consumer then keep
        // consumerId in filter

        List<ProjectBidPost> projectBids = projectBidPostRepository.findAllByProjectBidDateBetween(startDate, endDate);
        ExceptionUtils.verifyDataNotExistThenThrowException(projectBids);
        projectBids.sort(Comparator.comparing(ProjectBidPost::getProjectBidDate));
        Map<String, List<ProjectBidPost>> projectBudgetMap;
//        if(!CollectionUtils.isEmpty(projectBids) && supplierId!=null){
//            projectBudgetMap = projectBids.stream().filter(predicate ->predicate.getProviderId().equals(supplierId)&& predicate.getNdaApprovalStatus().name().equals("GRANTED")).collect(Collectors.groupingBy(projectBid->projectBid.getProjectBidDate().getMonth().name()));
//        }else{
        projectBudgetMap = projectBids.stream()
                .filter(predicate -> predicate.getNdaApprovalStatus().name().equals("GRANTED"))
                .collect(Collectors.groupingBy(projectBid -> projectBid.getProjectBidDate().getMonth().name()));

//        }
        Double costValue = 0.0;
        Double customerValue = 0.0;
        TreeMap<String, List<ProjectBidPost>> sortedMap = new TreeMap<>();
        sortedMap.putAll(projectBudgetMap);

        List<GlobalMarketData> globalMarketData = new ArrayList<>();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        for (Map.Entry<String, List<ProjectBidPost>> projectBidsKeyValue : sortedMap.entrySet()) {
            costValue = costValue
                    + projectBidsKeyValue.getValue().stream().mapToDouble(ProjectBidPost::getProjectCost).sum();
            CompanyRegistration registration = companyRepository.findByEmail(name);
            customerValue = customerValue + projectBidsKeyValue.getValue().stream()
                    .filter(predicate -> predicate.getConsumerId().equals(registration.getUserId()))
                    .mapToDouble(ProjectBidPost::getProjectCost).sum();
            GlobalMarketData data = new GlobalMarketData(projectBidsKeyValue.getKey(), customerValue, costValue);
            globalMarketData.add(data);

        }

        return globalMarketData;
    }

    @Override
    public List<ProjectBidDocumentHistory> getSupplierProjectsHistoryByProjectId(String projectId, String supplierId,
                                                                                 String consumerId) throws Exception{

        CompanyRegistration supplier = companyRepository.findByUserId(supplierId);
        ExceptionUtils.verifyDataNotExistThenThrowException(supplier,"Supplier not found");
        CompanyRegistration consumer = companyRepository.findByUserId(consumerId);
        ExceptionUtils.verifyDataNotExistThenThrowException(consumer,"Supplier not found");
        List<ProjectBidDocumentHistory> allBidDocumnets = projectBidDocumentHistoryRepository
                .findByProjectIdAndSupplierIdAndConsumerId(projectId, supplierId, consumerId);
        ExceptionUtils.verifyDataNotExistThenThrowException(allBidDocumnets);
        allBidDocumnets.forEach(e -> {
            e.setSupplierName(supplier.getFirstName() + " " + supplier.getLastName());
            e.setCustomerName(consumer.getFirstName() + " " + consumer.getLastName());
        });
        return allBidDocumnets;
    }

    @Override
    public ProjectBidDocumentHistory saveDocumentHistory(ProjectBidDocumentHistoryRequest request) throws Exception{
        ProjectBidDocumentHistory entityTobeSave = ProjectBidDocumentHistory.builder().id(request.getId())
                .supplierId(request.getSupplierId()).consumerId(request.getConsumerId())
                .projectId(request.getProjectId()).comment(request.getComment())
                .createdDate(request.getCreatedDate() != null ? request.getCreatedDate() : LocalDate.now())
                .updatedDate(LocalDate.now()).build();
        if (request.getAttachFile() != null) {
            entityTobeSave.setAttachFile(request.getAttachFile());
        }
        try
        {
        	ProjectBidDocumentHistory savedEntity = projectBidDocumentHistoryRepository.save(entityTobeSave);	
        	return savedEntity;
        }catch(Exception e)
        {
        	throw new SourceablyCustomeException("Document history not saved.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public List<SoftwareEvaluation> saveUpdateSoftwareEvaluation(SoftwareEvaluationRequest request) throws Exception {

        List<SoftwareEvaluation> entitiesTobeSave =new ArrayList<SoftwareEvaluation>();
        List<SoftwareEvaluation> allCriteria = getSoftwareEvaluationBySupplierId(request.getSoftEvaluations().get(0).getSupplierId());
        if(allCriteria == null || allCriteria.isEmpty())
        {
            request.getSoftEvaluations().forEach(dto -> {
                SoftwareEvaluation entityTobeSave = SoftwareEvaluation.builder().id(dto.getId())
                        .weight(dto.getWeight()).customerRating(dto.getCustomerRating()).score(dto.getScore())
                        .supplierId(dto.getSupplierId()).criteriaId(dto.getCriteriaId()).build();

                allCriteria.add(entityTobeSave);
            });
            entitiesTobeSave = allCriteria;
        }
        else
        {
            for(SoftwareEvaluation criteria:allCriteria)
            {
                for(SoftwareEvaluation requestCriteria:request.getSoftEvaluations())
                {
                    if(criteria.getSupplierId().equalsIgnoreCase(requestCriteria.getSupplierId()) &&
                            criteria.getCriteriaId().equals(requestCriteria.getCriteriaId()))
                    {
                        criteria.setCustomerRating(requestCriteria.getCustomerRating());
                        criteria.setScore(requestCriteria.getScore());
                        criteria.setWeight(requestCriteria.getWeight());
                    }
                }
            }
            entitiesTobeSave = allCriteria;
        }
        try
        {
        	List<SoftwareEvaluation> savedEntities = softwareEvaluationRepository.saveAll(entitiesTobeSave);
            return savedEntities;	
        }
        catch(Exception e)
        {
        	throw new SourceablyCustomeException("Software evaluation not saved", HttpStatus.UNPROCESSABLE_ENTITY);	
        }
        
    }

    @Override
    public List<SoftwareEvaluation> getSoftwareEvaluationBySupplierId(String supplierId) throws Exception{
        List<SoftwareEvaluation> projects = softwareEvaluationRepository.findBySupplierId(supplierId);
        ExceptionUtils.verifyDataNotExistThenThrowException(projects);
        return projects;
    }
}
