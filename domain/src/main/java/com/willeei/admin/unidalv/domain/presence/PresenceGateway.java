package com.willeei.admin.unidalv.domain.presence;

import java.util.List;
import java.util.Optional;

import com.willeei.admin.unidalv.domain.pagination.Pagination;
import com.willeei.admin.unidalv.domain.pagination.SearchQuery;

public interface PresenceGateway {

    Presence create(Presence aPresence);

    void deleteById(PresenceID anId);

    List<PresenceID> existsByIds(Iterable<PresenceID> ids);

    Pagination<Presence> findAll(SearchQuery aQuery);

    Optional<Presence> findById(PresenceID anId);

    Presence update(Presence aPresence);
}
