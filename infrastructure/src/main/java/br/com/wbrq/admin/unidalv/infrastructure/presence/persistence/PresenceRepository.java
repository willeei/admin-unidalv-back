package br.com.wbrq.admin.unidalv.infrastructure.presence.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PresenceRepository extends JpaRepository<PresenceJpaEntity, String> {

    Page<PresenceJpaEntity> findAll(Specification<PresenceJpaEntity> whereClause, Pageable pageable);

    @Query("SELECT p.id FROM Presence p WHERE p.id IN :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);
}
