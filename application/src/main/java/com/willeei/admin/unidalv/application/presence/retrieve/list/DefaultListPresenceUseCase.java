package com.willeei.admin.unidalv.application.presence.retrieve.list;

import java.util.Objects;

import com.willeei.admin.unidalv.domain.pagination.Pagination;
import com.willeei.admin.unidalv.domain.pagination.SearchQuery;
import com.willeei.admin.unidalv.domain.presence.PresenceGateway;

public final class DefaultListPresenceUseCase extends ListPresenceUseCase {

    private final PresenceGateway presenceGateway;

    public DefaultListPresenceUseCase(final PresenceGateway presenceGateway) {
        this.presenceGateway = Objects.requireNonNull(presenceGateway);
    }

    @Override
    public Pagination<PresenceListOutput> execute(final SearchQuery aQuery) {
        return this.presenceGateway.findAll(aQuery)
                .map(PresenceListOutput::from);
    }
}
