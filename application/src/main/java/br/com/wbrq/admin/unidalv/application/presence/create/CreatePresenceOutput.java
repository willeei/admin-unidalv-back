package br.com.wbrq.admin.unidalv.application.presence.create;

import br.com.wbrq.admin.unidalv.domain.presence.Presence;

public record CreatePresenceOutput(String id) {

    public static CreatePresenceOutput from(final Presence aPresence) {
        return new CreatePresenceOutput(aPresence.getId().getValue());
    }

}
