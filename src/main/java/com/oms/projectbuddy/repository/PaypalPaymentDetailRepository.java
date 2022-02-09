package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.PaypalPaymentDetail;
import io.micronaut.data.jpa.repository.JpaRepository;

public interface PaypalPaymentDetailRepository extends JpaRepository<PaypalPaymentDetail, Long> {
}
