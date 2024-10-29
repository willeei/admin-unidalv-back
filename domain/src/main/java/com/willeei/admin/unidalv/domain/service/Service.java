package com.willeei.admin.unidalv.domain.service;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.willeei.admin.unidalv.domain.AggregateRoot;
import com.willeei.admin.unidalv.domain.exceptions.NotificationException;
import com.willeei.admin.unidalv.domain.presence.PresenceID;
import com.willeei.admin.unidalv.domain.utils.InstantUtils;
import com.willeei.admin.unidalv.domain.validation.ValidationHandler;
import com.willeei.admin.unidalv.domain.validation.handler.Notification;

public class Service extends AggregateRoot<ServiceID> {

    private String name;
    private int point;
    private boolean active;
    private Set<PresenceID> presences;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    public Service(
            final ServiceID id,
            final String name,
            final int point,
            final boolean active,
            final Set<PresenceID> presences,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        super(id);
        this.name = name;
        this.point = point;
        this.active = active;
        this.presences = presences;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        selfValidate();
    }

    public static Service newService(final String aName, final int aPoint, final boolean isActive) {
        final var anId = ServiceID.unique();
        final var now = InstantUtils.now();
        final var deletedAt = isActive ? null : now;
        return new Service(
                anId,
                aName,
                aPoint,
                isActive,
                new HashSet<>(),
                now,
                now,
                deletedAt
        );
    }

    public static Service with(
            final ServiceID anId,
            final String aName,
            final int aPoint,
            final boolean isActive,
            final Set<PresenceID> presences,
            final Instant aCreationDate,
            final Instant aUpdateDate,
            final Instant aDeletedDate
    ) {
        return new Service(
                anId,
                aName,
                aPoint,
                isActive,
                presences,
                aCreationDate,
                aUpdateDate,
                aDeletedDate
        );
    }

    public static Service with(final Service aService) {
        return new Service(
                aService.getId(),
                aService.getName(),
                aService.getPoint(),
                aService.isActive(),
                new HashSet<>(aService.presences),
                aService.getCreatedAt(),
                aService.getUpdatedAt(),
                aService.getDeletedAt()
        );
    }

    public Service update(
            final String aName,
            final int aPoint,
            final boolean isActive,
            final Set<PresenceID> presences
    ) {
        if (isActive) {
            activate();
        } else {
            deactivate();
        }
        this.name = aName;
        this.point = aPoint;
        this.active = isActive;
        this.presences = new HashSet<>(presences != null ? presences : Collections.emptySet());
        this.updatedAt = InstantUtils.now();
        selfValidate();
        return this;
    }

    public Service activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Service deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = InstantUtils.now();
        }

        this.active = false;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new ServiceValidator(this, handler).validate();
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }

    public boolean isActive() {
        return active;
    }

    public Set<PresenceID> getPresences() {
        return Collections.unmodifiableSet(presences);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public Service addPresence(final PresenceID presenceID) {
        if (presenceID == null) {
            return this;
        }
        this.presences.add(presenceID);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Service addPresences(final Set<PresenceID> presenceIDs) {
        if (presenceIDs == null || presenceIDs.isEmpty()) {
            return this;
        }
        this.presences.addAll(presenceIDs);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Service removePresence(final PresenceID presenceID) {
        if (presenceID == null) {
            return this;
        }
        this.presences.remove(presenceID);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);

        if (notification.hasError()) {
            throw new NotificationException("Failed to create a Aggregate Service", notification);
        }
    }
}
