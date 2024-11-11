package br.com.wbrq.admin.unidalv.application.teen.retrieve.list;

import java.util.Objects;

import br.com.wbrq.admin.unidalv.domain.pagination.Pagination;
import br.com.wbrq.admin.unidalv.domain.pagination.SearchQuery;
import br.com.wbrq.admin.unidalv.domain.teen.TeenGateway;

public final class DefaultListTeenUseCase extends ListTeenUseCase {

    private final TeenGateway teenGateway;

    public DefaultListTeenUseCase(final TeenGateway teenGateway) {
        this.teenGateway = Objects.requireNonNull(teenGateway);
    }

    @Override
    public Pagination<TeenListOutput> execute(final SearchQuery aQuery) {
        return this.teenGateway.findAll(aQuery)
                .map(TeenListOutput::from);
    }
}
