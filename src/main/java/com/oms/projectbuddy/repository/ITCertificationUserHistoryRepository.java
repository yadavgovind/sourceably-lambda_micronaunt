package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.ITCertificationUserHistory;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;

import javax.persistence.Column;
import java.util.List;

@Repository
public interface ITCertificationUserHistoryRepository extends JpaRepository<ITCertificationUserHistory, Long> {
    List<ITCertificationUserHistory> findByCertificateDataId(Long providerCertificateId);

    List<ITCertificationUserHistory> findByCertificateDataIdOrderByHistoryCreatedEpochTimeDesc(Long providerCertificateId);

    @Query(value = "SELECT concat(first_name,' ',middle_name,' ',last_name) as naav  FROM company_registration where user_id =?;",nativeQuery = true)
    String getUserNameById(String userID);
}
