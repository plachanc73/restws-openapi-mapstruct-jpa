package ca.qc.plachanc73.demo.restws.core.filetype.service.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileTypeDto {
    private String name;
    private String documentTypeCode;
}
