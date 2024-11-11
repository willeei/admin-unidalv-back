package br.com.wbrq.admin.unidalv.application.service.update;

public record UpdateServiceCommand(
        String id,
        String name,
        int point,
        boolean isActive) {

    public static UpdateServiceCommand with(
            final String id,
            final String name,
            final int point,
            final Boolean isActive
    ) {
        return new UpdateServiceCommand(id, name, point, isActive != null ? isActive : Boolean.TRUE);
    }
}
