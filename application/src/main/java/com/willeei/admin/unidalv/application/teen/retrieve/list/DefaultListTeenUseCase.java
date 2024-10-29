package com.willeei.admin.unidalv.application.teen.retrieve.list;

import java.util.Objects;

import com.willeei.admin.unidalv.domain.pagination.Pagination;
import com.willeei.admin.unidalv.domain.pagination.SearchQuery;
import com.willeei.admin.unidalv.domain.teen.TeenGateway;

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
