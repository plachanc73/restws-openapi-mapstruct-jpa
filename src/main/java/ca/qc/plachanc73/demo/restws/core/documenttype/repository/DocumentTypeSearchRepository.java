package ca.qc.plachanc73.demo.restws.core.documenttype.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentTypeSearchRepository extends JpaRepository<DocumentTypeEntity, Long>, JpaSpecificationExecutor<DocumentTypeEntity> {

    long countAllByVisible(boolean visible);

    List<DocumentTypeEntity> findAllByVisible(boolean visible, Sort sort);
}
