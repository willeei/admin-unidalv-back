package br.com.wbrq.admin.unidalv.application.service.retrieve.get;

import java.util.Objects;

import br.com.wbrq.admin.unidalv.domain.exceptions.NotFoundException;
import br.com.wbrq.admin.unidalv.domain.service.Service;
import br.com.wbrq.admin.unidalv.domain.service.ServiceGateway;
import br.com.wbrq.admin.unidalv.domain.service.ServiceID;

public non-sealed class DefaultGetServiceByIdUseCase extends GetServiceByIdUseCase {

    private final ServiceGateway serviceGateway;

    public DefaultGetServiceByIdUseCase(final ServiceGateway serviceGateway) {
        this.serviceGateway = Objects.requireNonNull(serviceGateway);
    }

    @Override
    public ServiceOutput execute(final String anId) {
        final var aServiceID = ServiceID.from(anId);
        return this.serviceGateway.findById(aServiceID)
                .map(ServiceOutput::from)
                .orElseThrow(() -> NotFoundException.with(Service.class, aServiceID));
    }
}
