package com.willeei.admin.unidalv.application.service.delete;

import com.willeei.admin.unidalv.domain.service.ServiceGateway;
import com.willeei.admin.unidalv.domain.service.ServiceID;

import java.util.Objects;

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
