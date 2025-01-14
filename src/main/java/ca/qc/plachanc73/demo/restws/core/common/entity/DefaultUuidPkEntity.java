package ca.qc.plachanc73.demo.restws.core.common.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

/**
 * The base class of all (non-auditable) entities with UUID Primary key.
 */
@Data
@MappedSuperclass
public abstract class DefaultUuidPkEntity implements Identifiable<java.util.UUID> {

    @Id
    private java.util.UUID id;
}
