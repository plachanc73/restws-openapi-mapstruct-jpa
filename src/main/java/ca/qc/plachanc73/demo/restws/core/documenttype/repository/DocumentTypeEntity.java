package ca.qc.plachanc73.demo.restws.core.documenttype.repository;

import ca.qc.plachanc73.demo.restws.core.common.entity.DefaultEntity;
import ca.qc.plachanc73.demo.restws.core.filetype.repository.FileTypeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DOCUMENT_TYPE")
@FieldNameConstants
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentTypeEntity extends DefaultEntity {

    @NotBlank
    @Length(max = 10)
    @Column(name = "`code`")
    private String code;

    @NotBlank
    @Length(max = 255)
    @Column(name = "`name`")
    private String name;

    @NotNull
    @Column(name = "`visible`")
    private Boolean visible;

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH}, mappedBy = "documentType")
    private List<FileTypeEntity> fileTypes = new ArrayList<>();
}
