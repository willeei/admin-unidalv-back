package com.willeei.admin.unidalv.application.teen.create;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.willeei.admin.unidalv.domain.exceptions.NotificationException;
import com.willeei.admin.unidalv.domain.presence.PresenceGateway;
import com.willeei.admin.unidalv.domain.presence.PresenceID;
import com.willeei.admin.unidalv.domain.teen.Teen;
import com.willeei.admin.unidalv.domain.teen.TeenGateway;
import com.willeei.admin.unidalv.domain.validation.Error;
import com.willeei.admin.unidalv.domain.validation.ValidationHandler;
import com.willeei.admin.unidalv.domain.validation.handler.Notification;

public non-sealed class DefaultCreateTeenUseCase extends CreateTeenUseCase {

    private final PresenceGateway presenceGateway;
    private final TeenGateway teenGateway;

    public DefaultCreateTeenUseCase(final TeenGateway teenGateway, final PresenceGateway presenceGateway) {
        this.presenceGateway = Objects.requireNonNull(presenceGateway);
        this.teenGateway = Objects.requireNonNull(teenGateway);
    }

    @Override
    public CreateTeenOutput execute(final CreateTeenCommand aCmd) {
        final var aName = aCmd.name();
        final var aBirthDate = aCmd.birthDate();
        final var isMember = aCmd.isMember();
        final var isActive = aCmd.isActive();
        final var hasDiscipleship = aCmd.hasDiscipleship();
        final var aPhone = aCmd.phone();
        final var aGuardianPhone = aCmd.guardianPhone();
        final var aGuardianName = aCmd.guardianName();
        final var aGender = aCmd.gender();
        final var presences = toPresenceID(aCmd.presences());

        final var notification = Notification.create();
        notification.append(validatePresences(presences));

        final var aTeen = notification.validate(
                () -> Teen.newTeen(
                        aName,
                        aBirthDate,
                        isMember,
                        isActive,
                        hasDiscipleship,
                        aPhone,
                        aGuardianPhone,
                        aGuardianName,
                        aGender
                )
        );

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Teen", notification);
        }

        aTeen.addPresences(presences);

        return CreateTeenOutput.from(this.teenGateway.create(aTeen));
    }

    private ValidationHandler validatePresences(final Set<PresenceID> ids) {
        final var notification = Notification.create();
        if (ids == null || ids.isEmpty()) {
            return notification;
        }

        final var retrievedIds = presenceGateway.existsByIds(ids);

        if (ids.size() != retrievedIds.size()) {
            final var missingIds = new HashSet<>(ids);
            retrievedIds.forEach(missingIds::remove);

            final var missingIdsMessage = missingIds.stream()
                    .map(PresenceID::toString)
                    .collect(Collectors.joining(", "));

            notification.append(new Error("Some presences could not be found: %s".formatted(missingIdsMessage)));
        }

        return notification;
    }

    private Set<PresenceID> toPresenceID(final Set<String> presences) {
        return presences.stream()
                .map(PresenceID::from)
                .collect(Collectors.toSet());
    }
}
