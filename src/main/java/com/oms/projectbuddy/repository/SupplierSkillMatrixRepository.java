package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.SupplierSkillMatrix;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierSkillMatrixRepository extends JpaRepository<SupplierSkillMatrix,Long> {

    Boolean existsByUserIdAndIsActive(String userId, boolean b);

    SupplierSkillMatrix findByUserIdAndSkillMatrixId(String userId, String skillMatrixId);

    List<SupplierSkillMatrix> findByUserIdAndIsActive(String userId, boolean b);

}
