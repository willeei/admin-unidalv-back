package br.com.wbrq.admin.unidalv.application.service.create;

public record CreateServiceCommand(
        String name,
        int point,
        boolean isActive) {

    public static CreateServiceCommand with(
            final String aName,
            final int aPoint,
            final Boolean isActive
    ) {
        return new CreateServiceCommand(aName, aPoint, isActive != null ? isActive : Boolean.TRUE);
    }
}
