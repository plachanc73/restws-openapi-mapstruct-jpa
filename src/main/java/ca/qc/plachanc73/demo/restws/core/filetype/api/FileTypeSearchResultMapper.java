package ca.qc.plachanc73.demo.restws.core.filetype.api;

import ca.qc.plachanc73.demo.restws.core.filetype.service.dto.FileTypeSearchResultDto;
import ca.qc.plachanc73.demo.restws.dto.FileTypeSearchResult;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassExhaustiveStrategy;

@Mapper(componentModel = "spring", subclassExhaustiveStrategy = SubclassExhaustiveStrategy.COMPILE_ERROR)
public interface FileTypeSearchResultMapper {
    FileTypeSearchResult fileTypeSearchResultDtoToFileTypeSearchResult(FileTypeSearchResultDto fileTypeSearchResultDto);
}
