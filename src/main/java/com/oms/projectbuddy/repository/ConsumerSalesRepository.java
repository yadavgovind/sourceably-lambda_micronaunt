package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.ConsumerSales;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Repository;

@Repository
public interface ConsumerSalesRepository extends JpaRepository<ConsumerSales,Long> {

  ConsumerSales  findByUserId(String userId);

  Boolean existsByUserId(String userId);
}
