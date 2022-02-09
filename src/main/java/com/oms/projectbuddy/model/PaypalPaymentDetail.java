package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "paypal_payment_detail")
public class PaypalPaymentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paypalPaymentId;

    @Column(name="full_name")
    private String fullName;

    @Column(name="paypal_email")
    private String paypalEmail;

    @Column(name="paypal_plan_id")
    private String paypalPlanId;

    @Column(name="paypal_subscription_id")
    private String paypalSubscriptionId;

    @Column(name="subscription_id")
    private Long subscriptionId;

    @Column(name="transaction_id")
    private Long transactionId;

    @Column(name="payment_date")
    private Long paymentDate;

}
