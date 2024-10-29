package com.willeei.admin.unidalv.application.presence.retrieve.list;

import java.time.Instant;

import com.willeei.admin.unidalv.domain.presence.Presence;
import com.willeei.admin.unidalv.domain.presence.PresenceType;
import com.willeei.admin.unidalv.domain.service.Service;
import com.willeei.admin.unidalv.domain.teen.Teen;

public record PresenceListOutput(
        String day,
        String weekYear,
        String weekMonth,
        String month,
        String year,
        PresenceType type,
        Service service,
        Teen teen,
        boolean isActive,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt) {

    public static PresenceListOutput from(final Presence aPresence) {
        return new PresenceListOutput(
                aPresence.getDay(),
                aPresence.getWeekYear(),
                aPresence.getWeekMonth(),
                aPresence.getMonth(),
                aPresence.getYear(),
                aPresence.getType(),
                aPresence.getService(),
                aPresence.getTeen(),
                aPresence.isActive(),
                aPresence.getCreatedAt(),
                aPresence.getUpdatedAt(),
                aPresence.getDeletedAt()
        );
    }
}
