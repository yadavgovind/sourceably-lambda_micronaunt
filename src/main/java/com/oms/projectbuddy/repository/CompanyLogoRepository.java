package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.CompanyLogo;
import io.micronaut.data.jpa.repository.JpaRepository;

public interface CompanyLogoRepository extends JpaRepository<CompanyLogo,Long> {

    CompanyLogo findByUserIdAndIsDeleted(String userId, Boolean b);

    Boolean existsByUserIdAndIsDeleted(String userId, Boolean b);

}
