package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.AdditionalAttachment;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface AdditionalAttachmentRepository extends JpaRepository<AdditionalAttachment,Long> {

    List<AdditionalAttachment> findBySalesIdAndUserId(Long salesId, String userId);
//    @Transactional
//    Boolean deleteByIdAndUserId(Long id, String userId);

    Boolean existsByIdAndUserId(Long id, String userId);

    @Transactional
    void deleteAllBySalesIdAndUserId(Long salesId, String userId);
}
