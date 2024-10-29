package com.willeei.unidalv.application.presence.retrieve.list;

import com.willeei.unidalv.domain.presence.Presence;
import com.willeei.unidalv.domain.presence.PresenceType;
import com.willeei.unidalv.domain.presence.Worship;

import java.time.Instant;

public record PresenceListOutput(
        String day,
        String weekYear,
        String weekMonth,
        String month,
        String year,
        PresenceType type,
        Worship worship,
        boolean isActive,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

    public static PresenceListOutput from(final Presence aPresence) {
        return new PresenceListOutput(
                aPresence.getDay(),
                aPresence.getWeekYear(),
                aPresence.getWeekMonth(),
                aPresence.getMonth(),
                aPresence.getYear(),
                aPresence.getType(),
                aPresence.getWorship(),
                aPresence.isActive(),
                aPresence.getCreatedAt(),
                aPresence.getUpdatedAt(),
                aPresence.getDeletedAt()
        );
    }
}
