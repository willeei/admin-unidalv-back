package com.willeei.unidalv.domain.teen;

import com.willeei.unidalv.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface TeenGateway {

    Teen create(Teen aTeen);

    void deleteById(TeenID anId);

    List<TeenID> existsByIds(Iterable<TeenID> ids);

    List<Teen> findAll(SearchQuery aQuery);

    Optional<Teen> findById(TeenID anId);

    Teen update(Teen aTeen);
}
