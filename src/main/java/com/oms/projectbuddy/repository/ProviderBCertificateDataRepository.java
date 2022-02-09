package com.oms.projectbuddy.repository;

import java.util.List;

import io.micronaut.data.jpa.repository.JpaRepository;

import com.oms.projectbuddy.model.ProviderBCertificateData;

public interface ProviderBCertificateDataRepository extends JpaRepository<ProviderBCertificateData, Long> {
    boolean existsByUserId(String userId);

    ProviderBCertificateData findByUserId(String userId);

    boolean existsByUserIdAndCertificateId(String userId, Long businessCertificateId);

    ProviderBCertificateData findByUserIdAndCertificateId(String userId, Long businessCertificateId);
    
    List<ProviderBCertificateData> findAllByUserId(String userId);
    
}
