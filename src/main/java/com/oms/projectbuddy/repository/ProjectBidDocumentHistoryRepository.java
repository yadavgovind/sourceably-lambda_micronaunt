package com.oms.projectbuddy.repository;

import java.util.List;

import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Repository;

import com.oms.projectbuddy.model.ProjectBidDocumentHistory;
@Repository
public interface ProjectBidDocumentHistoryRepository extends JpaRepository<ProjectBidDocumentHistory,Long> {

    List<ProjectBidDocumentHistory> findBySupplierId(String supplierId);

    List<ProjectBidDocumentHistory> findByProjectIdAndSupplierIdAndConsumerId(String projectId, String supplierId, String consumerId);

}
