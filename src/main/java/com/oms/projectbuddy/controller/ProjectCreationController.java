package com.oms.projectbuddy.controller;


import java.util.List;
import java.util.Optional;

import com.oms.projectbuddy.dto.BidDocumentRequestDto;
import com.oms.projectbuddy.model.ProjectCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.oms.projectbuddy.model.request.ProjectBidPostRequest;
import com.oms.projectbuddy.model.request.ProjectCreationRequest;
import com.oms.projectbuddy.model.request.ProjectInviteListRequest;
import com.oms.projectbuddy.model.request.ProviderNdaDocRequest;
import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.IProjectCreationService;

import io.swagger.annotations.Api;


@ExecuteOn(TaskExecutors.IO)
@Controller("/api")
@Api(value = "Consumer project creation", description = "Consumer project", tags = {"Consumer project"})
public class ProjectCreationController {
    @Autowired
    private IProjectCreationService iProjectCreationService;

    @PostMapping("/createConsumerProject")
    public ResponseEntity<?> saveConsumerProject(@RequestBody ProjectCreationRequest projectRequest) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iProjectCreationService.saveUpdateProject(projectRequest), 0), HttpStatus.OK);
    }

    @GetMapping("/checkConsumerNda")
    public ResponseEntity<?> checkConsumerNda(@RequestParam String userId) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iProjectCreationService.checkConsumerNDA(userId), 0), HttpStatus.OK);
    }

//    @PostMapping("/uploadProjectDoc")
//    public ResponseEntity<?> uploadProjectDoc(@RequestBody ProjectDocS3Request docS3Request) {
//        try {
//            return new ResponseEntity<>(new EntityResponse(iProjectCreationService.projectDocS3upload(docS3Request), 0), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/deletedProjectDoc")
//    public ResponseEntity<?> deletedProjectDoc(@RequestParam String fileName) {
//        try {
//            return new ResponseEntity<>(new CustomResponseMessage(iProjectCreationService.deleteS3Document(fileName), 0), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @DeleteMapping("/deleteProject")
    public ResponseEntity<?> deleteProjectById(@RequestParam String systemGenerateProjectId) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iProjectCreationService.deleteProjectByProjectCode(systemGenerateProjectId), 0), HttpStatus.OK);
    }

    @PutMapping("/changeProjectStatus")
    public ResponseEntity<?> changeProjectStatus(@RequestParam String systemGenerateProjectId) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iProjectCreationService.changeProjectStatus(systemGenerateProjectId), 0), HttpStatus.OK);
    }

    @GetMapping("/getAllProjects")
    public ResponseEntity<Page<ProjectCreation>> getAllProjects(@RequestParam(value = "page", required = false) Integer page,
                                                                @RequestParam(value = "size", required = false) Integer size,
                                                                @RequestParam Long sort) throws Exception {
    	Pageable pageable = PageRequest.of(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
        return new ResponseEntity<>(iProjectCreationService.getAllProject(pageable, sort), HttpStatus.OK);
    }

    @GetMapping("/searchProjects")
    public ResponseEntity<?> searchProjectByProjectTitle(@RequestParam(value = "page", required = false) Integer page,
                                                         @RequestParam(value = "size", required = false) Integer size,
                                                         @RequestParam String searchtext,
                                                         @RequestParam String sort, @RequestParam String providerId) throws Exception {
    	Pageable pageable = PageRequest.of(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
        return new ResponseEntity<>(new EntityResponse(iProjectCreationService.searchProjectByText(pageable, searchtext, sort, providerId), 0), HttpStatus.OK);    }

    @GetMapping("/searchProjectsByCategory")
    public ResponseEntity<?> searchProjectByProjectCategory(@RequestParam(value = "page", required = false) Integer page,
                                                            @RequestParam(value = "size", required = false) Integer size,
                                                            @RequestParam String subCategoryId,
                                                            @RequestParam(value = "subCategory2Id", required = false) String subCategory2Id,
                                                            @RequestParam(value = "subCategory3Id", required = false) String subCategory3Id,
                                                            @RequestParam String providerId) throws Exception {
    	Pageable pageable = PageRequest.of(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
        return new ResponseEntity<>(new EntityResponse(iProjectCreationService.searchProjectByCategory(pageable, subCategoryId,subCategory2Id,subCategory3Id, providerId), 0), HttpStatus.OK);
    }

    @GetMapping("/filterProject")
    public ResponseEntity<?> filterProject(@RequestParam(value = "page", required = false) Integer page,
                                           @RequestParam(value = "size", required = false) Integer size,
                                           @RequestParam(value = "budgetRange", required = false) String budgetRange,
                                           @RequestParam(value = "teamSize", required = false) String teamSize, @RequestParam String providerId) throws Exception {
    	Pageable pageable = PageRequest.of(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
        return new ResponseEntity<>(new EntityResponse(iProjectCreationService.filterProject(pageable, budgetRange, teamSize, providerId), 0), HttpStatus.OK);
    }

    @GetMapping("/getProjectsByCompanyId")
    public ResponseEntity<?> getProjectById(@RequestParam String companyId,
                                            @RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "size", required = false) Integer size) throws Exception {
    	Pageable pageable = PageRequest.of(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
        return new ResponseEntity<>(new EntityResponse(iProjectCreationService.getProjectByCompanyId(companyId, pageable), 0), HttpStatus.OK);
    }

    @GetMapping("/getProjectsByProjectId")
    public ResponseEntity<?> getProjectByProjectId(@RequestParam String systemGenerateProjectId, @RequestParam String userId, @RequestParam String type) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iProjectCreationService.getProjectByProjectCode(systemGenerateProjectId, userId, type), 0), HttpStatus.OK);
    }

    @PostMapping("/CreateProjectinvites")
    public ResponseEntity<?> saveProjectInvites(@RequestBody ProjectInviteListRequest inviteRequest) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iProjectCreationService.addUpdateProjectInvite(inviteRequest), 0), HttpStatus.OK);
    }

    @GetMapping("/getProjectInvite")
    public ResponseEntity<?> getProjectInvite(@RequestParam String systemGenerateProjectId) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iProjectCreationService.getProjectInvitesByProjectCode(systemGenerateProjectId), 0), HttpStatus.OK);
    }

    @GetMapping("/getAllProjectInvite")
    public ResponseEntity<?> getAllProjectInvite() throws Exception{
    	return new ResponseEntity<>(new EntityResponse(iProjectCreationService.getAllProjectInvites(), 0), HttpStatus.OK);
    }

    @PutMapping("/changeProjectInviteStatus")
    public ResponseEntity<?> changeProjectInviteStatus(@RequestParam Long id) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iProjectCreationService.changeProjectInviteListStatus(id), 0), HttpStatus.OK);
    }

    @DeleteMapping("/removeProjectDocument")
    public ResponseEntity<?> removeProjectDocument(@RequestParam Long documentId) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iProjectCreationService.deleteProjectDocument(documentId), 0), HttpStatus.OK);
    }

    @GetMapping("/getProjectMilestone")
    public ResponseEntity<?> getProjectMilestone(@RequestParam String projectCode) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iProjectCreationService.getProjectMileStone(projectCode), 0), HttpStatus.OK);
    }

    @PostMapping("/saveProjectBid")
    public ResponseEntity<?> saveProjectBid(@RequestBody ProjectBidPostRequest postRequest) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iProjectCreationService.saveProjectBid(postRequest), 0), HttpStatus.OK);
    }

    @PutMapping("/updateProjectBid")
    public ResponseEntity<?> updateProjectBid(@RequestBody ProjectBidPostRequest postRequest) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iProjectCreationService.updateProjectBid(postRequest), 0), HttpStatus.OK);
    }

    @PostMapping("/projectBidDocument")
    public ResponseEntity<?> updateProjectBidDocument(@RequestBody List<BidDocumentRequestDto> bidDocumentRequestDto) {
        try {
            if(CollectionUtils.isEmpty(bidDocumentRequestDto)){
                return new ResponseEntity<>(new CustomResponseMessage("please add at least one request", -1), HttpStatus.NOT_ACCEPTABLE);
            }
            for (BidDocumentRequestDto documentRequestDto : bidDocumentRequestDto) {
                iProjectCreationService.saveUpdateBidDocument(documentRequestDto.getId(),documentRequestDto.getDocumentStatus(),documentRequestDto.getScore());
            }

            return new ResponseEntity<>(new EntityResponse("Successfully updated All documents", 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/saveProviderNDA")
    public ResponseEntity<?> saveProviderNDA(@RequestBody ProviderNdaDocRequest ndaRequest) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iProjectCreationService.saveProviderNdaDoc(ndaRequest), 0), HttpStatus.OK);
    }

    @PostMapping("/appproveProjectNda")
    public ResponseEntity<?> approveProjectNda(@RequestParam String systemGeneratedProjectId, @RequestParam String userId,
                                               @RequestParam String ndaComment) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iProjectCreationService.approveProjectNda(systemGeneratedProjectId, userId, ndaComment), 0), HttpStatus.OK);
    }

    @PostMapping("/rejectProjectNda")
    public ResponseEntity<?> rejectProjectNda(@RequestParam String systemGeneratedProjectId, @RequestParam String userId,
                                              @RequestParam String ndaComment) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iProjectCreationService.rejectProjectNda(systemGeneratedProjectId, userId, ndaComment), 0), HttpStatus.OK);
    }

    @GetMapping("/getProjetBidsByProjectId")
    public ResponseEntity<?> getProjetBidsByProjectId(@RequestParam String systemGeneratedProjectId,
                                                      @RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "size", required = false) Integer size) throws Exception {
    	Pageable pageable = PageRequest.of(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
        return new ResponseEntity<>(new EntityResponse(iProjectCreationService.getProjetBidsByProjectId(systemGeneratedProjectId, pageable), 0), HttpStatus.OK);
    }

    @PutMapping("/changeBidProposalStatus")
    public ResponseEntity<?> changeBidProposalStatus(@RequestParam String systemGeneratedProjectId, @RequestParam List<String> userIds, @RequestParam String proposalStatus) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iProjectCreationService.changeBidProposalStatus(systemGeneratedProjectId, userIds, proposalStatus), 0), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBidDocument")
    public ResponseEntity<?> deleteBidDocument(@RequestParam Long bidId, @RequestParam Long documentId) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iProjectCreationService.deleteBidDocument(bidId, documentId), 0), HttpStatus.OK);
    }

    @GetMapping("/getProjectsByBid")
    public ResponseEntity<?> getProjectById(@RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "size", required = false) Integer size,
                                            @RequestParam String providerId) throws Exception {
    	Pageable pageable = PageRequest.of(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(30));
        return new ResponseEntity<>(new EntityResponse(iProjectCreationService.getProjectsBidId(providerId, pageable), 0), HttpStatus.OK);
    }

    @GetMapping("/getAllProjectsDetails")
    public ResponseEntity<?> getAllProjects(@RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "size", required = false) Integer size,
                                            @RequestParam Long sort, @RequestParam String providerId) throws Exception {
    	Pageable pageable = PageRequest.of(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
        return new ResponseEntity<>(new EntityResponse(iProjectCreationService.getAllProjectsDetails(pageable, sort, providerId), 0), HttpStatus.OK);
    }


    @GetMapping("/getProjectsByStatus")
    public ResponseEntity<?> getProjectsByStatus(@RequestParam(name = "projectStatus")  String projectStatus) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iProjectCreationService.getProjectsByStatus(projectStatus), 0),
                HttpStatus.OK);
    }

    // To update project status by project code
    @PutMapping("/updateProjectstatus")
    public ResponseEntity<?> updateProjectstatus(@RequestParam(name = "projectCode")  String projectCode,@RequestParam(name = "projectStatus")  String projectStatus) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iProjectCreationService.updateProjectstatus(projectCode,projectStatus), 0),HttpStatus.OK);
    }

    // To publish and unpublish Project
    @PutMapping("/marketUpdate")
    public ResponseEntity<?> marketUpdate(@RequestParam(name = "projectCode")  String projectCode,@RequestParam(name = "publish")  Boolean isPublish) {

        try {
            return new ResponseEntity<>(new EntityResponse(iProjectCreationService.marketUpdate(projectCode,isPublish), 0),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
