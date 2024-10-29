package com.willeei.unidalv.application.presence.update;

import com.willeei.unidalv.domain.Identifier;
import com.willeei.unidalv.domain.exceptions.NotFoundException;
import com.willeei.unidalv.domain.exceptions.NotificationException;
import com.willeei.unidalv.domain.presence.Presence;
import com.willeei.unidalv.domain.presence.PresenceGateway;
import com.willeei.unidalv.domain.presence.PresenceID;
import com.willeei.unidalv.domain.validation.handler.Notification;

import java.util.Objects;
import java.util.function.Supplier;

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
        final var aWorship = aCmd.worship();
        final var isActive = aCmd.isActive();

        final var aPresence = this.presenceGateway.findById(anId)
                .orElseThrow(notFound(anId));

        final var notification = Notification.create();
        notification.validate(() -> aPresence.update(aDate, aType, aWorship, isActive));

        if (notification.hasError()) {
            notify(anId, notification);
        }

        return UpdatePresenceOutput.from(this.presenceGateway.update(aPresence));
    }

    private void notify(final Identifier anId, final Notification notification) {
        throw new NotificationException("Could not update Aggregate CastMember %s".formatted(anId.getValue()), notification);
    }

    private Supplier<NotFoundException> notFound(final PresenceID anId) {
        return () -> NotFoundException.with(Presence.class, anId);
    }
}
