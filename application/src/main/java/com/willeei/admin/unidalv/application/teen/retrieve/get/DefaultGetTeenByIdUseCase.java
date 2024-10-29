package com.willeei.admin.unidalv.application.teen.retrieve.get;

import java.util.Objects;

import com.willeei.admin.unidalv.domain.exceptions.NotFoundException;
import com.willeei.admin.unidalv.domain.teen.Teen;
import com.willeei.admin.unidalv.domain.teen.TeenGateway;
import com.willeei.admin.unidalv.domain.teen.TeenID;

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
