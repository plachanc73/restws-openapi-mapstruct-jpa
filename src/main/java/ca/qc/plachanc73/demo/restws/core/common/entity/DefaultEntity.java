package ca.qc.plachanc73.demo.restws.core.common.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

/**
 * The base class of all (non-auditable) entities.
 */
@Data
@MappedSuperclass
public abstract class DefaultEntity implements Identifiable<Long> {
    /**
     * The generated entity id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
