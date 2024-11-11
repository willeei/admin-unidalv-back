package br.com.wbrq.admin.unidalv.application.presence.retrieve.list;

import java.time.Instant;

import br.com.wbrq.admin.unidalv.domain.presence.Presence;
import br.com.wbrq.admin.unidalv.domain.presence.PresenceType;
import br.com.wbrq.admin.unidalv.domain.teen.Teen;

public record PresenceListOutput(
        String id,
        String day,
        String weekYear,
        String weekMonth,
        String month,
        String year,
        PresenceType type,
        Teen teen,
        Instant createdAt,
        Instant updatedAt) {

    public static PresenceListOutput from(final Presence aPresence) {
        return new PresenceListOutput(
                aPresence.getId().getValue(),
                aPresence.getDay(),
                aPresence.getWeekYear(),
                aPresence.getWeekMonth(),
                aPresence.getMonth(),
                aPresence.getYear(),
                aPresence.getType(),
                aPresence.getTeen(),
                aPresence.getCreatedAt(),
                aPresence.getUpdatedAt()
        );
    }
}
