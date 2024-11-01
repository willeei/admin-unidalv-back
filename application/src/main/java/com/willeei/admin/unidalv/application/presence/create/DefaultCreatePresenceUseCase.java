package com.willeei.admin.unidalv.application.presence.create;

import java.util.Objects;

import com.willeei.admin.unidalv.domain.exceptions.NotFoundException;
import com.willeei.admin.unidalv.domain.exceptions.NotificationException;
import com.willeei.admin.unidalv.domain.presence.Presence;
import com.willeei.admin.unidalv.domain.presence.PresenceGateway;
import com.willeei.admin.unidalv.domain.service.Service;
import com.willeei.admin.unidalv.domain.service.ServiceGateway;
import com.willeei.admin.unidalv.domain.service.ServiceID;
import com.willeei.admin.unidalv.domain.teen.Teen;
import com.willeei.admin.unidalv.domain.teen.TeenGateway;
import com.willeei.admin.unidalv.domain.teen.TeenID;
import com.willeei.admin.unidalv.domain.validation.Error;
import com.willeei.admin.unidalv.domain.validation.handler.Notification;

public non-sealed class DefaultCreatePresenceUseCase extends CreatePresenceUseCase {

    private final PresenceGateway presenceGateway;
    private final ServiceGateway serviceGateway;
    private final TeenGateway teenGateway;

    public DefaultCreatePresenceUseCase(
            final PresenceGateway presenceGateway,
            final ServiceGateway serviceGateway,
            final TeenGateway teenGateway
    ) {
        this.presenceGateway = Objects.requireNonNull(presenceGateway);
        this.serviceGateway = Objects.requireNonNull(serviceGateway);
        this.teenGateway = Objects.requireNonNull(teenGateway);
    }

    @Override
    public CreatePresenceOutput execute(final CreatePresenceCommand aCmd) {
        final var aDate = aCmd.date();
        final var aType = aCmd.type();
        final var isActive = aCmd.isActive();

        final var serviceID = ServiceID.from(aCmd.serviceId());
        final var teenID = TeenID.from(aCmd.teenId());

        final var service = getService(serviceID);

        final var teen = getTeen(teenID);

        final var notification = Notification.create();

        final var aPresence = notification.validate(
                () -> Presence.newPresence(aDate, aType, service, teen, isActive)
        );

        if (notification.hasError()) {
            notify(notification);
        }

        return CreatePresenceOutput.from(this.presenceGateway.create(aPresence));
    }

    private Teen getTeen(final TeenID teenID) {
        return this.teenGateway.findById(teenID).orElseThrow(() -> {
            throw NotFoundException.with(new Error("Teen not found with ID: %s".formatted(teenID)));
        });
    }

    private Service getService(final ServiceID serviceID) {
        return this.serviceGateway.findById(serviceID).orElseThrow(() -> {
            throw NotFoundException.with(new Error("Service not found with ID: %s".formatted(serviceID)));
        });
    }

    private void notify(Notification notification) {
        throw new NotificationException("Could not create Aggregate Presence", notification);
    }
}
