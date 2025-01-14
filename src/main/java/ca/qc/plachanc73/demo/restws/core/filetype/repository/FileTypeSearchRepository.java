package ca.qc.plachanc73.demo.restws.core.filetype.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileTypeSearchRepository extends JpaRepository<FileTypeEntity, Long>, JpaSpecificationExecutor<FileTypeEntity> {

    long countAllByVisible(boolean visible);

    List<FileTypeEntity> findAllByVisible(boolean visible, Sort sort);
}
