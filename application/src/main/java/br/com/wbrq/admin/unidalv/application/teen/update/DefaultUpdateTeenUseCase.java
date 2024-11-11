package br.com.wbrq.admin.unidalv.application.teen.update;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import br.com.wbrq.admin.unidalv.domain.Identifier;
import br.com.wbrq.admin.unidalv.domain.exceptions.DomainException;
import br.com.wbrq.admin.unidalv.domain.exceptions.NotFoundException;
import br.com.wbrq.admin.unidalv.domain.exceptions.NotificationException;
import br.com.wbrq.admin.unidalv.domain.presence.PresenceGateway;
import br.com.wbrq.admin.unidalv.domain.presence.PresenceID;
import br.com.wbrq.admin.unidalv.domain.teen.Teen;
import br.com.wbrq.admin.unidalv.domain.teen.TeenGateway;
import br.com.wbrq.admin.unidalv.domain.teen.TeenID;
import br.com.wbrq.admin.unidalv.domain.validation.Error;
import br.com.wbrq.admin.unidalv.domain.validation.ValidationHandler;
import br.com.wbrq.admin.unidalv.domain.validation.handler.Notification;

public non-sealed class DefaultUpdateTeenUseCase extends UpdateTeenUseCase {

    private final PresenceGateway presenceGateway;
    private final TeenGateway teenGateway;

    public DefaultUpdateTeenUseCase(final TeenGateway teenGateway, final PresenceGateway presenceGateway) {
        this.presenceGateway = Objects.requireNonNull(presenceGateway);
        this.teenGateway = Objects.requireNonNull(teenGateway);
    }

    @Override
    public UpdateTeenOutput execute(final UpdateTeenCommand aCmd) {
        final var anId = TeenID.from(aCmd.id());
        final var aName = aCmd.name();
        final var aBirthDate = aCmd.birthDate();
        final var isMember = aCmd.isMember();
        final var isActive = aCmd.isActive();
        final var hasDiscipleship = aCmd.hasDiscipleship();
        final var aPhone = aCmd.phone();
        final var aGuardianPhone = aCmd.guardianPhone();
        final var aGuardianName = aCmd.guardianName();
        final var aGender = aCmd.gender();
        final var aReEnrollmentDate = aCmd.reEnrollmentDate();
        final var presences = toPresenceID(aCmd.presences());

        final var aTeen = this.teenGateway.findById(anId)
                .orElseThrow(notFound(anId));

        final var notification = Notification.create();
        notification.append(validatePresences(presences));
        notification.validate(
                () -> aTeen.update(
                        aName,
                        aBirthDate,
                        isMember,
                        isActive,
                        hasDiscipleship,
                        aPhone,
                        aGuardianPhone,
                        aGuardianName,
                        aReEnrollmentDate,
                        aGender,
                        presences
                )
        );

        if (notification.hasError()) {
            throw new NotificationException("Could not update Aggregate Teen %s".formatted(aCmd.id()), notification);
        }

        aTeen.addPresences(presences);

        return UpdateTeenOutput.from(this.teenGateway.update(aTeen));
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

    private Supplier<DomainException> notFound(final Identifier anId) {
        return () -> NotFoundException.with(Teen.class, anId);
    }

    private Set<PresenceID> toPresenceID(final Set<String> presences) {
        return presences.stream()
                .map(PresenceID::from)
                .collect(Collectors.toSet());
    }
}
