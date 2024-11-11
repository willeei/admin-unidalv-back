package br.com.wbrq.admin.unidalv.application.presence.retrieve.get;

import java.util.Objects;

import br.com.wbrq.admin.unidalv.domain.exceptions.NotFoundException;
import br.com.wbrq.admin.unidalv.domain.presence.Presence;
import br.com.wbrq.admin.unidalv.domain.presence.PresenceGateway;
import br.com.wbrq.admin.unidalv.domain.presence.PresenceID;

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
