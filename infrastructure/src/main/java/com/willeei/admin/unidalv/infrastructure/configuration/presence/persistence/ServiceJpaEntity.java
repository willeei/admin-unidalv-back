package com.willeei.admin.unidalv.infrastructure.configuration.presence.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.willeei.admin.unidalv.domain.service.Service;

@Entity
@Table(name = "service_jpa_entity")
public class ServiceJpaEntity {

    @Id
    private Long id;

    public static ServiceJpaEntity from(final Service service) {
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
