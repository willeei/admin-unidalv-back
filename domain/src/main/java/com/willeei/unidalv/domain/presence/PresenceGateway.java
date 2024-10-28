package com.willeei.unidalv.domain.presence;

import com.willeei.unidalv.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface PresenceGateway {

    Presence create(Presence aPresence);

    void deleteById(PresenceID anId);

    List<PresenceID> existsByIds(Iterable<PresenceID> ids);

    List<Presence> findAll(SearchQuery aQuery);

    Optional<Presence> findById(PresenceID anId);

    Presence update(Presence aTeen);
}
