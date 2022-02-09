package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.CompanyFinancials;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Repository;

@Repository
public interface CompanyFinancialsRepository extends JpaRepository<CompanyFinancials,Long> {
    CompanyFinancials findByUserId(String userId);

    Boolean existsByUserId(String userId);
}
