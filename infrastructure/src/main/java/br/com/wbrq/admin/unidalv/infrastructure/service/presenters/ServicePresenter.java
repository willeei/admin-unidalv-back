package br.com.wbrq.admin.unidalv.infrastructure.service.presenters;

import br.com.wbrq.admin.unidalv.application.service.retrieve.get.ServiceOutput;
import br.com.wbrq.admin.unidalv.application.service.retrieve.list.ServiceListOutput;
import br.com.wbrq.admin.unidalv.infrastructure.service.models.ServiceListResponse;
import br.com.wbrq.admin.unidalv.infrastructure.service.models.ServiceResponse;

public interface ServicePresenter {

    static ServiceResponse present(final ServiceOutput output) {
        return new ServiceResponse(
                output.id(),
                output.name(),
                output.point(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static ServiceListResponse present(final ServiceListOutput output) {
        return new ServiceListResponse(
                output.id(),
                output.name(),
                output.point(),
                output.isActive(),
                output.createdAt().toString(),
                output.updatedAt().toString()
        );
    }
}
