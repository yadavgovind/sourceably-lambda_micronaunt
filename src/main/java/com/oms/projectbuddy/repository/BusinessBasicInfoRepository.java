package com.oms.projectbuddy.repository;

import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Repository;

import com.oms.projectbuddy.model.BusinessBasicInfo;

@Repository
public interface BusinessBasicInfoRepository extends JpaRepository<BusinessBasicInfo,Long>{
	
	BusinessBasicInfo findByUserId(String userId);


}
