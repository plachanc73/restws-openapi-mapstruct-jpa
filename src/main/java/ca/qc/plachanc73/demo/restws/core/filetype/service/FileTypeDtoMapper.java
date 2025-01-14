package ca.qc.plachanc73.demo.restws.core.filetype.service;

import ca.qc.plachanc73.demo.restws.core.filetype.repository.FileTypeEntity;
import ca.qc.plachanc73.demo.restws.core.filetype.service.dto.FileTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassExhaustiveStrategy;

@Mapper(componentModel = "spring", subclassExhaustiveStrategy = SubclassExhaustiveStrategy.COMPILE_ERROR)
public interface FileTypeDtoMapper {

    @Mapping(source = "documentType.code", target = "documentTypeCode")
    FileTypeDto fileTypeEntityToFileTypeDto(FileTypeEntity fileTypeEntity);
}