package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.CompanyHighlights;
import com.oms.projectbuddy.model.ConsumerHighlights;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Repository;

@Repository
public interface ConsumerHighlightsRepository extends JpaRepository<ConsumerHighlights,Long> {

    ConsumerHighlights findByUserId(String userId);
}
