package ca.qc.plachanc73.demo.restws.core.common.entity;

/**
 * Interface for a class that must have an identifier.  DTO, Entity, etc
 */
public interface Identifiable<I> {
    I getId();
}
