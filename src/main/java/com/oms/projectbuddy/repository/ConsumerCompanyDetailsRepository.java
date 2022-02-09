package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.ConsumerDetails;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Repository;

@Repository
public interface ConsumerCompanyDetailsRepository extends JpaRepository<ConsumerDetails, Long> {

    ConsumerDetails findByUserId(String userId);
}
