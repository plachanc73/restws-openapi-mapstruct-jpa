package ca.qc.plachanc73.demo.restws.core.document.repository;

import ca.qc.plachanc73.demo.restws.core.document.service.dto.DocumentSearchCriteriaDto;
import ca.qc.plachanc73.demo.restws.core.document.service.dto.DocumentSearchCriteriaFiltersDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class DocumentSearchCustomImpl implements DocumentSearchCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Long> countBySearchCriteria(DocumentSearchCriteriaDto searchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);

        Root<DocumentEntity> Document = query.from(DocumentEntity.class);

        query.select(criteriaBuilder.countDistinct(Document));

        //Filters
        List<Predicate> predicates = new ArrayList<>(applyFilters(searchCriteria, criteriaBuilder, Document));
        query.where(predicates.toArray(new Predicate[0]));

        //Execute query with the pagination
        Long count = entityManager.createQuery(query).getSingleResult();

        return Optional.of(count);
    }

    @Override
    public Optional<List<DocumentEntity>> findBySearchCriteria(DocumentSearchCriteriaDto searchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<DocumentEntity> query = criteriaBuilder.createQuery(DocumentEntity.class);
        Root<DocumentEntity> Document = query.from(DocumentEntity.class);

        query.select(Document);

        //Filters
        List<Predicate> predicates = new ArrayList<>();
        predicates.addAll(applyFilters(searchCriteria, criteriaBuilder, Document));
        query.where(predicates.toArray(new Predicate[0]));

        //Sort
        applySort(searchCriteria, query, criteriaBuilder, Document);

        //Execute query with the pagination
        List<DocumentEntity> results = entityManager.createQuery(query)
                .setFirstResult((int) (searchCriteria.getPage() * searchCriteria.getSize()))
                .setMaxResults(Math.toIntExact(searchCriteria.getSize()))
                .getResultList();

        return Optional.of(results);
    }

    private List<Predicate> applyFilters(DocumentSearchCriteriaDto searchCriteria,
            CriteriaBuilder criteriaBuilder, Root<DocumentEntity> Document) {
        DocumentSearchCriteriaFiltersDto filters = searchCriteria.getFilters();
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(Document.get(DocumentEntity_.visible), true));

        if (filters != null) {
            if (!CollectionUtils.isEmpty(filters.getDocumentTypeCodes())) {
                predicates.add(Document.get(DocumentEntity_.documentTypeCode)
                        .in(filters.getDocumentTypeCodes()));
            }

            if (!CollectionUtils.isEmpty(filters.getFileTypeNames())) {
                predicates.add(Document.get(DocumentEntity_.fileTypeName)
                        .in(filters.getFileTypeNames()));
            }
        }

        return predicates;
    }

    private void applySort(DocumentSearchCriteriaDto searchCriteria, CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder, Root<DocumentEntity> Document) {
        switch (searchCriteria.getSort()) {
            case DOCUMENT_NAME_ASC:
                query.orderBy(criteriaBuilder.asc(Document.get(DocumentEntity_.documentName)),
                        criteriaBuilder.asc(Document.get(DocumentEntity_.id)));
                break;
            case DOCUMENT_NAME_DESC:
                query.orderBy(criteriaBuilder.desc(Document.get(DocumentEntity_.documentName)),
                        criteriaBuilder.desc(Document.get(DocumentEntity_.id)));
                break;
        }
    }
}
