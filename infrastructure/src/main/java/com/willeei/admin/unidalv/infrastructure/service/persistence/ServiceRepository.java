package com.willeei.admin.unidalv.infrastructure.service.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceRepository extends JpaRepository<ServiceJpaEntity, String> {

    Page<ServiceJpaEntity> findAll(Specification<ServiceJpaEntity> whereClause, Pageable pageable);

    @Query("SELECT s.id FROM Service s WHERE s.id IN :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);

}
