package ca.qc.plachanc73.demo.restws.core.documenttype.service;

import ca.qc.plachanc73.demo.restws.core.documenttype.service.dto.DocumentTypeDto;

import java.util.List;

abstract class BaseDocumentTypeSearchServiceTest {

    protected static final DocumentTypeDto DOCUMENT_TYPE_DTO_NAME_GENX =
            DocumentTypeDto.builder()
                    .code("GENX")
                    .name("Name GENX")
                    .build();

    protected static final DocumentTypeDto DOCUMENT_TYPE_DTO_NAME_GENY =
            DocumentTypeDto.builder()
                    .code("GENY")
                    .name("Name GENY")
                    .build();

    protected static final List<DocumentTypeDto> DOCUMENT_TYPE_ALL_NAME_ASC = List.of(
            DOCUMENT_TYPE_DTO_NAME_GENX,
            DOCUMENT_TYPE_DTO_NAME_GENY
    );
}