package ca.qc.plachanc73.demo.restws.core.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentSearchRepository
        extends JpaRepository<DocumentEntity, Long>, JpaSpecificationExecutor<DocumentEntity>, DocumentSearchCustom {

}
