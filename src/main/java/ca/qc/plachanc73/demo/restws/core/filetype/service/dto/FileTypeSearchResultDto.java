package ca.qc.plachanc73.demo.restws.core.filetype.service.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileTypeSearchResultDto {
    private List<FileTypeDto> results;
    private Long totalResults;
}
