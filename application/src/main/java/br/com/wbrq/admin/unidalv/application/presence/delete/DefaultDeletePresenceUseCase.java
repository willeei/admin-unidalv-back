package br.com.wbrq.admin.unidalv.application.presence.delete;

import java.util.Objects;

import br.com.wbrq.admin.unidalv.domain.presence.PresenceGateway;
import br.com.wbrq.admin.unidalv.domain.presence.PresenceID;

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
