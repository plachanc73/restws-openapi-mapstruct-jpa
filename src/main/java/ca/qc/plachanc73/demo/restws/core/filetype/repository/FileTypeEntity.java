package ca.qc.plachanc73.demo.restws.core.filetype.repository;

import ca.qc.plachanc73.demo.restws.core.common.repository.DefaultEntity;
import ca.qc.plachanc73.demo.restws.core.documenttype.repository.DocumentTypeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "FILE_TYPE")
@FieldNameConstants
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileTypeEntity extends DefaultEntity {

    @NotBlank
    @Length(max = 50)
    @Column(name = "`name`")
    private String name;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "`documentTypeCode`", referencedColumnName = "`code`")
    private DocumentTypeEntity documentType;

    @NotNull
    @Column(name = "`visible`")
    private Boolean visible;
}
