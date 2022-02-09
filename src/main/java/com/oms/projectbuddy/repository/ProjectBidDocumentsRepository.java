package com.oms.projectbuddy.repository;

import com.oms.projectbuddy.model.ProjectBidDocuments;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.annotation.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ProjectBidDocumentsRepository extends JpaRepository<ProjectBidDocuments,Long> {


    Boolean existsByDocumentId(Long documentId);

    ProjectBidDocuments findByDocumentId(Long documentId);

    List<ProjectBidDocuments> findByBidId(Long bidId);

    Boolean existsByBidIdAndDocumentId(Long bidId, Long documentId);

    /*@Query(value = "delete from  pb_project_bid_documents WHERE bid_id=? And document_id=?",nativeQuery = true)
    Boolean deleteByBidIdAndDocumentId(Long bidId,Long documentId);
*/
    @Transactional
    void deleteByBidIdAndDocumentId(Long bidId, Long documentId);

    @Transactional
    void deleteByBidId(Long bidId);
}
