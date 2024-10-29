package com.willeei.admin.unidalv.domain.teen;

import java.util.List;
import java.util.Optional;

import com.willeei.admin.unidalv.domain.pagination.Pagination;
import com.willeei.admin.unidalv.domain.pagination.SearchQuery;

public interface TeenGateway {

    Teen create(Teen aTeen);

    void deleteById(TeenID anId);

    List<TeenID> existsByIds(Iterable<TeenID> ids);

    Pagination<Teen> findAll(SearchQuery aQuery);

    Optional<Teen> findById(TeenID anId);

    Teen update(Teen aTeen);
}
