package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.SkillMatrixCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillMatrixCategoryRepository extends JpaRepository<SkillMatrixCategory, String> {
    boolean existsBySkillMatrixName(String categoryName);

    boolean existsBySkillMatrixId(String skillMatrixId);

    SkillMatrixCategory findByskillMatrixId(String skillMatrixId);

    SkillMatrixCategory findBySkillMatrixName(String parentcategoryName);

    Page<SkillMatrixCategory> findByIsSubCategory(boolean b, Pageable pageable);

    Page<SkillMatrixCategory> findByParent(SkillMatrixCategory parentCateogry, Pageable pageable);

    Page<SkillMatrixCategory> findByParentAndIsSubCategory(SkillMatrixCategory parent, boolean b, Pageable pageable);

    List<SkillMatrixCategory> findByLevel(String level);

    List<SkillMatrixCategory> findByLevelAndLevel1(String level, String skillMatrixId);

    List<SkillMatrixCategory> findByLevelAndLevel2(String level, String skillMatrixId);

    List<SkillMatrixCategory> findByLevelAndLevel3(String level, String skillMatrixId);

    List<SkillMatrixCategory> findByLevelAndLevel4(String level, String skillMatrixId);

    List<SkillMatrixCategory> findByLevelAndLevel5(String level, String skillMatrixId);

    List<SkillMatrixCategory> findByLevelAndLevel6(String level, String skillMatrixId);

    List<SkillMatrixCategory> findByLevelAndLevel7(String level, String skillMatrixId);

}
