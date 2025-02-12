package ca.qc.plachanc73.demo.restws.core.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository
        extends JpaRepository<DocumentEntity, Long>, JpaSpecificationExecutor<DocumentEntity> {

    Optional<DocumentEntity> findByDocumentNameAndVisibleIsTrue(String documentName);

    Optional<DocumentEntity> findByDocumentUUIDAndVisibleIsTrue(UUID documentUUID);
}
