package ca.qc.plachanc73.demo.restws.core.documenttype.service;

import ca.qc.plachanc73.demo.restws.core.documenttype.repository.DocumentTypeEntity;
import ca.qc.plachanc73.demo.restws.core.documenttype.service.dto.DocumentTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassExhaustiveStrategy;

@Mapper(componentModel = "spring", subclassExhaustiveStrategy = SubclassExhaustiveStrategy.COMPILE_ERROR)
public interface DocumentTypeDtoMapper {
    DocumentTypeDto documentTypeEntityToDocumentTypeDto(DocumentTypeEntity documentTypeEntity);
}