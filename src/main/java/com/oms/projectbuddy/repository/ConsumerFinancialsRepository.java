package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.ConsumerFinancials;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Repository;

@Repository
public interface ConsumerFinancialsRepository extends JpaRepository<ConsumerFinancials,Long> {

   ConsumerFinancials findByUserId(String userId);

   Boolean existsByUserId(String userId);
}
