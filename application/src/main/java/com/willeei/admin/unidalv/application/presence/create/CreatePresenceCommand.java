package com.willeei.admin.unidalv.application.presence.create;

import java.time.LocalDate;

import com.willeei.admin.unidalv.domain.presence.PresenceType;

public record CreatePresenceCommand(
        LocalDate date,
        PresenceType type,
        boolean isActive,
        String serviceId,
        String teenId) {

    public static CreatePresenceCommand with(
            final LocalDate aDate,
            final PresenceType aType,
            final String aServiceId,
            final String aTeenId,
            final boolean isActive
    ) {
        return new CreatePresenceCommand(aDate, aType, isActive, aServiceId, aTeenId);
    }
}
