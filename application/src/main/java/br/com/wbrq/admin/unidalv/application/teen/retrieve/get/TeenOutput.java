package br.com.wbrq.admin.unidalv.application.teen.retrieve.get;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.wbrq.admin.unidalv.domain.presence.PresenceID;
import br.com.wbrq.admin.unidalv.domain.teen.Gender;
import br.com.wbrq.admin.unidalv.domain.teen.Teen;

public record TeenOutput(
        String id,
        String name,
        String birthDate,
        boolean isMember,
        boolean isActive,
        boolean hasDiscipleship,
        String phone,
        String guardianPhone,
        String guardianName,
        Gender gender,
        Instant enrollmentDate,
        Instant reEnrollmentDate,
        Set<String> presences,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt) {

    public static TeenOutput from(final Teen aTeen) {
        return new TeenOutput(
                aTeen.getId().getValue(),
                aTeen.getName(),
                aTeen.getBirthDate(),
                aTeen.isMember(),
                aTeen.isActive(),
                aTeen.hasDiscipleship(),
                aTeen.getPhone(),
                aTeen.getGuardianPhone(),
                aTeen.getGuardianName(),
                aTeen.getGender(),
                aTeen.getEnrollmentDate(),
                aTeen.getReEnrollmentDate(),
                aTeen.getPresences().stream()
                        .map(PresenceID::getValue)
                        .collect(Collectors.toSet()),
                aTeen.getCreatedAt(),
                aTeen.getUpdatedAt(),
                aTeen.getDeletedAt()
        );
    }

}
