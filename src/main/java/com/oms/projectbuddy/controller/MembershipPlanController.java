package com.oms.projectbuddy.controller;


import com.oms.projectbuddy.model.request.MembershipPlanSelectionRequest;
import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.IMembershipPlanService;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;


@ExecuteOn(TaskExecutors.IO)
@Controller("/api")
public class MembershipPlanController {
@Inject
private IMembershipPlanService iMembershipPlanService;

    @Get("/getAllMembershipPlan")
    public Object getAllMembershipPlan( String isoCode) throws Exception {
    	return new EntityResponse(iMembershipPlanService.getAllSubscriptionPlan(isoCode), 0);
    }
    
    @Post("/saveMembershipPlanSelection")
    public Object saveMembershipPlan(@Body MembershipPlanSelectionRequest request) throws Exception {
        	return new CustomResponseMessage(iMembershipPlanService.addMembershipPlanSelection(request), 0);
    }
    
    @Get("/getAllPlanSelection")
    public Object getAllPlanSelection() throws Exception {
    	return new EntityResponse(iMembershipPlanService.getAllPlanSelection(), 0);
    }
    
    @Get("/getPlanSelectionById")
    public Object getPlanSelectionById( Long id) throws Exception {
    	return new EntityResponse(iMembershipPlanService.getPlanSelectionById(id), 0);
    }
    
    @Get("/getPlanSelectionByUserId")
    public Object getPlanSelectionByUserId( String userId) throws Exception {
    	return new EntityResponse(iMembershipPlanService.getPlanSelectionByUserId(userId), 0);
    }

    @Delete("/deletePlanSelectionByUserId")
    public Object deletePlanSelectionByUserId( String userId) throws Exception {
    	return new EntityResponse(iMembershipPlanService.deletePlanSelectionByUserId(userId), 0);
    }
 }


