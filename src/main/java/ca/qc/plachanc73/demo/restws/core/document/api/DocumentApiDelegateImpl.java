package ca.qc.plachanc73.demo.restws.core.document.api;

import ca.qc.plachanc73.demo.restws.api.DocumentApiDelegate;
import ca.qc.plachanc73.demo.restws.core.common.api.exception.*;
import ca.qc.plachanc73.demo.restws.core.common.service.exception.*;
import ca.qc.plachanc73.demo.restws.core.document.service.DocumentManageService;
import ca.qc.plachanc73.demo.restws.core.document.service.DocumentSearchService;
import ca.qc.plachanc73.demo.restws.core.document.service.dto.*;
import ca.qc.plachanc73.demo.restws.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.UUID;

@Service
@Slf4j
public class DocumentApiDelegateImpl implements DocumentApiDelegate {

    public static final String REQUEST_BODY_IS_REQUIRED = "Request body is required";
    public static final String CRITERIA_ATTRIBUTE_IS_REQUIRED_IN_THE_REQUEST_BODY =
            "Criteria attribute is required in the request body";
    public static final String PAGE_CRITERIA_IS_INVALID = "Page criteria is invalid";
    public static final String PAGE_SIZE_CRITERIA_IS_INVALID = "Page size criteria is invalid";

    private final DocumentGetResponseMapper documentGetResponseMapper;
    private final DocumentManageService documentManageService;
    private final DocumentSearchRequestMapper documentSearchRequestMapper;
    private final DocumentSearchResultMapper documentSearchResultMapper;
    private final DocumentSearchService documentSearchService;
    private final DocumentUpsertRequestMapper documentUpsertRequestMapper;

    public DocumentApiDelegateImpl(DocumentGetResponseMapper documentGetResponseMapper,
            DocumentManageService documentManageService,
            DocumentSearchRequestMapper documentSearchRequestMapper,
            DocumentSearchResultMapper documentSearchResultMapper,
            DocumentSearchService documentSearchService,
            DocumentUpsertRequestMapper documentUpsertRequestMapper) {
        this.documentGetResponseMapper = documentGetResponseMapper;
        this.documentManageService = documentManageService;
        this.documentSearchRequestMapper = documentSearchRequestMapper;
        this.documentSearchResultMapper = documentSearchResultMapper;
        this.documentSearchService = documentSearchService;
        this.documentUpsertRequestMapper = documentUpsertRequestMapper;
    }

    @Override
    public ResponseEntity<Void> v1CreateDocument(
            DocumentUpsertRequest documentUpsertRequest) {
        try {
            DocumentDto documentDtoToCreate =
                    documentUpsertRequestMapper.documentUpsertRequestToDocumentDto(documentUpsertRequest);
            DocumentDto documentCreated = documentManageService.upsertDocument(documentDtoToCreate);
            log.info("Document with name {} was created successfully with the UUID {}",
                    documentCreated.getDocumentName(),
                    documentCreated.getDocumentUUID());

            return ResponseEntity.created(
                    URI.create("/v1/document/%s".formatted(documentCreated.getDocumentUUID().toString()))).build();
        } catch (BadRequestException bre) {
            throw new HttpBadRequestException();
        } catch (ConflictException ce) {
            throw new HttpConflictException();
        }
    }

    @Override
    public ResponseEntity<Void> v1DeleteDocument(UUID documentUUID) {
        try {
            documentManageService.deleteDocument(documentUUID);
            log.info("Document with UUID {} was deleted successfully", documentUUID);

            return ResponseEntity.noContent().build();
        } catch (NotFoundException nfe) {
            throw new HttpNotFoundException();
        }
    }

    @Override
    public ResponseEntity<DocumentGetResponse> v1GetDocument(UUID documentUUID) {
        try {
            DocumentDto documentRetrieved = documentManageService.getDocumentByUUID(documentUUID);
            DocumentGetResponse documentGetResponse =
                    documentGetResponseMapper.documentDtoToDocumentGetResponse(documentRetrieved);
            return ResponseEntity.ok(documentGetResponse);
        } catch (NotFoundException nfe) {
            throw new HttpNotFoundException();
        }
    }

    @Override
    public ResponseEntity<DocumentSearchResult> v1SearchDocument(DocumentSearchRequest documentSearchRequest) {
        validateSearchRequest(documentSearchRequest);

        DocumentSearchRequestDto documentSearchRequestDto =
                documentSearchRequestMapper.documentSearchRequestToDocumentSearchRequestDto(documentSearchRequest);
        DocumentSearchResultDto documentSearchResultDto =
                documentSearchService.searchDocument(documentSearchRequestDto.getCriteria());
        DocumentSearchResult documentSearchResult =
                documentSearchResultMapper.documentSearchResultDtoToDocumentSearchResult(documentSearchResultDto);

        return ResponseEntity.ok(documentSearchResult);
    }

    @Override
    public ResponseEntity<DocumentGetResponse> v1UpdateDocument(UUID documentUUID,
            DocumentUpsertRequest documentUpsertRequest) {
        try {
            DocumentDto documentDtoRetrieved = documentManageService.getDocumentByUUID(documentUUID);
            DocumentDto documentDtoToUpdate =
                    documentUpsertRequestMapper.documentUpsertRequestToDocumentDto(documentDtoRetrieved,
                            documentUpsertRequest);
            DocumentDto documentUpdated = documentManageService.upsertDocument(documentDtoToUpdate);
            DocumentGetResponse documentGetResponse =
                    documentGetResponseMapper.documentDtoToDocumentGetResponse(documentUpdated);
            log.info("Document with name {} and UUID {} was updated successfully", documentUpdated.getDocumentName(),
                    documentUpdated.getDocumentUUID());

            return ResponseEntity.ok(documentGetResponse);
        } catch (NotFoundException nfe) {
            throw new HttpNotFoundException();
        } catch (BadRequestException bre) {
            throw new HttpBadRequestException();
        } catch (ConflictException ce) {
            throw new HttpConflictException();
        }
    }

    private void validateSearchRequest(DocumentSearchRequest documentSearchRequest) {
        if (documentSearchRequest == null) {
            log.error(REQUEST_BODY_IS_REQUIRED);
            throw new HttpBadRequestException();
        }

        DocumentSearchCriteria documentSearchCriteria =
                documentSearchRequest.getCriteria();
        if (documentSearchCriteria == null) {
            log.error(CRITERIA_ATTRIBUTE_IS_REQUIRED_IN_THE_REQUEST_BODY);
            throw new HttpBadRequestException();
        }

        if (documentSearchCriteria.getPage() < 0L) {
            log.error(PAGE_CRITERIA_IS_INVALID);
            throw new HttpBadRequestException();
        }

        if (documentSearchCriteria.getSize() < 0L) {
            log.error(PAGE_SIZE_CRITERIA_IS_INVALID);
            throw new HttpBadRequestException();
        }
    }
}
