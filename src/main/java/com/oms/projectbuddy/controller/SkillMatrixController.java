package com.oms.projectbuddy.controller;

import com.oms.projectbuddy.model.request.SkillMatrixListRequest;
import com.oms.projectbuddy.model.request.SkillMatrixUpdateRequest;
import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.ISkillMatrixService;

import io.micronaut.data.model.Pageable;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.util.Optional;

@ExecuteOn(TaskExecutors.IO)
@Controller("/api")
//@Api(value = "Skill Matrix Category", description = "Skill Category", tags = {"Skill Matrix Categories"})
public class SkillMatrixController {
    @Inject
    private ISkillMatrixService iSkillMatrixService;
    private static final Logger LOG = LoggerFactory.getLogger(SkillMatrixController.class);


    @Post("/uploadSkillCategoryMatrix")
    public Object uploadSkillCategoryMatrix( String file) throws Exception {
        iSkillMatrixService.bukUploadSkillMatrixCategory(file);
        return "UploadSuccess";
    }

    @Get("/getParentCategories")
    public Object getParentcategories(  Integer page,
                                                  Integer size) {
        try {
            LOG.debug("Request added for the Details Of The Parent category");
            Pageable pageable = Pageable.from(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
            LOG.debug("Request For Parent Sent Success");

            return new EntityResponse(iSkillMatrixService.getParentCategoriesdetails(pageable), 0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }
    }

    @Get("/getChildSkillCategoryBySkillId")
    public Object getChildSkillCategoryBySkillId(  Integer page,
                                                              Integer size,
                                                             String skillCategoryId) throws Exception {

    	LOG.info("Request added for the Details Of The Child  category parent is  = " + skillCategoryId);
        Pageable pageable = Pageable.from(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(100));
        Object dataDetails = iSkillMatrixService.getChildSkillCategoryBySkillId(skillCategoryId, pageable);
        LOG.info("Response IS Added succss to the With Details in child Details = " + skillCategoryId);
        return new EntityResponse(dataDetails, 0);
    }

    @Get("/getAllLevelDetailsBySkillMatrixId")
    public Object getAllLevelDetailsBySkilMatrix( String skillMatrixId) throws Exception {
    	return new EntityResponse(iSkillMatrixService.getAllLevelDetailsBySkillMatrixId(skillMatrixId), 0);
    }

    @Post("/saveProviderSkillMatrix")
    public Object saveProviderSkillMatrix( String userId, @Body SkillMatrixListRequest skillMatrixListRequest) throws Exception {
    	return new EntityResponse(iSkillMatrixService.saveSupplierSkillMatrix(skillMatrixListRequest, userId), 0);
    }

    @Get("/getProviderSkillMatrix")
    public Object getProviderSkillMatrix( String userId) throws Exception {
    	return new EntityResponse(iSkillMatrixService.getProviderSkillMatrix(userId), 0);
    }
    @Put("/updateProviderSkillMatrix")
    public Object updateProviderSkillMatrix(@Body SkillMatrixUpdateRequest matrixUpdateRequest) throws Exception {
    	return new CustomResponseMessage(iSkillMatrixService.updateSupplierSkillMatrix(matrixUpdateRequest), 0);
    }

}
