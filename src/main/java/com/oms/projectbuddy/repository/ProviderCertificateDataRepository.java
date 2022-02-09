package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.ProviderCertificateData;
import io.micronaut.data.jpa.repository.JpaRepository;
 import io.micronaut.data.annotation.Query;

import java.time.LocalDate;
import java.util.List;

public interface ProviderCertificateDataRepository extends JpaRepository<ProviderCertificateData, Long> {
    boolean existsByCertificateIdAndUserId(Long certificateId, String userId);

    ProviderCertificateData findByCertificateIdAndUserId(Long certificateId, String userId);

    List<ProviderCertificateData> findByUserId(String userID);
    List<ProviderCertificateData> findByCreatedEpochTimeBetween(LocalDate startDate, LocalDate endDate);
    @Query(value = "select  evidence_file_score+comment_score+comment_score as score from pb_provider_certificate_data where certificate_id=? and user_id=? limit 1", nativeQuery = true)
    Double getScoreByUserAndCertificateID(Long certificationId, String userId);

    @Query(value = "select  evidence_file_score+comment_score+comment_score as score from pb_provider_certificate_data where user_id=? limit 1", nativeQuery = true)
    Double getScoreByUser(String userId);

    @Query(value = "select count(*) from pb_certification_master where user_id=? group by certificate_id", nativeQuery = true)
    Double getCountOfCertificateByUserId(String userId);
}
