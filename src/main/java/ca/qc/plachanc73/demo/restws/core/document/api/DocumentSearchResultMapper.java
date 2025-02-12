package ca.qc.plachanc73.demo.restws.core.document.api;

import ca.qc.plachanc73.demo.restws.core.document.service.dto.DocumentSearchResultDto;
import ca.qc.plachanc73.demo.restws.dto.DocumentSearchResult;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassExhaustiveStrategy;

@Mapper(componentModel = "spring", subclassExhaustiveStrategy = SubclassExhaustiveStrategy.COMPILE_ERROR)
public interface DocumentSearchResultMapper {
    DocumentSearchResult documentSearchResultDtoToDocumentSearchResult(DocumentSearchResultDto documentSearchResultDto);
}
