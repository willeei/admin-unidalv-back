package com.willeei.admin.unidalv.infrastructure.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.willeei.admin.unidalv.domain.pagination.Pagination;
import com.willeei.admin.unidalv.domain.pagination.SearchQuery;
import com.willeei.admin.unidalv.domain.service.Service;
import com.willeei.admin.unidalv.domain.service.ServiceGateway;
import com.willeei.admin.unidalv.domain.service.ServiceID;
import com.willeei.admin.unidalv.infrastructure.service.persistence.ServiceJpaEntity;
import com.willeei.admin.unidalv.infrastructure.service.persistence.ServiceRepository;

@Component
public class ServiceMySQLGateway implements ServiceGateway {

    private final ServiceRepository repository;

    public ServiceMySQLGateway(final ServiceRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Service create(Service aService) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(ServiceID anId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ServiceID> existsByIds(Iterable<ServiceID> anIds) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @Transactional
    public Optional<Service> findById(final ServiceID anId) {
        return this.repository.findById(anId.getValue()).map(ServiceJpaEntity::toAggregate);
    }

    @Override
    public Service update(Service aService) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Pagination<Service> findAll(SearchQuery aSearchQuery) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
