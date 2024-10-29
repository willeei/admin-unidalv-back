package com.willeei.admin.unidalv.application.service.retrieve.get;

import com.willeei.admin.unidalv.domain.exceptions.NotFoundException;
import com.willeei.admin.unidalv.domain.service.Service;
import com.willeei.admin.unidalv.domain.service.ServiceGateway;
import com.willeei.admin.unidalv.domain.service.ServiceID;

import java.util.Objects;

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
