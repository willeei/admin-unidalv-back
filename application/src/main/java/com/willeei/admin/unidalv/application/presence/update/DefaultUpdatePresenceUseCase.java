package com.willeei.admin.unidalv.application.presence.update;

import java.util.Objects;
import java.util.function.Supplier;

import com.willeei.admin.unidalv.domain.Identifier;
import com.willeei.admin.unidalv.domain.exceptions.NotFoundException;
import com.willeei.admin.unidalv.domain.exceptions.NotificationException;
import com.willeei.admin.unidalv.domain.presence.Presence;
import com.willeei.admin.unidalv.domain.presence.PresenceGateway;
import com.willeei.admin.unidalv.domain.presence.PresenceID;
import com.willeei.admin.unidalv.domain.validation.handler.Notification;

public non-sealed class DefaultUpdatePresenceUseCase extends UpdatePresenceUseCase {

    private final PresenceGateway presenceGateway;

    public DefaultUpdatePresenceUseCase(final PresenceGateway presenceGateway) {
        this.presenceGateway = Objects.requireNonNull(presenceGateway);
    }

    @Override
    public UpdatePresenceOutput execute(final UpdatePresenceCommand aCmd) {
        final var anId = PresenceID.from(aCmd.id());
        final var aDate = aCmd.date();
        final var aType = aCmd.type();
        final var aJustification = aCmd.justification();
        final var isActive = aCmd.isActive();

        final var aPresence = this.presenceGateway.findById(anId)
                .orElseThrow(notFound(anId));

        final var notification = Notification.create();
        notification.validate(() -> aPresence.update(aDate, aType, aJustification, isActive));

        if (notification.hasError()) {
            notify(anId, notification);
        }

        return UpdatePresenceOutput.from(this.presenceGateway.update(aPresence));
    }

    private void notify(final Identifier anId, final Notification notification) {
        throw new NotificationException(
                "Could not update Aggregate Presence %s".formatted(anId.getValue()), notification);
    }

    private Supplier<NotFoundException> notFound(final PresenceID anId) {
        return () -> NotFoundException.with(Presence.class, anId);
    }
}
