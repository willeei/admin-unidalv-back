package br.com.wbrq.admin.unidalv.application.service.create;

import java.util.Objects;

import br.com.wbrq.admin.unidalv.domain.exceptions.NotificationException;
import br.com.wbrq.admin.unidalv.domain.service.Service;
import br.com.wbrq.admin.unidalv.domain.service.ServiceGateway;
import br.com.wbrq.admin.unidalv.domain.validation.handler.Notification;

public non-sealed class DefaultCreateServiceUseCase extends CreateServiceUseCase {

    private final ServiceGateway serviceGateway;

    public DefaultCreateServiceUseCase(final ServiceGateway serviceGateway) {
        this.serviceGateway = Objects.requireNonNull(serviceGateway);
    }

    @Override
    public CreateServiceOutput execute(final CreateServiceCommand aCmd) {
        final var aName = aCmd.name();
        final var aPoint = aCmd.point();
        final var isActive = aCmd.isActive();

        final var notification = Notification.create();

        final var aService = notification.validate(() -> Service.newService(aName, aPoint, isActive));

        if (notification.hasError()) {
            notify(notification);
        }

        return CreateServiceOutput.from(this.serviceGateway.create(aService));
    }

    private void notify(Notification notification) {
        throw new NotificationException("Could not create Aggregate Service", notification);
    }
}
