package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.CompanySales;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Repository;

@Repository
public interface CompanySalesRepository extends JpaRepository<CompanySales,Long> {

    CompanySales findByUserId(String userId);

    Boolean existsByUserId(String userId);
}
