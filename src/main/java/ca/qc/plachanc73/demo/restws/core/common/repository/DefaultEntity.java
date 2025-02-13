package ca.qc.plachanc73.demo.restws.core.common.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * The base class of all (non-auditable) entities.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class DefaultEntity implements Identifiable<Long> {
    /**
     * The generated entity id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
