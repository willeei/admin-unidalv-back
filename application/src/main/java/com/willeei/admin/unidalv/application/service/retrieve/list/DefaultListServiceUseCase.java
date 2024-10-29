package com.willeei.admin.unidalv.application.service.retrieve.list;

import java.util.Objects;

import com.willeei.admin.unidalv.domain.pagination.Pagination;
import com.willeei.admin.unidalv.domain.pagination.SearchQuery;
import com.willeei.admin.unidalv.domain.service.ServiceGateway;

public non-sealed class DefaultListServiceUseCase extends ListServiceUseCase {

    private final ServiceGateway serviceGateway;

    public DefaultListServiceUseCase(final ServiceGateway serviceGateway) {
        this.serviceGateway = Objects.requireNonNull(serviceGateway);
    }

    @Override
    public Pagination<ServiceListOutput> execute(final SearchQuery aQuery) {
        return this.serviceGateway.findAll(aQuery)
                .map(ServiceListOutput::from);
    }
}
