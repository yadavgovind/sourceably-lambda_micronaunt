package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.MembershipPlanSelection;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Repository;

@Repository
public interface MembershipPlanSelectionRepository extends JpaRepository<MembershipPlanSelection,Long> {

    Boolean existsByUserId(String userId);

    Boolean existsByUserIdAndPlanStatus(String userId, Boolean b);

    MembershipPlanSelection findByUserIdAndPlanStatus(String userId, Boolean b);

    Boolean existsByUserIdAndIsDeleted(String userId, Boolean b);

    MembershipPlanSelection findByUserIdAndIsDeleted(String userId, Boolean b);

    MembershipPlanSelection findByUserId(String userId);



}
