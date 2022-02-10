package com.oms.projectbuddy.controller;


import java.util.List;
import java.util.Optional;

import com.microsoft.schemas.office.visio.x2012.main.PageType;
import com.oms.projectbuddy.dto.BidDocumentRequestDto;
import com.oms.projectbuddy.model.ProjectCreation;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;


import com.oms.projectbuddy.model.request.ProjectBidPostRequest;
import com.oms.projectbuddy.model.request.ProjectCreationRequest;
import com.oms.projectbuddy.model.request.ProjectInviteListRequest;
import com.oms.projectbuddy.model.request.ProviderNdaDocRequest;
import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.IProjectCreationService;
import jakarta.inject.Inject;


@ExecuteOn(TaskExecutors.IO)
@Controller("/api")
//@Api(value = "Consumer project creation", description = "Consumer project", tags = {"Consumer project"})
public class ProjectCreationController {
    @Inject
    private IProjectCreationService iProjectCreationService;

    @Post("/createConsumerProject")
    public Object saveConsumerProject(@Body ProjectCreationRequest projectRequest) throws Exception {
    	return new EntityResponse(iProjectCreationService.saveUpdateProject(projectRequest), 0);
    }

    @Get("/checkConsumerNda")
    public Object checkConsumerNda( String userId) throws Exception {
    	return new EntityResponse(iProjectCreationService.checkConsumerNDA(userId), 0);
    }

//    @Post("/uploadProjectDoc")
//    public Object uploadProjectDoc(@Body ProjectDocS3Request docS3Request) {
//        try {
//            return new EntityResponse(iProjectCreationService.projectDocS3upload(docS3Request), 0);
//        } catch (Exception e) {
//            return new CustomResponseMessage(e.getMessage(), -1);
//        }
//    }
//
//    @Delete("/deletedProjectDoc")
//    public Object deletedProjectDoc( String fileName) {
//        try {
//            return new CustomResponseMessage(iProjectCreationService.deleteS3Document(fileName), 0);
//        } catch (Exception e) {
//            return new CustomResponseMessage(e.getMessage(), -1);
//        }
//    }

    @Delete("/deleteProject")
    public Object deleteProjectById( String systemGenerateProjectId) throws Exception {
    	return new CustomResponseMessage(iProjectCreationService.deleteProjectByProjectCode(systemGenerateProjectId), 0);
    }

    @Put("/changeProjectStatus")
    public Object changeProjectStatus( String systemGenerateProjectId) throws Exception {
    	return new CustomResponseMessage(iProjectCreationService.changeProjectStatus(systemGenerateProjectId), 0);
    }

    @Get("/getAllProjects")
    public  Page<ProjectCreation> getAllProjects(  Integer page,
                                                                 Integer size,
                                                                 Long sort) throws Exception {
    	Pageable pageable = Pageable.from(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
        return iProjectCreationService.getAllProject(pageable, sort);
    }

    @Get("/searchProjects")
    public Object searchProjectByProjectTitle(  Integer page,
                                                          Integer size,
                                                          String searchtext,
                                                          String sort,  String providerId) throws Exception {
    	Pageable pageable = Pageable.from(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
        return new EntityResponse(iProjectCreationService.searchProjectByText(pageable, searchtext, sort, providerId), 0);    }

    @Get("/searchProjectsByCategory")
    public Object searchProjectByProjectCategory(  Integer page,
                                                             Integer size,
                                                             String subCategoryId,
                                                             String subCategory2Id,
                                                              String subCategory3Id,
                                                             String providerId) throws Exception {
    	Pageable pageable = Pageable.from(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
        return new EntityResponse(iProjectCreationService.searchProjectByCategory(pageable, subCategoryId,subCategory2Id,subCategory3Id, providerId), 0);
    }

    @Get("/filterProject")
    public Object filterProject(  Integer page,
                                           Integer size,
                                           String budgetRange,
                                             String teamSize,  String providerId) throws Exception {
    	Pageable pageable = Pageable.from(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
        return new EntityResponse(iProjectCreationService.filterProject(pageable, budgetRange, teamSize, providerId), 0);
    }

    @Get("/getProjectsByCompanyId")
    public Object getProjectById( String companyId,
                                             Integer page,
                                             Integer size) throws Exception {
    	Pageable pageable = Pageable.from(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
        return new EntityResponse(iProjectCreationService.getProjectByCompanyId(companyId, pageable), 0);
    }

    @Get("/getProjectsByProjectId")
    public Object getProjectByProjectId( String systemGenerateProjectId,  String userId,  String type) throws Exception {
    	return new EntityResponse(iProjectCreationService.getProjectByProjectCode(systemGenerateProjectId, userId, type), 0);
    }

    @Post("/CreateProjectinvites")
    public Object saveProjectInvites(@Body ProjectInviteListRequest inviteRequest) throws Exception {
    	return new CustomResponseMessage(iProjectCreationService.addUpdateProjectInvite(inviteRequest), 0);
    }

    @Get("/getProjectInvite")
    public Object getProjectInvite( String systemGenerateProjectId) throws Exception {
    	return new EntityResponse(iProjectCreationService.getProjectInvitesByProjectCode(systemGenerateProjectId), 0);
    }

    @Get("/getAllProjectInvite")
    public Object getAllProjectInvite() throws Exception{
    	return new EntityResponse(iProjectCreationService.getAllProjectInvites(), 0);
    }

    @Put("/changeProjectInviteStatus")
    public Object changeProjectInviteStatus( Long id) throws Exception {
    	return new EntityResponse(iProjectCreationService.changeProjectInviteListStatus(id), 0);
    }

    @Delete("/removeProjectDocument")
    public Object removeProjectDocument( Long documentId) throws Exception {
    	return new EntityResponse(iProjectCreationService.deleteProjectDocument(documentId), 0);
    }

    @Get("/getProjectMilestone")
    public Object getProjectMilestone( String projectCode) throws Exception {
    	return new EntityResponse(iProjectCreationService.getProjectMileStone(projectCode), 0);
    }

    @Post("/saveProjectBid")
    public Object saveProjectBid(@Body ProjectBidPostRequest postRequest) throws Exception {
    	return new EntityResponse(iProjectCreationService.saveProjectBid(postRequest), 0);
    }

    @Put("/updateProjectBid")
    public Object updateProjectBid(@Body ProjectBidPostRequest postRequest) throws Exception {
    	return new EntityResponse(iProjectCreationService.updateProjectBid(postRequest), 0);
    }

    @Post("/projectBidDocument")
    public Object updateProjectBidDocument(@Body List<BidDocumentRequestDto> bidDocumentRequestDto) {
        try {
            if(CollectionUtils.isEmpty(bidDocumentRequestDto)){
                return new CustomResponseMessage("please add at least one request", -1);
            }
            for (BidDocumentRequestDto documentRequestDto : bidDocumentRequestDto) {
                iProjectCreationService.saveUpdateBidDocument(documentRequestDto.getId(),documentRequestDto.getDocumentStatus(),documentRequestDto.getScore());
            }

            return new EntityResponse("Successfully updated All documents", 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }
    }

    @Post("/saveProviderNDA")
    public Object saveProviderNDA(@Body ProviderNdaDocRequest ndaRequest) throws Exception {
    	return new EntityResponse(iProjectCreationService.saveProviderNdaDoc(ndaRequest), 0);
    }

    @Post("/appproveProjectNda")
    public Object approveProjectNda( String systemGeneratedProjectId,  String userId,
                                                String ndaComment) throws Exception {
    	return new EntityResponse(iProjectCreationService.approveProjectNda(systemGeneratedProjectId, userId, ndaComment), 0);
    }

    @Post("/rejectProjectNda")
    public Object rejectProjectNda( String systemGeneratedProjectId,  String userId,
                                               String ndaComment) throws Exception {
    	return new EntityResponse(iProjectCreationService.rejectProjectNda(systemGeneratedProjectId, userId, ndaComment), 0);
    }

    @Get("/getProjetBidsByProjectId")
    public Object getProjetBidsByProjectId( String systemGeneratedProjectId,
                                                       Integer page,
                                                        Integer size) throws Exception {
    	Pageable pageable = Pageable.from(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
        return new EntityResponse(iProjectCreationService.getProjetBidsByProjectId(systemGeneratedProjectId, pageable), 0);
    }

    @Put("/changeBidProposalStatus")
    public Object changeBidProposalStatus( String systemGeneratedProjectId,  List<String> userIds,  String proposalStatus) throws Exception {
    	return new CustomResponseMessage(iProjectCreationService.changeBidProposalStatus(systemGeneratedProjectId, userIds, proposalStatus), 0);
    }

    @Delete("/deleteBidDocument")
    public Object deleteBidDocument( Long bidId,  Long documentId) throws Exception {
    	return new CustomResponseMessage(iProjectCreationService.deleteBidDocument(bidId, documentId), 0);
    }

    @Get("/getProjectsByBid")
    public Object getProjectById( Integer page,
                                              Integer size,
                                             String providerId) throws Exception {
    	Pageable pageable = Pageable.from(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(30));
        return new EntityResponse(iProjectCreationService.getProjectsBidId(providerId, pageable), 0);
    }

    @Get("/getAllProjectsDetails")
    public Object getAllProjects( Integer page,
                                            Integer size,
                                             Long sort,  String providerId) throws Exception {
    	Pageable pageable = Pageable.from(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
        return new EntityResponse(iProjectCreationService.getAllProjectsDetails(pageable, sort, providerId), 0);
    }


    @Get("/getProjectsByStatus")
    public Object getProjectsByStatus(  String projectStatus) throws Exception {
    	return new EntityResponse(iProjectCreationService.getProjectsByStatus(projectStatus), 0);
    }

    // To update project status by project code
    @Put("/updateProjectstatus")
    public Object updateProjectstatus(  String projectCode,   String projectStatus) throws Exception {
    	return new EntityResponse(iProjectCreationService.updateProjectstatus(projectCode,projectStatus), 0);
    }

    // To publish and unpublish Project
    @Put("/marketUpdate")
    public Object marketUpdate(   String projectCode,   Boolean isPublish) {

        try {
            return new EntityResponse(iProjectCreationService.marketUpdate(projectCode,isPublish), 0);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomResponseMessage(e.getMessage(), -1);
        }
    }
}
