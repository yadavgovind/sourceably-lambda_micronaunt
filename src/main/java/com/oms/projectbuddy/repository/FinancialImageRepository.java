package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.FinancialImageUpload;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FinancialImageRepository extends JpaRepository<FinancialImageUpload,Long> {

    List<FinancialImageUpload> findByFinancialIdAndUserId(Long financialId, String userId);

    @Transactional
    void deleteAllByFinancialIdAndUserId(Long financialId, String userId);

}
