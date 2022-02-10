package com.oms.projectbuddy.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    public Long getPaypalPaymentId() {
        return paypalPaymentId;
    }

    public void setPaypalPaymentId(Long paypalPaymentId) {
        this.paypalPaymentId = paypalPaymentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }

    public void setPaypalEmail(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }

    public String getPaypalPlanId() {
        return paypalPlanId;
    }

    public void setPaypalPlanId(String paypalPlanId) {
        this.paypalPlanId = paypalPlanId;
    }

    public String getPaypalSubscriptionId() {
        return paypalSubscriptionId;
    }

    public void setPaypalSubscriptionId(String paypalSubscriptionId) {
        this.paypalSubscriptionId = paypalSubscriptionId;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Long paymentDate) {
        this.paymentDate = paymentDate;
    }
}
