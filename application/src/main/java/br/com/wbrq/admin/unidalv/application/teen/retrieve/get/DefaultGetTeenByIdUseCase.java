package br.com.wbrq.admin.unidalv.application.teen.retrieve.get;

import java.util.Objects;

import br.com.wbrq.admin.unidalv.domain.exceptions.NotFoundException;
import br.com.wbrq.admin.unidalv.domain.teen.Teen;
import br.com.wbrq.admin.unidalv.domain.teen.TeenGateway;
import br.com.wbrq.admin.unidalv.domain.teen.TeenID;

public final class DefaultGetTeenByIdUseCase extends GetTeenByIdUseCase {

    private final TeenGateway teenGateway;

    public DefaultGetTeenByIdUseCase(final TeenGateway teenGateway) {
        this.teenGateway = Objects.requireNonNull(teenGateway);
    }

    @Override
    public TeenOutput execute(final String anId) {
        final var aTeenId = TeenID.from(anId);
        return this.teenGateway.findById(aTeenId)
                .map(TeenOutput::from)
                .orElseThrow(() -> NotFoundException.with(Teen.class, aTeenId));
    }
}
