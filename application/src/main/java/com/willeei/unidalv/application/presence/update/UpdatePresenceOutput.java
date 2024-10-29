package com.willeei.unidalv.application.presence.update;

import com.willeei.unidalv.domain.presence.Presence;

public record UpdatePresenceOutput(String id) {

    public static UpdatePresenceOutput from(final Presence aPresence) {
        return new UpdatePresenceOutput(aPresence.getId().getValue());
    }

}
