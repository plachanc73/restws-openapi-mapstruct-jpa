package ca.qc.plachanc73.demo.restws.core.documenttype.service;

import ca.qc.plachanc73.demo.restws.core.documenttype.repository.DocumentTypeEntity;
import ca.qc.plachanc73.demo.restws.core.documenttype.repository.DocumentTypeEntity_;
import ca.qc.plachanc73.demo.restws.core.documenttype.repository.DocumentTypeSearchRepository;
import ca.qc.plachanc73.demo.restws.core.documenttype.service.dto.DocumentTypeDto;
import ca.qc.plachanc73.demo.restws.core.documenttype.service.dto.DocumentTypeSearchResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DocumentTypeSearchService {

    private final DocumentTypeSearchRepository documentTypeSearchRepository;
    private final DocumentTypeDtoMapper documentTypeDtoMapper;

    public DocumentTypeSearchService(DocumentTypeSearchRepository documentTypeSearchRepository, DocumentTypeDtoMapper documentTypeDtoMapper) {
        this.documentTypeSearchRepository = documentTypeSearchRepository;
        this.documentTypeDtoMapper = documentTypeDtoMapper;
    }

    public DocumentTypeSearchResultDto searchDocumentType() {

        Long totalResults = documentTypeSearchRepository.countAllByVisible(true);

        List<DocumentTypeDto> dtoResults = null;
        if (totalResults > 0L) {
            List<DocumentTypeEntity> entityResults = documentTypeSearchRepository.findAllByVisible(true, Sort.by(Sort.Order.asc(DocumentTypeEntity_.CODE)));

            if (!CollectionUtils.isEmpty(entityResults)) {
                dtoResults = entityResults.stream()
                        .map(documentTypeDtoMapper::documentTypeEntityToDocumentTypeDto)
                        .collect(Collectors.toList());
            }
        }

        return DocumentTypeSearchResultDto.builder()
                .results(dtoResults)
                .totalResults(totalResults)
                .build();
    }
}
