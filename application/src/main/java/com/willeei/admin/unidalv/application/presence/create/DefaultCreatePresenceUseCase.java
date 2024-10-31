package com.willeei.admin.unidalv.application.presence.create;

import java.util.Objects;

import com.willeei.admin.unidalv.domain.exceptions.NotificationException;
import com.willeei.admin.unidalv.domain.presence.Presence;
import com.willeei.admin.unidalv.domain.presence.PresenceGateway;
import com.willeei.admin.unidalv.domain.validation.handler.Notification;

public non-sealed class DefaultCreatePresenceUseCase extends CreatePresenceUseCase {

    private final PresenceGateway presenceGateway;

    public DefaultCreatePresenceUseCase(final PresenceGateway presenceGateway) {
        this.presenceGateway = Objects.requireNonNull(presenceGateway);
    }

    @Override
    public CreatePresenceOutput execute(final CreatePresenceCommand aCmd) {
        final var aDate = aCmd.date();
        final var aType = aCmd.type();
        final var isActive = aCmd.isActive();

        final var notification = Notification.create();

        final var aPresence = notification.validate(
                () -> Presence.newPresence(aDate, aType, isActive)
        );

        if (notification.hasError()) {
            notify(notification);
        }

        return CreatePresenceOutput.from(this.presenceGateway.create(aPresence));
    }

    private void notify(Notification notification) {
        throw new NotificationException("Could not create Aggregate Presence", notification);
    }
}
