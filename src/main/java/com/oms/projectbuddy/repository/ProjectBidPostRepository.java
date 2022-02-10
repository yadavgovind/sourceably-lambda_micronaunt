package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.ProjectBidPost;
  import io.micronaut.data.annotation.Query;
import io.micronaut.data.model.Page;

import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.model.Pageable;


import java.time.LocalDate;
import java.util.List;

public interface ProjectBidPostRepository extends JpaRepository<ProjectBidPost, Long> {

	Boolean existsByProviderIdAndSystemGeneratedProjectId(String userId, String systemGeneratedProjectId);

	List<ProjectBidPost> findAllByProjectBidDateBetween(LocalDate startDate, LocalDate currentDate);

	ProjectBidPost findByProviderIdAndSystemGeneratedProjectId(String userId, String systemGeneratedProjectId);
	List<ProjectBidPost> findBySystemGeneratedProjectIdIn(List<String> systemGeneratedProjectId);
	Boolean existsByBidId(Long bidId);

	ProjectBidPost findByBidId(Long bidId);

	Boolean existsBySystemGeneratedProjectId(String systemGeneratedProjectId);

	Page<ProjectBidPost> findBySystemGeneratedProjectId(String systemGeneratedProjectId, Pageable pageable);

	List<ProjectBidPost> findBySystemGeneratedProjectId(String systemGeneratedProjectId);

	@Query(value = "select count(*) from sbly_project_bid_post where system_generated_project_id= ? and bid_id is NOT NULL", nativeQuery = true)
	Long countProjectBid(String systemGeneratedProjectId);

	boolean existsByProviderId(String providerId);

	@Query(value = "select * from sbly_project_bid_post where provider_id=? ", nativeQuery = true)
	List<ProjectBidPost> getByProviderId(String providerId);

	List<ProjectBidPost> findAllByProjectBidDateBetweenAndConsumerId(LocalDate startDate, LocalDate currentDate, String providerId);

	Page<ProjectBidPost> findByProviderId(String providerId, Pageable pageable);

	@Query(value = "select * from sbly_project_bid_post where system_generated_project_id=:projectCode and provider_id=:providerId limit 1", nativeQuery = true)
	ProjectBidPost getByProviderIdAndSystemGeneratedProjectId(String providerId, String projectCode);

	Page<ProjectBidPost> findByProviderIdOrderByIdDesc(String providerId, Pageable pageable);
}
