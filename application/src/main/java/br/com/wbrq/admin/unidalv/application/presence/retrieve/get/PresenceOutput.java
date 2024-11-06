package br.com.wbrq.admin.unidalv.application.presence.retrieve.get;

import java.time.Instant;

import br.com.wbrq.admin.unidalv.domain.presence.Presence;
import br.com.wbrq.admin.unidalv.domain.presence.PresenceType;
import br.com.wbrq.admin.unidalv.domain.service.Service;
import br.com.wbrq.admin.unidalv.domain.teen.Teen;

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
