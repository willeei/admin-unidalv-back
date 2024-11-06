package br.com.wbrq.admin.unidalv.domain.service;

import java.util.List;
import java.util.Optional;

import br.com.wbrq.admin.unidalv.domain.pagination.Pagination;
import br.com.wbrq.admin.unidalv.domain.pagination.SearchQuery;

public interface ServiceGateway {

    Service create(Service aService);

    void deleteById(ServiceID anId);

    List<ServiceID> existsByIds(Iterable<ServiceID> anIds);

    Optional<Service> findById(ServiceID anId);

    Service update(Service aService);

    Pagination<Service> findAll(SearchQuery aSearchQuery);
}
