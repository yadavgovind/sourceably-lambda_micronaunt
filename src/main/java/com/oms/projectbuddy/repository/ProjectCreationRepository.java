package com.oms.projectbuddy.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.micronaut.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import io.micronaut.data.annotation.Query;

import com.oms.projectbuddy.model.ProjectCreation;
import com.oms.projectbuddy.utils.ProjectStatus;
import org.springframework.data.repository.query.Param;

public interface ProjectCreationRepository extends JpaRepository<ProjectCreation,Long> {

    Boolean existsByProjectNumberAndCompanyId(String projectNumer, String companyId);

    Boolean existsByProjectCodeAndIsDeleted(String projectCode, Boolean b);

    Boolean existsByCompanyIdAndIsDeleted(String companyId, Boolean b);

    ProjectCreation findByProjectCodeAndIsDeleted(String ProjectCode, Boolean b);

    List<ProjectCreation> findByCompanyId(String comanyId);

    Page<ProjectCreation> findAllByIsDeletedAndIsActiveAndExpiryDateGreaterThanEqualOrderByCreatedEpochTimeDesc(Boolean b, Boolean a, LocalDate currentDate, Pageable pageable);

    Page<ProjectCreation> findAllByIsDeletedAndIsActiveAndMarketplaceAndExpiryDateGreaterThanEqualOrderByCreatedEpochTimeDesc(Boolean b, Boolean a, Boolean marketPlace, LocalDate currentDate, Pageable pageable);

    @Query(value = "SELECT * FROM  sbly_consumer_project_creation where expiry_date >= current_date() and is_marketplace =true and is_active=true and is_marketplace=true  and is_deleted=false order by expiry_date", nativeQuery = true)
    Page<ProjectCreation> findAllSortByEndDate(Pageable pageable);

    @Query(value = "select * from  sbly_consumer_project_creation where project_team_size=? AND budget_range=? and expiry_date >= current_date()  and is_active=true and   is_marketplace=true and  is_deleted=false order by created_epoch_time desc", nativeQuery = true)
    Page<ProjectCreation> findAllByBudgetAndTeamSize(String teamSize, String budgetRange, Pageable pageable);

    @Query(value = "select * from  sbly_consumer_project_creation where budget_range=? and is_active=true and expiry_date >= current_date() and is_marketplace=true  and is_deleted=false order by created_epoch_time  desc", nativeQuery = true)
    Page<ProjectCreation> findAllByBudget(String budgetRange, Pageable pageable);

    @Query(value = "select * from  sbly_consumer_project_creation where project_team_size=? and is_active=true and expiry_date >= current_date() and is_marketplace=true and is_deleted=false order by created_epoch_time  desc", nativeQuery = true)
    Page<ProjectCreation> findAllByTeamSize(String teamSize, Pageable pageable);

    Boolean existsByProjectCode(String projectCode);

    @Query(value = "select count(*)  from  sbly_consumer_project_creation where is_deleted=? and is_active=true", nativeQuery = true)
    Long countTotalProject(Boolean b);

    @Query(value = "select pp.* from  sbly_consumer_project_creation as pp inner join  company_registration as cr "+
            " on pp.company_id=cr.user_id where cr.company_name like %:searchText% AND pp.is_active=true"+
            " or pp.project_title like %:searchText% AND pp.is_active=true and expiry_date >= current_date() and is_marketplace=true  or  pp.short_description like %:searchText% AND pp.is_active=true  and   is_marketplace=true order by created_epoch_time  desc", nativeQuery = true)
    Page<ProjectCreation> searchByProjectTitle(String searchText, Pageable pageable);

    //List<ProjectCreation> findByProjectType(String projectType);

    ProjectCreation findByProjectIdAndIsDeleted(Long projectId, Boolean b);

    Page<ProjectCreation> findAllByProjectSubCategoryId(String id, Pageable pageable);

    //@Query(value = "SELECT * FROM  sbly_consumer_project_creation where project_sub_category_id IN (:subCategoryId) and is_active=:isActive order by created_epoch_time  desc", nativeQuery = true)
    //Page<ProjectCreation> getProjectSubCategoryId(List<String> subCategoryId, Boolean isActive,Pageable pageable);

    @Query(value = "SELECT * FROM  sbly_consumer_project_creation where project_sub_category_id IN (:subCategoryId) and project_sub_category_Level2_id IN (:subCategory2Id) and project_sub_category_Level3_id IN (:subCategory3Id) and is_active=:isActive order by created_epoch_time  desc", nativeQuery = true)
    Page<ProjectCreation> getProjectSubCategoryId(List<String> subCategoryId, List<String> subCategory2Id, List<String> subCategory3Id, Boolean isActive, Pageable pageable);

    @Query(value = "SELECT * FROM  sbly_consumer_project_creation where project_sub_category_id IN (:subCategoryId) and project_sub_category_Level2_id IN (:subCategory2Id)  and is_active=:isActive order by created_epoch_time  desc", nativeQuery = true)
    Page<ProjectCreation> getProjectSubCategoryId2(List<String> subCategoryId, List<String> subCategory2Id, Boolean isActive, Pageable pageable);

    @Query(value = "SELECT * FROM  sbly_consumer_project_creation where project_sub_category_id IN (:subCategoryId)   and project_sub_category_Level3_id IN (:subCategory3Id) and is_active=:isActive order by created_epoch_time  desc", nativeQuery = true)
    Page<ProjectCreation> getProjectSubCategoryId3(List<String> subCategoryId, List<String> subCategory3Id, Boolean isActive, Pageable pageable);


    @Query(value = "SELECT * FROM  sbly_consumer_project_creation where project_sub_category_id IN (:subCategoryId)    and is_active=:isActive order by created_epoch_time  desc", nativeQuery = true)
    Page<ProjectCreation> getProjectSubCategoryId(List<String> subCategoryId, Boolean isActive, Pageable pageable);

    ProjectCreation findByProjectCode(String systemGeneratedProjectId);

    Page<ProjectCreation> findByCompanyIdAndIsDeletedOrderByUpdatedEpochTimeDesc(String projectCompanyId, boolean b, Pageable pageable);

    Page<ProjectCreation> findByCompanyIdAndIsDeletedOrderByIdDesc(String projectCompanyId, boolean b, Pageable pageable);

    // "select *  from  pb_consumer_project_creation where project_title like :searchText% order by created_epoch_time  desc"

    List<ProjectCreation> findAllByProjectCodeIn(List<String> projectCode);

    List<ProjectCreation> findByProjectStatusAndCompanyId(ProjectStatus projectStatus, String companyId);

    @Modifying
    @Query(nativeQuery = true,value = " update sbly_consumer_project_creation set  project_status =:projectStatus where project_code=:projectCode ")
    public void updateProjectStatus(@Param("projectCode") String projectCode, @Param("projectStatus") String projectStatus);
    @Modifying
    @Query(nativeQuery = true,value = " update sbly_consumer_project_creation set  is_marketplace =:market where project_code=:projectCode ")
    public void updateMarket(@Param("projectCode") String projectCode, @Param("market") Boolean market);

}
