package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.CompanyDetails;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Repository;

@Repository
public interface CompanyDetailsRepository extends JpaRepository<CompanyDetails,Long> {

    CompanyDetails findByUserId(String userId);

    //Boolean existByUserId(String userId);

}
