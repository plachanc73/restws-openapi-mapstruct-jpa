package ca.qc.plachanc73.demo.restws.core.document.repository;

import ca.qc.plachanc73.demo.restws.core.common.entity.DefaultEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Table(name = "DOCUMENT")
@FieldNameConstants
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentEntity extends DefaultEntity {

    @NotBlank
    @Column(name = "`documentUUID`")
    private java.util.UUID documentUUID;

    @NotBlank
    @Length(max = 255)
    @Column(name = "`documentName`")
    private String documentName;

    @NotNull
    @Length(max = 5)
    @Column(name = "`documentTypeCode`")
    private String documentTypeCode;

    @NotNull
    @Length(max = 10)
    @Column(name = "`fileTypeName`")
    private String fileTypeName;

    @NotBlank
    @Length(max = 500)
    @Column(name = "`fileLocation`")
    private String fileLocation;

    @Length(max = 1000)
    @Column(name = "`description`")
    private String description;

    @NotNull
    @Column(name = "`visible`")
    private Boolean visible;

    @NotNull
    @Column(name = "`creationTimestamp`")
    private LocalDateTime creationTimestamp;

    @NotNull
    @Column(name = "`lastUpdateTimestamp`")
    private LocalDateTime lastUpdateTimestamp;

    @PrePersist
    protected void onCreate() {
        this.creationTimestamp = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateTimestamp = LocalDateTime.now();
    }
}
