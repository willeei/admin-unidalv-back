package br.com.wbrq.admin.unidalv.application.service.create;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.wbrq.admin.unidalv.domain.exceptions.NotificationException;
import br.com.wbrq.admin.unidalv.domain.presence.PresenceGateway;
import br.com.wbrq.admin.unidalv.domain.presence.PresenceID;
import br.com.wbrq.admin.unidalv.domain.service.Service;
import br.com.wbrq.admin.unidalv.domain.service.ServiceGateway;
import br.com.wbrq.admin.unidalv.domain.validation.Error;
import br.com.wbrq.admin.unidalv.domain.validation.ValidationHandler;
import br.com.wbrq.admin.unidalv.domain.validation.handler.Notification;

public non-sealed class DefaultCreateServiceUseCase extends CreateServiceUseCase {

    private final ServiceGateway serviceGateway;
    private final PresenceGateway presenceGateway;

    public DefaultCreateServiceUseCase(final ServiceGateway serviceGateway, final PresenceGateway presenceGateway) {
        this.serviceGateway = Objects.requireNonNull(serviceGateway);
        this.presenceGateway = Objects.requireNonNull(presenceGateway);
    }

    @Override
    public CreateServiceOutput execute(final CreateServiceCommand aCmd) {
        final var aName = aCmd.name();
        final var aPoint = aCmd.point();
        final var isActive = aCmd.isActive();
        final var presences = toPresenceID(aCmd.presences());

        final var notification = Notification.create();
        notification.append(validatePresences(presences));

        final var aService = notification.validate(() -> Service.newService(aName, aPoint, isActive));

        if (notification.hasError()) {
            notify(notification);
        }

        aService.addPresences(presences);

        return CreateServiceOutput.from(this.serviceGateway.create(aService));
    }

    private void notify(Notification notification) {
        throw new NotificationException("Could not create Aggregate Service", notification);
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

    private Set<PresenceID> toPresenceID(final Set<String> presences) {
        return presences.stream()
                .map(PresenceID::from)
                .collect(Collectors.toSet());
    }
}
