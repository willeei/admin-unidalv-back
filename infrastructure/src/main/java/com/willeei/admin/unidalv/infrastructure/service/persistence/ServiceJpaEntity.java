package com.willeei.admin.unidalv.infrastructure.service.persistence;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.willeei.admin.unidalv.domain.service.Service;
import com.willeei.admin.unidalv.infrastructure.presence.persistence.PresenceJpaEntity;

@Entity(name = "Service")
@Table(name = "services")
public class ServiceJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "point", nullable = false)
    private int point;

    @Column(name = "active", nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PresenceJpaEntity> presences;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    public ServiceJpaEntity() {
    }

    public ServiceJpaEntity(
            final String id,
            final String name,
            final int point,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        this.id = id;
        this.name = name;
        this.point = point;
        this.active = active;
        this.presences = new HashSet<>();
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static ServiceJpaEntity from(final Service aService, final Set<PresenceJpaEntity> presences) {
        final var entity = new ServiceJpaEntity(
                aService.getId().getValue(),
                aService.getName(),
                aService.getPoint(),
                aService.isActive(),
                aService.getCreatedAt(),
                aService.getUpdatedAt(),
                aService.getDeletedAt()
        );

        entity.setPresences(presences);
        return entity;
    }

    public Service toAggregate() {
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<PresenceJpaEntity> getPresences() {
        return presences;
    }

    public void setPresences(Set<PresenceJpaEntity> presences) {
        this.presences = presences;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }
}
