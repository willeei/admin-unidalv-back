package com.willeei.admin.unidalv.infrastructure.teen.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeenRepository extends JpaRepository<TeenJpaEntity, String> {

    Page<TeenJpaEntity> findAll(Specification<TeenJpaEntity> whereClause, Pageable pageable);

    @Query("SELECT t.id FROM Teen t WHERE t.id IN :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);

}
