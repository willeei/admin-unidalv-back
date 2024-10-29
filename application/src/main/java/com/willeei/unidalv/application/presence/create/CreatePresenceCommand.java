package com.willeei.unidalv.application.presence.create;

import com.willeei.unidalv.domain.presence.PresenceType;
import com.willeei.unidalv.domain.presence.Worship;

import java.time.LocalDate;

public record CreatePresenceCommand(
        LocalDate date,
        PresenceType type,
        Worship worship,
        boolean isActive
) {

    public static CreatePresenceCommand with(
            final LocalDate aDate, final PresenceType aType, final Worship aWorship, final boolean aIsActive
    ) {
        return new CreatePresenceCommand(aDate, aType, aWorship, aIsActive);
    }
}
