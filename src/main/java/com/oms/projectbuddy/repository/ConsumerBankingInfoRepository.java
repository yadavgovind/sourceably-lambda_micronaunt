package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.ConsumerBankingInformation;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ConsumerBankingInfoRepository extends CrudRepository<ConsumerBankingInformation,Long> {

    List<ConsumerBankingInformation> findByUserId(String userId);

    @Transactional
    void deleteAllByUserId(String userId);
}
