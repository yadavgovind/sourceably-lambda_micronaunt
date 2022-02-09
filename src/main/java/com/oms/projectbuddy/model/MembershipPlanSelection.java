package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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

}
