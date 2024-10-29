package com.willeei.admin.unidalv.application.presence.retrieve.list;

import com.willeei.admin.unidalv.domain.pagination.Pagination;
import com.willeei.admin.unidalv.domain.pagination.SearchQuery;
import com.willeei.admin.unidalv.domain.presence.PresenceGateway;

import java.util.Objects;

public final class DefaultListPresenceByIdUseCase extends ListPresenceUseCase {

    private final PresenceGateway presenceGateway;

    public DefaultListPresenceByIdUseCase(final PresenceGateway presenceGateway) {
        this.presenceGateway = Objects.requireNonNull(presenceGateway);
    }

    @Override
    public Pagination<PresenceListOutput> execute(final SearchQuery aQuery) {
        return this.presenceGateway.findAll(aQuery)
                .map(PresenceListOutput::from);
    }
}
