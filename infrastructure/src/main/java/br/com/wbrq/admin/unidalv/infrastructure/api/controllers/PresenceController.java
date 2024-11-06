package br.com.wbrq.admin.unidalv.infrastructure.api.controllers;

import java.net.URI;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.wbrq.admin.unidalv.application.presence.create.CreatePresenceCommand;
import br.com.wbrq.admin.unidalv.application.presence.create.CreatePresenceUseCase;
import br.com.wbrq.admin.unidalv.application.presence.delete.DeletePresenceUseCase;
import br.com.wbrq.admin.unidalv.application.presence.retrieve.get.GetPresenceByIdUseCase;
import br.com.wbrq.admin.unidalv.application.presence.retrieve.list.ListPresenceUseCase;
import br.com.wbrq.admin.unidalv.application.presence.update.UpdatePresenceCommand;
import br.com.wbrq.admin.unidalv.application.presence.update.UpdatePresenceUseCase;
import br.com.wbrq.admin.unidalv.domain.pagination.Pagination;
import br.com.wbrq.admin.unidalv.domain.pagination.SearchQuery;
import br.com.wbrq.admin.unidalv.infrastructure.api.ServiceAPI;
import br.com.wbrq.admin.unidalv.infrastructure.presence.models.CreatePresenceRequest;
import br.com.wbrq.admin.unidalv.infrastructure.presence.models.PresenceListResponse;
import br.com.wbrq.admin.unidalv.infrastructure.presence.models.PresenceResponse;
import br.com.wbrq.admin.unidalv.infrastructure.presence.models.UpdatePresenceRequest;
import br.com.wbrq.admin.unidalv.infrastructure.presence.presenters.PresencePresenter;

@RestController
public class PresenceController implements ServiceAPI {

    private final CreatePresenceUseCase createPresenceUseCase;
    private final DeletePresenceUseCase deletePresenceUseCase;
    private final GetPresenceByIdUseCase getPresenceByIdUseCase;
    private final ListPresenceUseCase listPresenceUseCase;
    private final UpdatePresenceUseCase updatePresenceUseCase;

    public PresenceController(
            final CreatePresenceUseCase createPresenceUseCase,
            final DeletePresenceUseCase deletePresenceUseCase,
            final GetPresenceByIdUseCase getPresenceByIdUseCase,
            final ListPresenceUseCase listPresenceUseCase,
            final UpdatePresenceUseCase updatePresenceUseCase
    ) {
        this.createPresenceUseCase = Objects.requireNonNull(createPresenceUseCase);
        this.deletePresenceUseCase = Objects.requireNonNull(deletePresenceUseCase);
        this.getPresenceByIdUseCase = Objects.requireNonNull(getPresenceByIdUseCase);
        this.listPresenceUseCase = Objects.requireNonNull(listPresenceUseCase);
        this.updatePresenceUseCase = Objects.requireNonNull(updatePresenceUseCase);
    }

    @Override
    public ResponseEntity<?> createPresence(final CreatePresenceRequest req) {
        final var aCmd = CreatePresenceCommand.with(
                req.date(),
                req.type(),
                req.justification(),
                req.serviceId(),
                req.teenId()
        );

        final var output = createPresenceUseCase.execute(aCmd);

        return ResponseEntity.created(URI.create("/presences/" + output.id())).build();
    }

    @Override
    public Pagination<PresenceListResponse> listPresences(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        return this.listPresenceUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(PresencePresenter::present);
    }

    @Override
    public PresenceResponse getById(final String id) {
        return PresencePresenter.present(getPresenceByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdatePresenceRequest req) {
        final var aCommand
                = UpdatePresenceCommand.with(id, req.date(), req.type(), req.justification());

        final var output = this.updatePresenceUseCase.execute(aCommand);

        return ResponseEntity.ok(output);
    }

    @Override
    public void deleteById(final String id) {
        this.deletePresenceUseCase.execute(id);
    }
}
