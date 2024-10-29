package com.willeei.unidalv.application.presence.update;

import com.willeei.unidalv.domain.presence.PresenceType;
import com.willeei.unidalv.domain.presence.Worship;

import java.time.LocalDate;

public record UpdatePresenceCommand(
        String id,
        LocalDate date,
        PresenceType type,
        Worship worship,
        boolean isActive
) {

    public static UpdatePresenceCommand with(
            final String anId,
            final LocalDate aDate,
            final PresenceType aType,
            final Worship aWorship,
            final boolean aIsActive
    ) {
        return new UpdatePresenceCommand(anId, aDate, aType, aWorship, aIsActive);
    }
}
