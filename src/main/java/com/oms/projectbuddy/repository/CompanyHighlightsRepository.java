package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.CompanyHighlights;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Repository;

@Repository
public interface CompanyHighlightsRepository extends JpaRepository<CompanyHighlights,Long> {

    CompanyHighlights findByUserId(String userId);

}
