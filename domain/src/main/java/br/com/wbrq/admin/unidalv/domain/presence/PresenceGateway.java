package br.com.wbrq.admin.unidalv.domain.presence;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import br.com.wbrq.admin.unidalv.domain.pagination.Pagination;
import br.com.wbrq.admin.unidalv.domain.pagination.SearchQuery;
import br.com.wbrq.admin.unidalv.domain.service.ServiceID;

public interface PresenceGateway {

    Presence create(Presence aPresence);

    void deleteById(PresenceID anId);

    List<PresenceID> existsByIds(Iterable<PresenceID> ids);

    Pagination<Presence> findAll(SearchQuery aQuery);

    Optional<Presence> findById(PresenceID anId);

    Presence update(Presence aPresence);

    Set<Presence> findByServiceId(ServiceID id);
}
