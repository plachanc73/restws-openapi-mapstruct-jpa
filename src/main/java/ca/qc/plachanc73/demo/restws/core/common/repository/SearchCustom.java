package ca.qc.plachanc73.demo.restws.core.common.repository;

import java.util.List;
import java.util.Optional;

public interface SearchCustom<E, S> {

    Optional<Long> countBySearchCriteria(S searchCriteria);

    Optional<List<E>> findBySearchCriteria(S searchCriteria);
}
