package br.com.wbrq.admin.unidalv.infrastructure.api.controllers;

import java.net.URI;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.wbrq.admin.unidalv.application.service.create.CreateServiceCommand;
import br.com.wbrq.admin.unidalv.application.service.create.CreateServiceUseCase;
import br.com.wbrq.admin.unidalv.application.service.delete.DeleteServiceUseCase;
import br.com.wbrq.admin.unidalv.application.service.retrieve.get.GetServiceByIdUseCase;
import br.com.wbrq.admin.unidalv.application.service.retrieve.list.ListServiceUseCase;
import br.com.wbrq.admin.unidalv.application.service.update.UpdateServiceCommand;
import br.com.wbrq.admin.unidalv.application.service.update.UpdateServiceUseCase;
import br.com.wbrq.admin.unidalv.domain.pagination.Pagination;
import br.com.wbrq.admin.unidalv.domain.pagination.SearchQuery;
import br.com.wbrq.admin.unidalv.infrastructure.api.ServiceAPI;
import br.com.wbrq.admin.unidalv.infrastructure.service.models.CreateServiceRequest;
import br.com.wbrq.admin.unidalv.infrastructure.service.models.ServiceListResponse;
import br.com.wbrq.admin.unidalv.infrastructure.service.models.ServiceResponse;
import br.com.wbrq.admin.unidalv.infrastructure.service.models.UpdateServiceRequest;
import br.com.wbrq.admin.unidalv.infrastructure.service.presenters.ServicePresenter;

@RestController
public class ServiceController implements ServiceAPI {

    private final CreateServiceUseCase createServiceUseCase;
    private final DeleteServiceUseCase deleteServiceUseCase;
    private final GetServiceByIdUseCase getServiceByIdUseCase;
    private final ListServiceUseCase listServiceUseCase;
    private final UpdateServiceUseCase updateServiceUseCase;

    public ServiceController(
            final CreateServiceUseCase createServiceUseCase,
            final DeleteServiceUseCase deleteServiceUseCase,
            final GetServiceByIdUseCase getServiceByIdUseCase,
            final ListServiceUseCase listServiceUseCase,
            final UpdateServiceUseCase updateServiceUseCase
    ) {
        this.createServiceUseCase = Objects.requireNonNull(createServiceUseCase);
        this.deleteServiceUseCase = Objects.requireNonNull(deleteServiceUseCase);
        this.getServiceByIdUseCase = Objects.requireNonNull(getServiceByIdUseCase);
        this.listServiceUseCase = Objects.requireNonNull(listServiceUseCase);
        this.updateServiceUseCase = Objects.requireNonNull(updateServiceUseCase);
    }

    @Override
    public ResponseEntity<?> createService(final CreateServiceRequest input) {
        final var aCmd = CreateServiceCommand.with(
                input.name(), input.point(), input.active()
        );

        final var output = this.createServiceUseCase.execute(aCmd);

        return ResponseEntity.created(URI.create("/services/" + output.id())).build();
    }

    @Override
    public Pagination<ServiceListResponse> listServices(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        return this.listServiceUseCase
                .execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(ServicePresenter::present);
    }

    @Override
    public ServiceResponse getById(final String id) {
        return ServicePresenter.present(this.getServiceByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(final String id, UpdateServiceRequest req) {
        final var aCmd = UpdateServiceCommand.with(id, req.name(), req.point(), req.active());

        final var output = this.updateServiceUseCase.execute(aCmd);
        return ResponseEntity.ok(output);
    }

    @Override
    public void deleteById(final String id) {
        this.deleteServiceUseCase.execute(id);
    }

}
