package com.willeei.unidalv.application.presence.delete;

import com.willeei.unidalv.domain.presence.PresenceGateway;
import com.willeei.unidalv.domain.presence.PresenceID;

import java.util.Objects;

public non-sealed class DefaultDeletePresenceUseCase extends DeletePresenceUseCase {

    private final PresenceGateway presenceGateway;

    public DefaultDeletePresenceUseCase(final PresenceGateway presenceGateway) {
        this.presenceGateway = Objects.requireNonNull(presenceGateway);
    }

    @Override
    public void execute(final String anId) {
        this.presenceGateway.deleteById(PresenceID.from(anId));
    }

}
