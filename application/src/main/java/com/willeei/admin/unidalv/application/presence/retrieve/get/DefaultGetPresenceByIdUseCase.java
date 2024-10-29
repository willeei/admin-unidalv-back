package com.willeei.admin.unidalv.application.presence.retrieve.get;

import com.willeei.admin.unidalv.domain.exceptions.NotFoundException;
import com.willeei.admin.unidalv.domain.presence.Presence;
import com.willeei.admin.unidalv.domain.presence.PresenceGateway;
import com.willeei.admin.unidalv.domain.presence.PresenceID;

import java.util.Objects;

public final class DefaultGetPresenceByIdUseCase extends GetPresenceByIdUseCase {

    private final PresenceGateway presenceGateway;

    public DefaultGetPresenceByIdUseCase(final PresenceGateway presenceGateway) {
        this.presenceGateway = Objects.requireNonNull(presenceGateway);
    }

    @Override
    public PresenceOutput execute(final String anId) {
        final var aPresenceId = PresenceID.from(anId);
        return this.presenceGateway.findById(aPresenceId)
                .map(PresenceOutput::from)
                .orElseThrow(() -> NotFoundException.with(Presence.class, aPresenceId));
    }
}
