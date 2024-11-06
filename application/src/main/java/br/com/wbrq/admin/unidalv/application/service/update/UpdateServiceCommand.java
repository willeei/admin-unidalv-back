package br.com.wbrq.admin.unidalv.application.service.update;

import java.util.Set;

public record UpdateServiceCommand(
        String id,
        String name,
        int point,
        boolean isActive,
        Set<String> presences) {

    public static UpdateServiceCommand with(
            final String id,
            final String name,
            final int point,
            final Boolean isActive,
            final Set<String> presences
    ) {
        return new UpdateServiceCommand(id, name, point, isActive != null ? isActive : Boolean.TRUE, presences);
    }
}
