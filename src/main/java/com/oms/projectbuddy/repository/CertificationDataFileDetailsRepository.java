package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.CertificationDataFileDetails;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificationDataFileDetailsRepository extends JpaRepository<CertificationDataFileDetails, Long> {
    List<CertificationDataFileDetails> findByProviderCertificationDataId(Long providerCertificateId);

    boolean existsByFilePath(String filePath);
}
