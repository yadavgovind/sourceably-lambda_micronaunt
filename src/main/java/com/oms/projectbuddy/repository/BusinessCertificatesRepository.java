package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.BusinessCertificates;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessCertificatesRepository extends JpaRepository<BusinessCertificates, Long> {
    boolean existsByCertificateName(String certificateName);

    BusinessCertificates findByCertificateName(String parentName);

    List<BusinessCertificates> findByIsSubCategoryOrderByBusinessCertificateIdAsc(boolean b);

    List<BusinessCertificates> findByIsSubCategoryAndParentOrderByBusinessCertificateIdAsc(boolean b, BusinessCertificates businessCertificates);
}
