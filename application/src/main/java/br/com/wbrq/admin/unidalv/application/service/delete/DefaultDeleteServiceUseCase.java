package br.com.wbrq.admin.unidalv.application.service.delete;

import java.util.Objects;

import br.com.wbrq.admin.unidalv.domain.service.ServiceGateway;
import br.com.wbrq.admin.unidalv.domain.service.ServiceID;

public non-sealed class DefaultDeleteServiceUseCase extends DeleteServiceUseCase {

    private final ServiceGateway serviceGateway;

    public DefaultDeleteServiceUseCase(final ServiceGateway serviceGateway) {
        this.serviceGateway = Objects.requireNonNull(serviceGateway);
    }

    @Override
    public void execute(final String anId) {
        this.serviceGateway.deleteById(ServiceID.from(anId));
    }

}
