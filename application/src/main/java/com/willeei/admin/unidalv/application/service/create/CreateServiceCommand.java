package com.willeei.admin.unidalv.application.service.create;

import java.util.Set;

public record CreateServiceCommand(
        String name,
        int point,
        boolean isActive,
        Set<String> presences) {

    public static CreateServiceCommand with(
            final String aName,
            final int aPoint,
            final Boolean isActive,
            final Set<String> aPresences
    ) {
        return new CreateServiceCommand(aName, aPoint, isActive != null ? isActive : Boolean.TRUE, aPresences);
    }
}
