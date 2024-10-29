package com.willeei.admin.unidalv.application.teen.delete;

import java.util.Objects;

import com.willeei.admin.unidalv.domain.teen.TeenGateway;
import com.willeei.admin.unidalv.domain.teen.TeenID;

public non-sealed class DefaultDeleteTeenUseCase extends DeleteTeenUseCase {

    private final TeenGateway teenGateway;

    public DefaultDeleteTeenUseCase(final TeenGateway teenGateway) {
        this.teenGateway = Objects.requireNonNull(teenGateway);
    }

    @Override
    public void execute(final String anId) {
        this.teenGateway.deleteById(TeenID.from(anId));
    }
}
