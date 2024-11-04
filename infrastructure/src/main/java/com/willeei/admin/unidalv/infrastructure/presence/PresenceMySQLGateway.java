package com.willeei.admin.unidalv.infrastructure.presence;

import static com.willeei.admin.unidalv.infrastructure.utils.SpecificationUtils.like;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.willeei.admin.unidalv.domain.pagination.Pagination;
import com.willeei.admin.unidalv.domain.pagination.SearchQuery;
import com.willeei.admin.unidalv.domain.presence.Presence;
import com.willeei.admin.unidalv.domain.presence.PresenceGateway;
import com.willeei.admin.unidalv.domain.presence.PresenceID;
import com.willeei.admin.unidalv.infrastructure.presence.persistence.PresenceJpaEntity;
import com.willeei.admin.unidalv.infrastructure.presence.persistence.PresenceRepository;

@Component
public class PresenceMySQLGateway implements PresenceGateway {

    private final PresenceRepository repository;

    public PresenceMySQLGateway(final PresenceRepository presenceRepository) {
        this.repository = presenceRepository;
    }

    @Override
    @Transactional
    public Presence create(final Presence aPresence) {
        return this.save(aPresence);
    }

    @Override
    public void deleteById(final PresenceID anId) {
        final var anIdValue = anId.getValue();
        if (this.repository.existsById(anIdValue)) {
            this.repository.deleteById(anIdValue);
        }
    }

    @Override
    public List<PresenceID> existsByIds(final Iterable<PresenceID> presenceIDs) {
        final var ids = StreamSupport.stream(presenceIDs.spliterator(), false)
                .map(PresenceID::getValue)
                .toList();
        return this.repository.existsByIds(ids).stream()
                .map(PresenceID::from)
                .toList();
    }

    @Override
    @Transactional
    public Pagination<Presence> findAll(SearchQuery aQuery) {
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
                pageResult.map(PresenceJpaEntity::toAggregate).toList()
        );
    }

    @Override
    @Transactional
    public Optional<Presence> findById(final PresenceID anId) {
        return this.repository.findById(anId.getValue()).map(PresenceJpaEntity::toAggregate);
    }

    @Override
    @Transactional
    public Presence update(final Presence aPresence) {
        return this.save(aPresence);
    }

    private Presence save(final Presence aPresence) {
        return this.repository.save(PresenceJpaEntity.from(aPresence)).toAggregate();
    }

    private Specification<PresenceJpaEntity> assembleSpecification(final String str) {
        final Specification<PresenceJpaEntity> dayLike = like("day", str);
        final Specification<PresenceJpaEntity> monthLike = like("month", str);
        final Specification<PresenceJpaEntity> yearLike = like("year", str);
        return dayLike.or(monthLike.or(yearLike));
    }
}
