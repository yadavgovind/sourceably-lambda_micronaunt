package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.ProjectInviteList;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectInviteListRepository extends JpaRepository<ProjectInviteList,Long> {

    Boolean existsByProjectCode(String projectCode);

    ProjectInviteList findByProjectCode(String projectCode);

    List<ProjectInviteList>  findByCompanyId(String companyId);

}
