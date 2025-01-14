package ca.qc.plachanc73.demo.restws.core.filetype.service;

import ca.qc.plachanc73.demo.restws.core.filetype.repository.FileTypeEntity;
import ca.qc.plachanc73.demo.restws.core.filetype.repository.FileTypeEntity_;
import ca.qc.plachanc73.demo.restws.core.filetype.repository.FileTypeSearchRepository;
import ca.qc.plachanc73.demo.restws.core.filetype.service.dto.FileTypeDto;
import ca.qc.plachanc73.demo.restws.core.filetype.service.dto.FileTypeSearchResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileTypeSearchService {

    private final FileTypeSearchRepository fileTypeSearchRepository;
    private final FileTypeDtoMapper fileTypeDtoMapper;

    public FileTypeSearchService(FileTypeSearchRepository fileTypeSearchRepository, FileTypeDtoMapper fileTypeDtoMapper) {
        this.fileTypeSearchRepository = fileTypeSearchRepository;
        this.fileTypeDtoMapper = fileTypeDtoMapper;
    }

    public FileTypeSearchResultDto searchFileType() {

        Long totalResults = fileTypeSearchRepository.countAllByVisible(true);

        List<FileTypeDto> dtoResults = null;
        if (totalResults > 0L) {
            List<FileTypeEntity> entityResults = fileTypeSearchRepository.findAllByVisible(true, Sort.by(Sort.Order.asc(FileTypeEntity_.NAME)));

            if (!CollectionUtils.isEmpty(entityResults)) {
                dtoResults = entityResults.stream()
                        .map(fileTypeDtoMapper::fileTypeEntityToFileTypeDto)
                        .collect(Collectors.toList());
            }
        }

        return FileTypeSearchResultDto.builder()
                .results(dtoResults)
                .totalResults(totalResults)
                .build();
    }
}
