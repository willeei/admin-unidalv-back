package com.willeei.admin.unidalv.application.teen.create;

import java.util.List;
import java.util.Objects;

import com.willeei.admin.unidalv.domain.presence.PresenceID;
import com.willeei.admin.unidalv.domain.teen.TeenGateway;

public class DefaultCreateTeenUseCase extends CreateTeenUseCase {

    private final TeenGateway teenGateway;

    public DefaultCreateTeenUseCase(final TeenGateway teenGateway) {
        this.teenGateway = Objects.requireNonNull(teenGateway);
    }

    @Override
    public CreateTeenOutput execute(final CreateTeenCommand aCmd) {
        final var presences = toPresenceID(aCmd.presences());
        return new CreateTeenOutput();
    }

    private List<PresenceID> toPresenceID(final List<String> presences) {
        return presences.stream().map(PresenceID::from).toList();
    }
}
