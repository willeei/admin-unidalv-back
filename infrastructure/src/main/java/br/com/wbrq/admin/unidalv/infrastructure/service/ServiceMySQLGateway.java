package br.com.wbrq.admin.unidalv.infrastructure.service;

import static br.com.wbrq.admin.unidalv.infrastructure.utils.SpecificationUtils.like;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.wbrq.admin.unidalv.domain.pagination.Pagination;
import br.com.wbrq.admin.unidalv.domain.pagination.SearchQuery;
import br.com.wbrq.admin.unidalv.domain.service.Service;
import br.com.wbrq.admin.unidalv.domain.service.ServiceGateway;
import br.com.wbrq.admin.unidalv.domain.service.ServiceID;
import br.com.wbrq.admin.unidalv.infrastructure.presence.persistence.PresenceRepository;
import br.com.wbrq.admin.unidalv.infrastructure.service.persistence.ServiceJpaEntity;
import br.com.wbrq.admin.unidalv.infrastructure.service.persistence.ServiceRepository;

@Component
public class ServiceMySQLGateway implements ServiceGateway {

    private final ServiceRepository repository;
    private final PresenceRepository presenceRepository;

    public ServiceMySQLGateway(final ServiceRepository repository, final PresenceRepository presenceRepository) {
        this.repository = Objects.requireNonNull(repository);
        this.presenceRepository = Objects.requireNonNull(presenceRepository);
    }

    @Override
    public Service create(final Service aService) {
        return this.save(aService);
    }

    @Override
    public void deleteById(final ServiceID anId) {
        final var anIdValue = anId.getValue();
        if (this.repository.existsById(anIdValue)) {
            this.repository.deleteById(anIdValue);
        }
    }

    @Override
    public List<ServiceID> existsByIds(final Iterable<ServiceID> anIds) {
        final var ids = StreamSupport.stream(anIds.spliterator(), false)
                .map(ServiceID::getValue)
                .toList();
        return this.repository.existsByIds(ids).stream()
                .map(ServiceID::from)
                .toList();
    }

    @Override
    @Transactional
    public Optional<Service> findById(final ServiceID anId) {
        return this.repository.findByIdWithPresences(anId.getValue()).map(ServiceJpaEntity::toAggregate);
    }

    @Override
    @Transactional
    public Service update(final Service aService) {
        // final var serviceEntity = this.repository.findById(aService.getId().getValue())
        //         .orElseThrow(() -> new EntityNotFoundException("Service not found"));

        // serviceEntity.setName(aService.getName());
        // serviceEntity.setPoint(aService.getPoint());
        // serviceEntity.setActive(aService.isActive());
        final var newPresences = this.presenceRepository.findByServiceId(aService.getId().getValue());
        final var entity = ServiceJpaEntity.from(aService, newPresences);

        // serviceEntity.updatePresences(newPresences);
        
        return this.repository.save(entity).toAggregate();
    }

    @Override
    @Transactional
    public Pagination<Service> findAll(final SearchQuery aQuery) {
        // Pagination
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        // Busca dinâmica pelo critério terms
        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult = this.repository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(ServiceJpaEntity::toAggregate).toList()
        );
    }

    private Service save(final Service aService) {
        return this.repository.save(ServiceJpaEntity.from(aService)).toAggregate();
    }

    private Specification<ServiceJpaEntity> assembleSpecification(final String str) {
        return like("name", str);
    }
}
