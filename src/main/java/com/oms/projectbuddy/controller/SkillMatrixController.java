package com.oms.projectbuddy.controller;

import com.oms.projectbuddy.model.request.SkillMatrixListRequest;
import com.oms.projectbuddy.model.request.SkillMatrixUpdateRequest;
import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.ISkillMatrixService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.Optional;

@ExecuteOn(TaskExecutors.IO)
@Controller("/api")
@Api(value = "Skill Matrix Category", description = "Skill Category", tags = {"Skill Matrix Categories"})
public class SkillMatrixController {
    @Autowired
    private ISkillMatrixService iSkillMatrixService;
    private static final Logger LOG = LoggerFactory.getLogger(SkillMatrixController.class);


    @PostMapping("/uploadSkillCategoryMatrix")
    public ResponseEntity<?> uploadSkillCategoryMatrix(@RequestParam MultipartFile file) throws Exception {
        iSkillMatrixService.bukUploadSkillMatrixCategory(file);
        return new ResponseEntity<>("UploadSuccess", HttpStatus.OK);
    }

    @GetMapping("/getParentCategories")
    public ResponseEntity<?> getParentcategories(@RequestParam(value = "page", required = false) Integer page,
                                                 @RequestParam(value = "size", required = false) Integer size) {
        try {
            LOG.debug("Request added for the Details Of The Parent category");
            Pageable pageable = PageRequest.of(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
            LOG.debug("Request For Parent Sent Success");

            return new ResponseEntity<>(new EntityResponse(iSkillMatrixService.getParentCategoriesdetails(pageable), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getChildSkillCategoryBySkillId")
    public ResponseEntity<?> getChildSkillCategoryBySkillId(@RequestParam(value = "page", required = false) Integer page,
                                                            @RequestParam(value = "size", required = false) Integer size,
                                                            @RequestParam String skillCategoryId) throws Exception {

    	LOG.info("Request added for the Details Of The Child  category parent is  = " + skillCategoryId);
        Pageable pageable = PageRequest.of(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(100));
        Object dataDetails = iSkillMatrixService.getChildSkillCategoryBySkillId(skillCategoryId, pageable);
        LOG.info("Response IS Added succss to the With Details in child Details = " + skillCategoryId);
        return new ResponseEntity<>(new EntityResponse(dataDetails, 0), HttpStatus.OK);
    }

    @GetMapping("/getAllLevelDetailsBySkillMatrixId")
    public ResponseEntity<?> getAllLevelDetailsBySkilMatrix(@RequestParam String skillMatrixId) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iSkillMatrixService.getAllLevelDetailsBySkillMatrixId(skillMatrixId), 0), HttpStatus.OK);
    }

    @PostMapping("/saveProviderSkillMatrix")
    public ResponseEntity<?> saveProviderSkillMatrix(@RequestParam String userId, @RequestBody SkillMatrixListRequest skillMatrixListRequest) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iSkillMatrixService.saveSupplierSkillMatrix(skillMatrixListRequest, userId), 0), HttpStatus.OK);
    }

    @GetMapping("/getProviderSkillMatrix")
    public ResponseEntity<?> getProviderSkillMatrix(@RequestParam String userId) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iSkillMatrixService.getProviderSkillMatrix(userId), 0), HttpStatus.OK);
    }
    @PutMapping("/updateProviderSkillMatrix")
    public ResponseEntity<?> updateProviderSkillMatrix(@RequestBody SkillMatrixUpdateRequest matrixUpdateRequest) throws Exception {
    	return new ResponseEntity<>(new CustomResponseMessage(iSkillMatrixService.updateSupplierSkillMatrix(matrixUpdateRequest), 0), HttpStatus.OK);
    }

}
