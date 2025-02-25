package com.willeei.admin.unidalv.application.presence.update;

import java.time.LocalDate;

import com.willeei.admin.unidalv.domain.presence.PresenceType;
import com.willeei.admin.unidalv.domain.service.Service;

public record UpdatePresenceCommand(
        String id,
        LocalDate date,
        PresenceType type,
        Service service,
        boolean isActive) {

    public static UpdatePresenceCommand with(
            final String anId,
            final LocalDate aDate,
            final PresenceType aType,
            final Service aService,
            final boolean aIsActive
    ) {
        return new UpdatePresenceCommand(anId, aDate, aType, aService, aIsActive);
    }
}
