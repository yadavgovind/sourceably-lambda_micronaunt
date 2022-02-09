package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.ProjectMileStoneList;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProjectMilestoneRepository extends JpaRepository<ProjectMileStoneList, Long> {

	boolean existsByProjectId(String projectId);

	List<ProjectMileStoneList> findAllByDateBetween(LocalDate startDate, LocalDate currentDate);

	// @Query(value="select * from pb_project_milestone_list where milestone_type=?
	// and date=?",nativeQuery = true)
	ProjectMileStoneList findByProjectId(String projectId);
	List<ProjectMileStoneList> findAllByProjectIdIn(List<String> projectIds);
}
