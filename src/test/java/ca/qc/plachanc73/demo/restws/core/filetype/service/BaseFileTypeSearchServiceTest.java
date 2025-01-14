package ca.qc.plachanc73.demo.restws.core.filetype.service;

import ca.qc.plachanc73.demo.restws.core.filetype.service.dto.FileTypeDto;

import java.util.List;

abstract class BaseFileTypeSearchServiceTest {

    protected static final FileTypeDto FILE_TYPE_DTO_NAME_ABC =
            FileTypeDto.builder()
                    .name("ABC")
                    .documentTypeCode("GENX")
                    .build();

    protected static final FileTypeDto FILE_TYPE_DTO_NAME_BCD =
            FileTypeDto.builder()
                    .name("BCD")
                    .documentTypeCode("GENX")
                    .build();

    protected static final FileTypeDto FILE_TYPE_DTO_NAME_CDE =
            FileTypeDto.builder()
                    .name("CDE")
                    .documentTypeCode("GENY")
                    .build();

    protected static final List<FileTypeDto> FILE_TYPE_ALL_NAME_ASC = List.of(
            FILE_TYPE_DTO_NAME_ABC,
            FILE_TYPE_DTO_NAME_BCD,
            FILE_TYPE_DTO_NAME_CDE
    );
}