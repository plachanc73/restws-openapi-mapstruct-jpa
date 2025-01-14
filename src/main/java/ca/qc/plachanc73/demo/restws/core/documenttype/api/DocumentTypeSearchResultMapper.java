package ca.qc.plachanc73.demo.restws.core.documenttype.api;

import ca.qc.plachanc73.demo.restws.core.documenttype.service.dto.DocumentTypeSearchResultDto;
import ca.qc.plachanc73.demo.restws.dto.DocumentTypeSearchResult;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassExhaustiveStrategy;

@Mapper(componentModel = "spring", subclassExhaustiveStrategy = SubclassExhaustiveStrategy.COMPILE_ERROR)
public interface DocumentTypeSearchResultMapper {
    DocumentTypeSearchResult documentTypeSearchResultDtoToDocumentTypeSearchResult(DocumentTypeSearchResultDto documentTypeSearchResultDto);
}
