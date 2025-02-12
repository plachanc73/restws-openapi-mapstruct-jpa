package ca.qc.plachanc73.demo.restws.core.document.service.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentSearchCriteriaFiltersDto {

    private List<String> documentTypeCodes;
    private List<String> fileTypeNames;
}
