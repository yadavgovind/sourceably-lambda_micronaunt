package com.oms.projectbuddy.controller;


import com.oms.projectbuddy.model.request.MembershipPlanSelectionRequest;
import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.IMembershipPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ExecuteOn(TaskExecutors.IO)
@Controller("/api")
public class MembershipPlanController {
@Autowired
private IMembershipPlanService iMembershipPlanService;

    @GetMapping("/getAllMembershipPlan")
    public ResponseEntity<?> getAllMembershipPlan(@RequestParam String isoCode) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iMembershipPlanService.getAllSubscriptionPlan(isoCode), 0), HttpStatus.OK);
    }
    
    @PostMapping("/saveMembershipPlanSelection")
    public ResponseEntity<?> saveMembershipPlan(@RequestBody MembershipPlanSelectionRequest request) throws Exception {
        	return new ResponseEntity<>(new CustomResponseMessage(iMembershipPlanService.addMembershipPlanSelection(request), 0), HttpStatus.OK);
    }
    
    @GetMapping("/getAllPlanSelection")
    public ResponseEntity<?> getAllPlanSelection() throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iMembershipPlanService.getAllPlanSelection(), 0), HttpStatus.OK);
    }
    
    @GetMapping("/getPlanSelectionById")
    public ResponseEntity<?> getPlanSelectionById(@RequestParam Long id) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iMembershipPlanService.getPlanSelectionById(id), 0), HttpStatus.OK);
    }
    
    @GetMapping("/getPlanSelectionByUserId")
    public ResponseEntity<?> getPlanSelectionByUserId(@RequestParam String userId) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iMembershipPlanService.getPlanSelectionByUserId(userId), 0), HttpStatus.OK);
    }

    @DeleteMapping("/deletePlanSelectionByUserId")
    public ResponseEntity<?> deletePlanSelectionByUserId(@RequestParam String userId) throws Exception {
    	return new ResponseEntity<>(new EntityResponse(iMembershipPlanService.deletePlanSelectionByUserId(userId), 0), HttpStatus.OK);
    }
 }


