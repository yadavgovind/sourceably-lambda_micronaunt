package com.oms.projectbuddy.repository;

import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Repository;

import com.oms.projectbuddy.model.BankingInformation;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BankingInfoRepository extends JpaRepository<BankingInformation,Long>{

   // Boolean existByUserId(String userId);
    List<BankingInformation> findByUserId(String userId);

    @Transactional
    void deleteAllByUserId(String userId);

}
