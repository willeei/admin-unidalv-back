package br.com.wbrq.admin.unidalv.application.presence.retrieve.list;

import java.util.Objects;

import br.com.wbrq.admin.unidalv.domain.pagination.Pagination;
import br.com.wbrq.admin.unidalv.domain.pagination.SearchQuery;
import br.com.wbrq.admin.unidalv.domain.presence.PresenceGateway;

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
