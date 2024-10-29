package com.willeei.admin.unidalv.application.teen.create;

import java.util.Set;

import com.willeei.admin.unidalv.domain.teen.Gender;

public record CreateTeenCommand(
        String name,
        String birthDate,
        boolean isMember,
        boolean isActive,
        boolean hasDiscipleship,
        String phone,
        String guardianPhone,
        String guardianName,
        Gender gender,
        Set<String> presences) {

    public static CreateTeenCommand with(
            final String aName,
            final String aBirthDate,
            final Boolean isMember,
            final Boolean isActive,
            final Boolean hasDiscipleship,
            final String aPhone,
            final String aGuardianPhone,
            final String aGuardianName,
            final Gender aGender,
            final Set<String> presences
    ) {
        return new CreateTeenCommand(
                aName,
                aBirthDate,
                isMember != null ? isMember : Boolean.FALSE,
                isActive != null ? isActive : Boolean.TRUE,
                hasDiscipleship != null ? hasDiscipleship : Boolean.FALSE,
                aPhone,
                aGuardianPhone,
                aGuardianName,
                aGender,
                presences
        );
    }
}
