package com.oms.projectbuddy.repository;

import io.micronaut.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import io.micronaut.data.annotation.Query;
import org.springframework.data.repository.query.Param;
import io.micronaut.data.annotation.Repository;


import com.oms.projectbuddy.model.CompanyRegistration;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyRegistration, Long> {

    CompanyRegistration findByEmail(String s);

    Boolean existsByEmail(String email);

    Boolean existsByMobileNumber(String mobileNumber);

    CompanyRegistration findByUserId(String userId);

    Boolean existsByUserId(String userId);
    List<CompanyRepository> findAllByRegistrationTypeAndIsDeletedAndIsActive(String registrationType, Boolean isdeleted, Boolean isActive);

    @Modifying
    @Query(nativeQuery = true, value = " UPDATE company_registration cr SET cr.is_first_login = TRUE where cr.company_id=:companyId  ")
    void updateFirstLogin(@Param("companyId") Long companyId);

    List<CompanyRegistration> findAllByIsDeletedOrderByCreatedEpochTimeDesc(boolean b);
}
