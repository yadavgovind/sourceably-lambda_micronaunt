package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.ConsumerBusinessBasicInfo;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Repository;

@Repository
public interface ConsumerBusinessBasicInfoRepository extends JpaRepository<ConsumerBusinessBasicInfo,Long> {

    ConsumerBusinessBasicInfo findByUserId(String userId);
}
