package com.willeei.admin.unidalv.application.presence.retrieve.get;

import java.time.Instant;

import com.willeei.admin.unidalv.domain.presence.Presence;
import com.willeei.admin.unidalv.domain.presence.PresenceType;
import com.willeei.admin.unidalv.domain.service.Service;
import com.willeei.admin.unidalv.domain.teen.Teen;

public record PresenceOutput(
        String id,
        String day,
        String weekYear,
        String weekMonth,
        String month,
        String year,
        PresenceType type,
        String justification,
        Service service,
        Teen teen,
        Instant createdAt,
        Instant updatedAt) {

    public static PresenceOutput from(final Presence aPresence) {
        return new PresenceOutput(
                aPresence.getId().getValue(),
                aPresence.getDay(),
                aPresence.getWeekYear(),
                aPresence.getWeekMonth(),
                aPresence.getMonth(),
                aPresence.getYear(),
                aPresence.getType(),
                aPresence.getJustification(),
                aPresence.getService(),
                aPresence.getTeen(),
                aPresence.getCreatedAt(),
                aPresence.getUpdatedAt()
        );
    }
}
