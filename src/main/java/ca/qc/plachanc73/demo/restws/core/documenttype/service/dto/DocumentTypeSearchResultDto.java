package ca.qc.plachanc73.demo.restws.core.documenttype.service.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentTypeSearchResultDto {
    private List<DocumentTypeDto> results;
    private Long totalResults;
}
