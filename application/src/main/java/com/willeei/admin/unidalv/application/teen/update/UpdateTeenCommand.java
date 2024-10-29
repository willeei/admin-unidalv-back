package com.willeei.admin.unidalv.application.teen.update;

import java.time.Instant;
import java.util.Set;

import com.willeei.admin.unidalv.domain.teen.Gender;

public record UpdateTeenCommand(
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
        Instant reEnrollmentDate,
        Set<String> presences) {

    public static UpdateTeenCommand with(
            final String anId,
            final String aName,
            final String aBirthDate,
            final Boolean isMember,
            final Boolean isActive,
            final Boolean hasDiscipleship,
            final String aPhone,
            final String aGuardianPhone,
            final String aGuardianName,
            final Gender aGender,
            final Instant aReEnrollmentDate,
            final Set<String> presences
    ) {
        return new UpdateTeenCommand(
                anId,
                aName,
                aBirthDate,
                isMember != null ? isMember : Boolean.FALSE,
                isActive != null ? isActive : Boolean.TRUE,
                hasDiscipleship != null ? hasDiscipleship : Boolean.FALSE,
                aPhone,
                aGuardianPhone,
                aGuardianName,
                aGender,
                aReEnrollmentDate,
                presences
        );
    }
}
