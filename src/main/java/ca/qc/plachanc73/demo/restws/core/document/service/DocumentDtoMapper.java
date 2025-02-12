package ca.qc.plachanc73.demo.restws.core.document.service;

import ca.qc.plachanc73.demo.restws.core.document.repository.DocumentEntity;
import ca.qc.plachanc73.demo.restws.core.document.service.dto.DocumentDto;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassExhaustiveStrategy;

@Mapper(componentModel = "spring", subclassExhaustiveStrategy = SubclassExhaustiveStrategy.COMPILE_ERROR)
public interface DocumentDtoMapper {

    DocumentDto documentEntityToDocumentDto(DocumentEntity documentEntity);

    DocumentEntity documentDtoToDocumentEntity(DocumentDto documentDto);
}
