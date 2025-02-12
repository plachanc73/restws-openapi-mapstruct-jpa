package ca.qc.plachanc73.demo.restws.core.document.service;

import ca.qc.plachanc73.demo.restws.core.common.service.exception.*;
import ca.qc.plachanc73.demo.restws.core.document.repository.DocumentEntity;
import ca.qc.plachanc73.demo.restws.core.document.repository.DocumentRepository;
import ca.qc.plachanc73.demo.restws.core.document.service.dto.DocumentDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class DocumentManageService {

    private final DocumentRepository documentRepository;
    private final DocumentDtoMapper documentDtoMapper;

    public DocumentManageService(DocumentRepository documentRepository, DocumentDtoMapper documentDtoMapper) {
        this.documentRepository = documentRepository;
        this.documentDtoMapper = documentDtoMapper;
    }

    public void deleteDocument(UUID documentUUID) throws NotFoundException {
        DocumentEntity documentEntity = documentRepository.findByDocumentUUIDAndVisibleIsTrue(documentUUID)
                .orElseThrow(() -> new NotFoundException(
                        "No visible document found for the UUID %s".formatted(documentUUID)));

        // The document is put invisible
        documentEntity.setVisible(false);
        documentRepository.save(documentEntity);
    }

    public DocumentDto getDocumentByUUID(UUID documentUUID) throws NotFoundException {
        DocumentEntity documentEntity = documentRepository.findByDocumentUUIDAndVisibleIsTrue(documentUUID)
                .orElseThrow(() -> new NotFoundException(
                        "No visible document found with the UUID %s".formatted(documentUUID)));
        return documentDtoMapper.documentEntityToDocumentDto(documentEntity);
    }

    public DocumentDto upsertDocument(DocumentDto documentDtoToUpsert)
            throws BadRequestException, ConflictException {
        if (documentDtoToUpsert == null) {
            throw new IllegalArgumentException("The document is required for Upsert");
        } else if (StringUtils.isBlank(documentDtoToUpsert.getDocumentName())) {
            throw new BadRequestException("The document name is required for Upsert");
        } else if (StringUtils.isBlank(documentDtoToUpsert.getDocumentTypeCode())) {
            throw new BadRequestException("The document type code is required for Upsert");
        } else if (StringUtils.isBlank(documentDtoToUpsert.getFileTypeName())) {
            throw new BadRequestException("The file type name is required for Upsert");
        }

        DocumentEntity documentEntityToUpsert =
                documentDtoMapper.documentDtoToDocumentEntity(documentDtoToUpsert);

        if (documentEntityToUpsert.getDocumentUUID() == null) {
            // The document will be created with a new UUID and visible by default
            documentEntityToUpsert.setDocumentUUID(UUID.randomUUID());
            documentEntityToUpsert.setVisible(true);
        }

        Optional<DocumentEntity> optionalDocumentFoundWithSameName =
                documentRepository.findByDocumentNameAndVisibleIsTrue(documentEntityToUpsert.getDocumentName());

        // If another document already exists with the same name, we throw a conflict exception
        if (optionalDocumentFoundWithSameName.isPresent()) {
            throw new ConflictException(
                    "Another document exists with the same name %s".formatted(
                            documentEntityToUpsert.getDocumentName()));
        }

        // The document was upserted (created or updated)
        DocumentEntity documentEntityUpserted = documentRepository.save(documentEntityToUpsert);
        return documentDtoMapper.documentEntityToDocumentDto(documentEntityUpserted);
    }
}
