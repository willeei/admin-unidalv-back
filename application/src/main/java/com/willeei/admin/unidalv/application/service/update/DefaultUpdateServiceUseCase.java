package com.willeei.admin.unidalv.application.service.update;

import com.willeei.admin.unidalv.domain.Identifier;
import com.willeei.admin.unidalv.domain.exceptions.DomainException;
import com.willeei.admin.unidalv.domain.exceptions.NotFoundException;
import com.willeei.admin.unidalv.domain.exceptions.NotificationException;
import com.willeei.admin.unidalv.domain.presence.PresenceGateway;
import com.willeei.admin.unidalv.domain.presence.PresenceID;
import com.willeei.admin.unidalv.domain.service.Service;
import com.willeei.admin.unidalv.domain.service.ServiceGateway;
import com.willeei.admin.unidalv.domain.service.ServiceID;
import com.willeei.admin.unidalv.domain.validation.Error;
import com.willeei.admin.unidalv.domain.validation.ValidationHandler;
import com.willeei.admin.unidalv.domain.validation.handler.Notification;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public non-sealed class DefaultUpdateServiceUseCase extends UpdateServiceUseCase {

    private final ServiceGateway serviceGateway;
    private final PresenceGateway presenceGateway;

    public DefaultUpdateServiceUseCase(final ServiceGateway serviceGateway, final PresenceGateway presenceGateway) {
        this.serviceGateway = Objects.requireNonNull(serviceGateway);
        this.presenceGateway = Objects.requireNonNull(presenceGateway);
    }

    @Override
    public UpdateServiceOutput execute(final UpdateServiceCommand aCmd) {
        final var anId = ServiceID.from(aCmd.id());
        final var aName = aCmd.name();
        final var aPoint = aCmd.point();
        final var isActive = aCmd.isActive();
        final var presences = toPresenceID(aCmd.presences());

        final var aService = this.serviceGateway.findById(anId)
                .orElseThrow(notFound(anId));

        final var notification = Notification.create();
        notification.append(validatePresences(presences));
        notification.validate(() -> aService.update(aName, aPoint, isActive, presences));

        if (notification.hasError()) {
            notify(notification);
        }

        return UpdateServiceOutput.from(this.serviceGateway.update(aService));
    }

    private void notify(Notification notification) {
        throw new NotificationException("Could not update Aggregate Service", notification);
    }

    private ValidationHandler validatePresences(final Set<PresenceID> ids) {
        final var notification = Notification.create();
        if (ids == null || ids.isEmpty()) {
            return notification;
        }

        final var retrievedIds = presenceGateway.existsByIds(ids);

        if (ids.size() != retrievedIds.size()) {
            final var missingIds = new HashSet<>(ids);
            retrievedIds.forEach(missingIds::remove);

            final var missingIdsMessage = missingIds.stream()
                    .map(PresenceID::toString)
                    .collect(Collectors.joining(", "));

            notification.append(new Error("Some presences could not be found: %s".formatted(missingIdsMessage)));
        }

        return notification;
    }

    private Supplier<DomainException> notFound(final Identifier anId) {
        return () -> NotFoundException.with(Service.class, anId);
    }

    private Set<PresenceID> toPresenceID(final Set<String> presences) {
        return presences.stream()
                .map(PresenceID::from)
                .collect(Collectors.toSet());
    }
}
