package ca.qc.plachanc73.demo.restws.core.document.service;

import ca.qc.plachanc73.demo.restws.core.document.repository.DocumentEntity;
import ca.qc.plachanc73.demo.restws.core.document.repository.DocumentSearchRepository;
import ca.qc.plachanc73.demo.restws.core.document.service.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DocumentSearchService {

    private final DocumentSearchRepository documentSearchRepository;
    private final DocumentDtoMapper documentDtoMapper;

    public DocumentSearchService(DocumentSearchRepository documentSearchRepository,
            DocumentDtoMapper documentDtoMapper) {
        this.documentSearchRepository = documentSearchRepository;
        this.documentDtoMapper = documentDtoMapper;
    }

    public DocumentSearchResultDto searchDocument(DocumentSearchCriteriaDto searchCriteria) {
        Long totalResults =
                documentSearchRepository.countBySearchCriteria(searchCriteria)
                        .orElse(0L);

        List<DocumentDto> dtoResults = null;
        if (totalResults > 0L) {
            List<DocumentEntity> entityResults =
                    documentSearchRepository.findBySearchCriteria(searchCriteria)
                            .orElse(null);


            if (!CollectionUtils.isEmpty(entityResults)) {
                dtoResults = entityResults.stream()
                        .map(documentDtoMapper::documentEntityToDocumentDto)
                        .collect(Collectors.toList());
            }
        }

        return DocumentSearchResultDto.builder()
                .criteria(searchCriteria)
                .results(dtoResults)
                .totalResults(totalResults)
                .build();
    }
}
