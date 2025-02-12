package ca.qc.plachanc73.demo.restws.core.document.api;

import ca.qc.plachanc73.demo.restws.core.document.service.dto.DocumentSearchRequestDto;
import ca.qc.plachanc73.demo.restws.dto.DocumentSearchRequest;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassExhaustiveStrategy;

@Mapper(componentModel = "spring", subclassExhaustiveStrategy = SubclassExhaustiveStrategy.COMPILE_ERROR)
public interface DocumentSearchRequestMapper {

    DocumentSearchRequestDto documentSearchRequestToDocumentSearchRequestDto(
            DocumentSearchRequest documentSearchRequest);
}
