package com.oms.projectbuddy.services.impl;

import com.oms.projectbuddy.exception.SourceablyCustomeException;
import com.oms.projectbuddy.model.CompanyRegistration;
import com.oms.projectbuddy.model.MembershipPlanDisplay;
import com.oms.projectbuddy.model.MembershipPlanSelection;
import com.oms.projectbuddy.model.request.MembershipPlanSelectionRequest;
import com.oms.projectbuddy.repository.MembershipPlanDisplayRepository;
import com.oms.projectbuddy.repository.MembershipPlanSelectionRepository;
import com.oms.projectbuddy.services.IMembershipPlanService;
import com.oms.projectbuddy.utility.EmailTemplates;
import com.oms.projectbuddy.utility.SmsEmailIntegration;
import com.oms.projectbuddy.utils.ExceptionUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MembershipPlanService implements IMembershipPlanService {
    @Autowired
    private MembershipPlanDisplayRepository membershipPlanDisplayRepository;
    @Autowired
    private MembershipPlanSelectionRepository membershipPlanSelectionRepository;

    @Autowired
    private SmsEmailIntegration smsEmailIntegration;

    @Override
    public Object getAllSubscriptionPlan(String isoCode) {
    	List<MembershipPlanDisplay> planDisplayList = membershipPlanDisplayRepository.findAll();
    	ExceptionUtils.verifyDataNotExistThenThrowException(planDisplayList);
    	if (isoCode.equalsIgnoreCase("91")) {
            for (MembershipPlanDisplay plan : planDisplayList) {
                plan.setMonthlyCost(plan.getMonthlyCostInr());
                plan.setYearlyCost(plan.getYearlyCostInr());
               // plan.setMonthlyCostInr(null);
               // plan.setYearlyCostInr(null);
                plan.setCurrency("INR");
            }
            return planDisplayList;
        } else {
            for (MembershipPlanDisplay plan : planDisplayList) {
              //  plan.setMonthlyCostInr(null);
              //  plan.setYearlyCostInr(null);
                plan.setCurrency("DOLLAR");
            }
            return planDisplayList;
        }
    }

    @Override
    public String addMembershipPlanSelection(MembershipPlanSelectionRequest request) throws Exception{
        try {/*
            if(membershipPlanSelectionRepository.existsByUserId(request.getUserId())){
                MembershipPlanSelection selection=membershipPlanSelectionRepository.findByUserId(request.getUserId());
                selection.setPlanStatus(false);
            }*/
            MembershipPlanSelection membershipPlanSelection = new MembershipPlanSelection();
            membershipPlanSelection.setMembershipPlanId(request.getMembershipPlanId());
            membershipPlanSelection.setPlanCost(request.getPlanCost());
            membershipPlanSelection.setPlanName(request.getPlanName());
            membershipPlanSelection.setPaymentCycle(request.getPaymentCycle());
            membershipPlanSelection.setPaymentStatus("PENDING");
            membershipPlanSelection.setPaymentMode(request.getPaymentMode());
            membershipPlanSelection.setPlanStatus(false);
            membershipPlanSelection.setTrailStatus(true);
            membershipPlanSelection.setTrailStartDate(Instant.now().toEpochMilli());
            membershipPlanSelection.setTrailExpiryDate(Instant.now().plus(90, ChronoUnit.DAYS).toEpochMilli());
            membershipPlanSelection.setUserId(request.getUserId());
            membershipPlanSelection.setPaymentDate(membershipPlanSelection.getTrailExpiryDate()+TimeUnit.DAYS.toMillis(1));
            Long daysInMilli;
            if(request.getPaymentCycle().equalsIgnoreCase("1year")){
            daysInMilli= TimeUnit.DAYS.toMillis(365);}
            if(request.getPaymentCycle().equalsIgnoreCase("1month")){
                daysInMilli= TimeUnit.DAYS.toMillis(30); }
            if(request.getPaymentCycle().equalsIgnoreCase("3month")){
                daysInMilli= TimeUnit.DAYS.toMillis(90); }
            if(request.getPaymentCycle().equalsIgnoreCase("6month")){
                daysInMilli= TimeUnit.DAYS.toMillis(180); }
            else{
                daysInMilli= TimeUnit.DAYS.toMillis(365);}

            Long nextBillingEpochTime = membershipPlanSelection.getPaymentDate()+daysInMilli;
            membershipPlanSelection.setNextBillingDate(nextBillingEpochTime);
            MembershipPlanSelection selection = membershipPlanSelectionRepository.save(membershipPlanSelection);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy");
            String dateAppend = dateFormat.format(new Date());
            String formatId = String.format("%05d", selection.getId());
            String id = "INV" + dateAppend + "-" + formatId;
            selection.setInvoiceId(id);
            membershipPlanSelectionRepository.save(selection);
            EmailTemplates emailTemplates=new EmailTemplates();
            CompanyRegistration registration=new CompanyRegistration();
            String message=emailTemplates.paymentTemplate(selection.getInvoiceId(),selection.getPaymentDate(),selection.getPaymentCycle(),selection.getUserId(),registration.getAddressLine1());
            smsEmailIntegration.sendEmail(registration.getEmail().toLowerCase(),"About Payment",message);
            return "Saved Successfully";
        } catch (Exception e) {
        	throw new SourceablyCustomeException("MemberShip plan not saved", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    @Override
    public Object getAllPlanSelection() throws Exception{
    	List<MembershipPlanSelection> allmembershipPlans = membershipPlanSelectionRepository.findAll();
    	ExceptionUtils.verifyDataNotExistThenThrowException(allmembershipPlans);
    	return allmembershipPlans;
    }

    @Override
    public MembershipPlanSelection getPlanSelectionById(Long id) throws Exception {
    	return membershipPlanSelectionRepository.findById(id).orElseThrow(()-> new SourceablyCustomeException("Membership id not found", HttpStatus.UNPROCESSABLE_ENTITY));
    }
    
    @Override
    public MembershipPlanSelection getPlanSelectionByUserId(String userId) throws Exception{
    	
    	MembershipPlanSelection membershipPlanSelection = membershipPlanSelectionRepository.findByUserIdAndIsDeleted(userId,false);
    	ExceptionUtils.verifyDataNotExistThenThrowException(membershipPlanSelection);
    	return membershipPlanSelection;
    }

    @Override
    public String deletePlanSelectionByUserId(String userId) throws Exception {
        if(membershipPlanSelectionRepository.existsByUserIdAndIsDeleted(userId,false)){
            MembershipPlanSelection planSelection=membershipPlanSelectionRepository.findByUserIdAndPlanStatus(userId,false);;
            planSelection.setIsDeleted(true);
            return "deleted";
        }else{
        	throw new SourceablyCustomeException("User not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
