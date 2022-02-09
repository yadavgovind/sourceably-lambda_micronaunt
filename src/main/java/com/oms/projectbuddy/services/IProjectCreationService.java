package com.oms.projectbuddy.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oms.projectbuddy.dto.CountryRevenueDto;
import com.oms.projectbuddy.dto.DashBoardDto;
import com.oms.projectbuddy.dto.GlobalBussinessGraphData;
import com.oms.projectbuddy.dto.GlobalSearchFilter;
import com.oms.projectbuddy.dto.InternalBudgetProposalStatusDto;
import com.oms.projectbuddy.dto.PBcertificateScorePercentage;
import com.oms.projectbuddy.model.CompanyRegistration;
import com.oms.projectbuddy.model.ProjectCreation;
import com.oms.projectbuddy.model.ProjectMileStoneList;
import com.oms.projectbuddy.model.request.ProjectBidPostRequest;
import com.oms.projectbuddy.model.request.ProjectCreationRequest;
import com.oms.projectbuddy.model.request.ProjectDocS3Request;
import com.oms.projectbuddy.model.request.ProjectInviteListRequest;
import com.oms.projectbuddy.model.request.ProviderNdaDocRequest;

public interface IProjectCreationService {

    Object saveUpdateProject(ProjectCreationRequest request) throws Exception;

    String checkConsumerNDA(String userId) throws Exception;

    Page<ProjectCreation> getAllProject(Pageable pageable, Long sort) throws Exception;

//    Object  projectDocS3upload(ProjectDocS3Request file) throws Exception;

//    String deleteS3Document(String fileName) throws Exception;

    Object searchProjectByText(Pageable pageable, String searchtext, String sort, String providerId) throws Exception;

    Object getProjectByCompanyId(String projectCompanyId, Pageable pageable) throws Exception;

    String deleteProjectByProjectCode(String projectCode) throws Exception;

    String changeProjectStatus(String projectCode) throws Exception;

    String addUpdateProjectInvite(ProjectInviteListRequest inviteListRequest) throws Exception;

    Object getAllProjectInvites()throws Exception;

    Object getProjectInvitesByProjectCode(String systemGenerateProjectId) throws Exception;

    Object changeProjectInviteListStatus(Long id) throws Exception;

    String deleteProjectDocument(Long documentId) throws Exception;

    //String addupdateProjectMilestone(ProjectMilestoneListRequest milestoneListRequest);

    ProjectMileStoneList getProjectMileStone(String projectId) throws Exception;


    Object getProjectByProjectCode(String systemGenerateProjectId, String userId, String type) throws Exception;

    Object saveProjectBid(ProjectBidPostRequest postRequest) throws Exception;

    Object getProjectsBidId(String providerId, Pageable pageable) throws Exception;

    Object saveProviderNdaDoc(ProviderNdaDocRequest ndaRequest) throws Exception;

    String approveProjectNda(String systemGeneratedProjectId, String userId, String ndaComment) throws Exception;

    String rejectProjectNda(String systemGeneratedProjectId, String userId, String ndaComment) throws Exception;

    Object getProjetBidsByProjectId(String systemGeneratedProjectId, Pageable pageable) throws Exception;

    String changeBidProposalStatus(String systemGeneratedProjectId, List<String> userIds, String status) throws Exception;

    String updateProjectBid(ProjectBidPostRequest postRequest) throws Exception;

    String deleteBidDocument(Long bidId, Long documentId) throws Exception;

    Object searchProjectByCategory(Pageable pageable, String categoryId, String category2Id, String category3Id, String providerId) throws Exception;

    Object filterProject(Pageable pageable, String budgetRange, String teamSize, String providerId) throws Exception;


    Object getAllProjectsDetails(Pageable pageable, Long sort, String providerId)throws Exception;

    List<CompanyRegistration> getProjectByOptionalProperty(GlobalSearchFilter searchFilter)throws Exception;

    List<DashBoardDto> getAllProjectCostByMonth(String startDate, String endDate)throws Exception;

    List<InternalBudgetProposalStatusDto> getInternalBudgetProposalStatus(String status, String startDate, String endDate)throws Exception;

    List<CountryRevenueDto> getRevenuePerformance(String startDate, String endDate)throws Exception;

    List<PBcertificateScorePercentage> bcertificatePercentage(String supplierId)throws Exception;

    List<GlobalBussinessGraphData> globalCyberGraphData(String startDate, String endDate, String supplierId)throws Exception;

    List<ProjectCreation> getProjectsByStatus(String projectSatus)throws Exception;

    String saveUpdateBidDocument(Long id, String status, Double score);

    String updateProjectstatus(String projectCode, String projectStatus)throws Exception;

    String marketUpdate(String projectCode, Boolean publish)throws Exception;
}
