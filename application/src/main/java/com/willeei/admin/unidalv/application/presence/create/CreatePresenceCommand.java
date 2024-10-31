package com.willeei.admin.unidalv.application.presence.create;

import java.time.LocalDate;

import com.willeei.admin.unidalv.domain.presence.PresenceType;

public record CreatePresenceCommand(
        LocalDate date,
        PresenceType type,
        boolean isActive) {

    public static CreatePresenceCommand with(
            final LocalDate aDate,
            final PresenceType aType,
            final boolean aIsActive
    ) {
        return new CreatePresenceCommand(aDate, aType, aIsActive);
    }
}
