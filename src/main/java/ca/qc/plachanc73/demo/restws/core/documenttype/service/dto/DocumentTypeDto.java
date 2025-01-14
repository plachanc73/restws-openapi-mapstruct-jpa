package ca.qc.plachanc73.demo.restws.core.documenttype.service.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentTypeDto {
    private String code;
    private String name;
}
