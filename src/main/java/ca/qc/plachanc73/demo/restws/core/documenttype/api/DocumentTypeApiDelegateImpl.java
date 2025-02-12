package ca.qc.plachanc73.demo.restws.core.documenttype.api;

import ca.qc.plachanc73.demo.restws.api.DocumentTypeApiDelegate;
import ca.qc.plachanc73.demo.restws.core.documenttype.service.DocumentTypeSearchService;
import ca.qc.plachanc73.demo.restws.core.documenttype.service.dto.DocumentTypeSearchResultDto;
import ca.qc.plachanc73.demo.restws.dto.DocumentTypeSearchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DocumentTypeApiDelegateImpl implements DocumentTypeApiDelegate {

    private final DocumentTypeSearchResultMapper documentTypeSearchResultMapper;
    private final DocumentTypeSearchService documentTypeSearchService;

    public DocumentTypeApiDelegateImpl(DocumentTypeSearchResultMapper documentTypeSearchResultMapper,
            DocumentTypeSearchService documentTypeSearchService) {
        this.documentTypeSearchResultMapper = documentTypeSearchResultMapper;
        this.documentTypeSearchService = documentTypeSearchService;
    }

    @Override
    public ResponseEntity<DocumentTypeSearchResult> v1SearchDocumentType() {

        DocumentTypeSearchResultDto documentTypeSearchResultDto = documentTypeSearchService.searchDocumentType();

        DocumentTypeSearchResult documentTypeSearchResult =
                documentTypeSearchResultMapper.documentTypeSearchResultDtoToDocumentTypeSearchResult(
                        documentTypeSearchResultDto);

        return ResponseEntity.ok(documentTypeSearchResult);
    }
}
