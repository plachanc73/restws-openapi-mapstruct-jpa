package ca.qc.plachanc73.demo.restws.core.document.api;

import ca.qc.plachanc73.demo.restws.core.document.service.dto.DocumentDto;
import ca.qc.plachanc73.demo.restws.dto.DocumentGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassExhaustiveStrategy;

@Mapper(componentModel = "spring", subclassExhaustiveStrategy = SubclassExhaustiveStrategy.COMPILE_ERROR)
public interface DocumentGetResponseMapper {
    DocumentGetResponse documentDtoToDocumentGetResponse(DocumentDto documentDto);
}
