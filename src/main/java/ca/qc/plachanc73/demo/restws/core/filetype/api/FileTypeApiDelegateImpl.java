package ca.qc.plachanc73.demo.restws.core.filetype.api;

import ca.qc.plachanc73.demo.restws.api.FileTypeApiDelegate;
import ca.qc.plachanc73.demo.restws.core.filetype.service.FileTypeSearchService;
import ca.qc.plachanc73.demo.restws.core.filetype.service.dto.FileTypeSearchResultDto;
import ca.qc.plachanc73.demo.restws.dto.FileTypeSearchResult;
import org.springframework.http.ResponseEntity;

public class FileTypeApiDelegateImpl implements FileTypeApiDelegate {

    private final FileTypeSearchResultMapper fileTypeSearchResultMapper;
    private final FileTypeSearchService fileTypeSearchService;

    public FileTypeApiDelegateImpl(FileTypeSearchResultMapper fileTypeSearchResultMapper, FileTypeSearchService fileTypeSearchService) {
        this.fileTypeSearchResultMapper = fileTypeSearchResultMapper;
        this.fileTypeSearchService = fileTypeSearchService;
    }

    @Override
    public ResponseEntity<FileTypeSearchResult> v1SearchFileType() {

        FileTypeSearchResultDto fileTypeSearchResultDto = fileTypeSearchService.searchFileType();

        FileTypeSearchResult fileTypeSearchResult = fileTypeSearchResultMapper.fileTypeSearchResultDtoToFileTypeSearchResult(fileTypeSearchResultDto);

        return ResponseEntity.ok(fileTypeSearchResult);
    }
}
