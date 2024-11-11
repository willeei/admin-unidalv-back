package br.com.wbrq.admin.unidalv.application.presence.create;

import java.time.LocalDate;

import br.com.wbrq.admin.unidalv.domain.presence.PresenceType;

public record CreatePresenceCommand(
        LocalDate date,
        PresenceType type,
        String justification,
        String serviceId,
        String teenId) {

    public static CreatePresenceCommand with(
            final LocalDate aDate,
            final PresenceType aType,
            final String aJustification,
            final String aServiceId,
            final String aTeenId
    ) {
        return new CreatePresenceCommand(aDate, aType, aJustification, aServiceId, aTeenId);
    }
}
