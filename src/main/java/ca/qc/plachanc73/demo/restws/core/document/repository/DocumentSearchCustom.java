package ca.qc.plachanc73.demo.restws.core.document.repository;

import ca.qc.plachanc73.demo.restws.core.document.service.dto.DocumentSearchCriteriaDto;

import java.util.List;
import java.util.Optional;

public interface DocumentSearchCustom {

    Optional<Long> countBySearchCriteria(DocumentSearchCriteriaDto searchCriteria);

    Optional<List<DocumentEntity>> findBySearchCriteria(DocumentSearchCriteriaDto searchCriteria);
}
