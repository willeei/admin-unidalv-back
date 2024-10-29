package com.willeei.admin.unidalv.application.presence.create;

import java.time.LocalDate;

import com.willeei.admin.unidalv.domain.presence.PresenceType;
import com.willeei.admin.unidalv.domain.service.Service;

public record CreatePresenceCommand(
        LocalDate date,
        PresenceType type,
        Service service,
        boolean isActive) {

    public static CreatePresenceCommand with(
            final LocalDate aDate, final PresenceType aType, final Service aService, final boolean aIsActive
    ) {
        return new CreatePresenceCommand(aDate, aType, aService, aIsActive);
    }
}
