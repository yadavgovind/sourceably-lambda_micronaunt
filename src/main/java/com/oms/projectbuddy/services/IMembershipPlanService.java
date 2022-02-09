package com.oms.projectbuddy.services;

import com.oms.projectbuddy.model.MembershipPlanSelection;
import com.oms.projectbuddy.model.request.MembershipPlanSelectionRequest;

public interface IMembershipPlanService {
    Object getAllSubscriptionPlan(String phoneCode)throws Exception;

    String addMembershipPlanSelection(MembershipPlanSelectionRequest selectionRequest) throws Exception;

    Object getAllPlanSelection() throws Exception;

    MembershipPlanSelection getPlanSelectionById(Long id) throws Exception;

    MembershipPlanSelection getPlanSelectionByUserId(String userId) throws Exception;

    String deletePlanSelectionByUserId(String userId) throws Exception;





}
