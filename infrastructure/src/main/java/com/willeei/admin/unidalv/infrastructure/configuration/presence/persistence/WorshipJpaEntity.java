package com.willeei.admin.unidalv.infrastructure.configuration.presence.persistence;

import com.willeei.admin.unidalv.domain.service.Service;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "worship_jpa_entity")
public class WorshipJpaEntity {

    @Id
    private Long id;

    public static WorshipJpaEntity from(Service service) {
        return null;
    }

    public Service toAggregate() {
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}