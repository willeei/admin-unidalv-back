package br.com.wbrq.admin.unidalv.infrastructure.service.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceRepository extends JpaRepository<ServiceJpaEntity, String> {

    @Query("SELECT s FROM Service s LEFT JOIN FETCH s.presences WHERE s.id = :id")
    Optional<ServiceJpaEntity> findByIdWithPresences(String id);

    Page<ServiceJpaEntity> findAll(Specification<ServiceJpaEntity> whereClause, Pageable pageable);

    @Query("SELECT s.id FROM Service s WHERE s.id IN :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);

}
