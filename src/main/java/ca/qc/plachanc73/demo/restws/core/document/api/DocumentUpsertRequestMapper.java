package ca.qc.plachanc73.demo.restws.core.document.api;

import ca.qc.plachanc73.demo.restws.core.document.service.dto.DocumentDto;
import ca.qc.plachanc73.demo.restws.dto.DocumentUpsertRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", subclassExhaustiveStrategy = SubclassExhaustiveStrategy.COMPILE_ERROR)
public interface DocumentUpsertRequestMapper {

    DocumentDto documentUpsertRequestToDocumentDto(DocumentUpsertRequest documentUpsertRequest);

    DocumentDto documentUpsertRequestToDocumentDto(@MappingTarget DocumentDto documentDtoRetrieved,
            DocumentUpsertRequest documentUpsertRequest);
}
