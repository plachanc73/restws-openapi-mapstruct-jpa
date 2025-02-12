package ca.qc.plachanc73.demo.restws.core.document.service.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentSearchRequestDto {
    DocumentSearchCriteriaDto criteria;
}
