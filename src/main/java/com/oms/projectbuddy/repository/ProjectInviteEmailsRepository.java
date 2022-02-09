package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.ProjectInviteEmails;
import io.micronaut.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ProjectInviteEmailsRepository extends JpaRepository<ProjectInviteEmails,Long> {

    List<ProjectInviteEmails> findByProjectCode(String projectCode);

    Boolean existsByProjectCode(String projectCode);

    @Transactional
    void deleteAllByProjectCode(String projectCode);
}
