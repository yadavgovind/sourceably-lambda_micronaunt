package com.oms.projectbuddy.repository;

import java.util.List;

import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Repository;

import com.oms.projectbuddy.model.SoftwareEvaluation;
@Repository
public interface SoftwareEvaluationRepository extends JpaRepository<SoftwareEvaluation,Long> {

    List<SoftwareEvaluation> findBySupplierId(String supplierId);
    
}