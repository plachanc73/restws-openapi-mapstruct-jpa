package ca.qc.plachanc73.demo.restws.core.document.service.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentDto {
    private UUID documentUUID;
    private String documentName;
    private String documentTypeCode;
    private String fileTypeName;
    private String fileLocation;
    private String description;
    private Boolean visible;
    private LocalDateTime creationTimestamp;
    private LocalDateTime lastUpdateTimestamp;
}
