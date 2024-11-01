package com.willeei.admin.unidalv.infrastructure.teen;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.willeei.admin.unidalv.domain.pagination.Pagination;
import com.willeei.admin.unidalv.domain.pagination.SearchQuery;
import com.willeei.admin.unidalv.domain.teen.Teen;
import com.willeei.admin.unidalv.domain.teen.TeenGateway;
import com.willeei.admin.unidalv.domain.teen.TeenID;
import com.willeei.admin.unidalv.infrastructure.teen.persistence.TeenJpaEntity;
import com.willeei.admin.unidalv.infrastructure.teen.persistence.TeenRepository;

@Component
public class TeenMySQLGateway implements TeenGateway {

    private final TeenRepository repository;

    public TeenMySQLGateway(final TeenRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Teen create(Teen aTeen) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(TeenID anId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TeenID> existsByIds(Iterable<TeenID> ids) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Pagination<Teen> findAll(SearchQuery aQuery) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @Transactional
    public Optional<Teen> findById(final TeenID anId) {
        return this.repository.findById(anId.getValue()).map(TeenJpaEntity::toAggregate);
    }

    @Override
    public Teen update(Teen aTeen) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
