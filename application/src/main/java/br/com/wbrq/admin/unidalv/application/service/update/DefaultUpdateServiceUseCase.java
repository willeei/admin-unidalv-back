package br.com.wbrq.admin.unidalv.application.service.update;

import java.util.Objects;
import java.util.function.Supplier;

import br.com.wbrq.admin.unidalv.domain.Identifier;
import br.com.wbrq.admin.unidalv.domain.exceptions.DomainException;
import br.com.wbrq.admin.unidalv.domain.exceptions.NotFoundException;
import br.com.wbrq.admin.unidalv.domain.exceptions.NotificationException;
import br.com.wbrq.admin.unidalv.domain.service.Service;
import br.com.wbrq.admin.unidalv.domain.service.ServiceGateway;
import br.com.wbrq.admin.unidalv.domain.service.ServiceID;
import br.com.wbrq.admin.unidalv.domain.validation.handler.Notification;

public non-sealed class DefaultUpdateServiceUseCase extends UpdateServiceUseCase {

    private final ServiceGateway serviceGateway;

    public DefaultUpdateServiceUseCase(final ServiceGateway serviceGateway) {
        this.serviceGateway = Objects.requireNonNull(serviceGateway);
    }

    @Override
    public UpdateServiceOutput execute(final UpdateServiceCommand aCmd) {
        final var anId = ServiceID.from(aCmd.id());
        final var aName = aCmd.name();
        final var aPoint = aCmd.point();
        final var isActive = aCmd.isActive();

        final var aService = this.serviceGateway.findById(anId)
                .orElseThrow(notFound(anId));

        final var notification = Notification.create();
        notification.validate(() -> aService.update(aName, aPoint, isActive));

        if (notification.hasError()) {
            notify(notification);
        }

        return UpdateServiceOutput.from(this.serviceGateway.update(aService));
    }

    private void notify(Notification notification) {
        throw new NotificationException("Could not update Aggregate Service", notification);
    }

    private Supplier<DomainException> notFound(final Identifier anId) {
        return () -> NotFoundException.with(Service.class, anId);
    }
}
