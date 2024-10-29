package com.willeei.admin.unidalv.domain.service;

import com.willeei.admin.unidalv.domain.pagination.Pagination;
import com.willeei.admin.unidalv.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface ServiceGateway {

    Service create(Service aService);

    void deleteById(ServiceID anId);

    List<ServiceID> existsByIds(Iterable<ServiceID> anIds);

    Optional<Service> findById(ServiceID anId);

    Service update(Service aService);

    Pagination<Service> findAll(SearchQuery aSearchQuery);
}
