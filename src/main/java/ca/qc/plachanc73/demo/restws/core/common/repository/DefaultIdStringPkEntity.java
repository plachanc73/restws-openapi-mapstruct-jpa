package ca.qc.plachanc73.demo.restws.core.common.repository;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

/**
 * The base class of all (non-auditable) entities with id Primary key in String.
 */
@Data
@MappedSuperclass
public abstract class DefaultIdStringPkEntity implements Identifiable<String> {
    /**
     * The entity id.
     */
    @Id
    private String id;
}
