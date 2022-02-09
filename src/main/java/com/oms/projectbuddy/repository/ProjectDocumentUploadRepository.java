package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.ProjectDocumentUpload;
import io.micronaut.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ProjectDocumentUploadRepository extends JpaRepository<ProjectDocumentUpload,Long> {

    Boolean existsByProjectCode(String projectCode);

    List<ProjectDocumentUpload> findByProjectCode(String projectCode);

    @Transactional
    void deleteByProjectCode(String projectCode);
}
