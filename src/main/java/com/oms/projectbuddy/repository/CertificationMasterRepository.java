package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.CertificationMaster;
import com.oms.projectbuddy.utils.CertificationType;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Query;

import java.util.List;

public interface CertificationMasterRepository extends JpaRepository<CertificationMaster, Long> {

    boolean existsByCertificationName(String levelName);

    CertificationMaster findByCertificationName(String parentLevelName);

   List<CertificationMaster> findByCertificationTypeAndIsSubCategory(CertificationType certificationType, boolean b);
    List<CertificationMaster> findByCertificationType(CertificationType certificationType);

    List<CertificationMaster> findByParentIdAndAndLevel(Long parentId, String level);

    @Query(nativeQuery = true,value = "select certification_name from pb_certification_master where certification_id=? ")
    String getCertifcationNameById(Long certificateId);

    @Query(nativeQuery = true,value = "select certification_description from pb_certification_master where certification_id=? ")
    String getCertifcationNameDetailsById(Long certificateId);
}
