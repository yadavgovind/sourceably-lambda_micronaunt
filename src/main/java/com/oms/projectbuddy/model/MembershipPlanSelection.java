package com.oms.projectbuddy.model;

import javax.persistence.*;

@Entity
@Table(name = "pb_membership_plan_selection")
public class MembershipPlanSelection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private String userId;
    @Column(name="membership_plan_id")
    private String membershipPlanId;
    @Column(name="plan_name")
    private String planName;
    @Column(name="plan_cost")
    private Long planCost;
    @Column(name="payment_cycle")
    private String paymentCycle;
    @Column(name="payment_mode")
    private String paymentMode;
    @Column(name="payment_transaction_id")
    private String paymentTransactionId;
    @Column(name="invoice_id")
    private String invoiceId;
    @Column(name="payment_status")
    private String paymentStatus;
    @Column(name="payment_date")
    private Long paymentDate;
    @Column(name="next_billing_date")
    private Long nextBillingDate;
    @Column(name="plan_status")
    private Boolean planStatus;
    @Column(name="trail_status")
    private Boolean trailStatus;
    @Column(name="trail_start_date")
    private Long trailStartDate;
    @Column(name="trail_expiry_date")
    private Long trailExpiryDate;
    @Column(name="is_deleted")
    private Boolean isDeleted;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMembershipPlanId() {
        return membershipPlanId;
    }

    public void setMembershipPlanId(String membershipPlanId) {
        this.membershipPlanId = membershipPlanId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Long getPlanCost() {
        return planCost;
    }

    public void setPlanCost(Long planCost) {
        this.planCost = planCost;
    }

    public String getPaymentCycle() {
        return paymentCycle;
    }

    public void setPaymentCycle(String paymentCycle) {
        this.paymentCycle = paymentCycle;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentTransactionId() {
        return paymentTransactionId;
    }

    public void setPaymentTransactionId(String paymentTransactionId) {
        this.paymentTransactionId = paymentTransactionId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Long getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Long paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getNextBillingDate() {
        return nextBillingDate;
    }

    public void setNextBillingDate(Long nextBillingDate) {
        this.nextBillingDate = nextBillingDate;
    }

    public Boolean getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(Boolean planStatus) {
        this.planStatus = planStatus;
    }

    public Boolean getTrailStatus() {
        return trailStatus;
    }

    public void setTrailStatus(Boolean trailStatus) {
        this.trailStatus = trailStatus;
    }

    public Long getTrailStartDate() {
        return trailStartDate;
    }

    public void setTrailStartDate(Long trailStartDate) {
        this.trailStartDate = trailStartDate;
    }

    public Long getTrailExpiryDate() {
        return trailExpiryDate;
    }

    public void setTrailExpiryDate(Long trailExpiryDate) {
        this.trailExpiryDate = trailExpiryDate;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
