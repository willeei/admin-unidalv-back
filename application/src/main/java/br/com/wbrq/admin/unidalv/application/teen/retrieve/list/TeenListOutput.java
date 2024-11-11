package br.com.wbrq.admin.unidalv.application.teen.retrieve.list;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.wbrq.admin.unidalv.domain.presence.PresenceID;
import br.com.wbrq.admin.unidalv.domain.teen.Gender;
import br.com.wbrq.admin.unidalv.domain.teen.Teen;

public record TeenListOutput(
        String id,
        String name,
        String birthDate,
        boolean isActive,
        String phone,
        Gender gender,
        Instant enrollmentDate,
        Set<String> presences,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt) {

    public static TeenListOutput from(final Teen aTeen) {
        return new TeenListOutput(
                aTeen.getId().getValue(),
                aTeen.getName(),
                aTeen.getBirthDate(),
                aTeen.isActive(),
                aTeen.getPhone(),
                aTeen.getGender(),
                aTeen.getEnrollmentDate(),
                aTeen.getPresences().stream()
                        .map(PresenceID::getValue)
                        .collect(Collectors.toSet()),
                aTeen.getCreatedAt(),
                aTeen.getUpdatedAt(),
                aTeen.getDeletedAt()
        );
    }

}
