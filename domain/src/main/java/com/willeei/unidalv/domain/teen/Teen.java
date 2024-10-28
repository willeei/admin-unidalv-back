package com.willeei.unidalv.domain.teen;

import com.willeei.unidalv.domain.AggregateRoot;
import com.willeei.unidalv.domain.exceptions.NotificationException;
import com.willeei.unidalv.domain.presence.PresenceID;
import com.willeei.unidalv.domain.utils.BirthDateUtils;
import com.willeei.unidalv.domain.utils.InstantUtils;
import com.willeei.unidalv.domain.validation.ValidationHandler;
import com.willeei.unidalv.domain.validation.handler.Notification;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Teen extends AggregateRoot<TeenID> {

    private final Instant createdAt;
    private String name;
    private String birthDate;
    private boolean member;
    private boolean active;
    private boolean discipleship;
    private String phone;
    private String guardianPhone;
    private String guardianName;
    private Instant enrollmentDate;
    private Instant reEnrollmentDate;
    private List<PresenceID> presences;
    private Instant updatedAt;
    private Instant deletedAt;

    private Teen(
            final TeenID anId,
            final String aName,
            final String aBirthDate,
            final boolean isMember,
            final boolean isActive,
            final boolean hasDiscipleship,
            final String aPhone,
            final String aGuardianPhone,
            final String aGuardianName,
            final Instant anEnrollmentDate,
            final Instant aReEnrollmentDate,
            final List<PresenceID> presences,
            final Instant aCreationDate,
            final Instant aUpdateDate,
            final Instant aDeleteDate
    ) {
        super(anId);
        this.name = aName;
        this.birthDate = aBirthDate;
        this.member = isMember;
        this.active = isActive;
        this.discipleship = hasDiscipleship;
        this.phone = aPhone;
        this.guardianPhone = aGuardianPhone;
        this.guardianName = aGuardianName;
        this.enrollmentDate = anEnrollmentDate;
        this.reEnrollmentDate = aReEnrollmentDate;
        this.presences = presences;
        this.createdAt = aCreationDate;
        this.updatedAt = aUpdateDate;
        this.deletedAt = aDeleteDate;
        selfValidate();
    }

    public static Teen newTeen(
            final String aName,
            final String aBirthDate,
            final boolean isMember,
            final boolean isActive,
            final boolean hasDiscipleship,
            final String aPhone,
            final String aGuardianPhone,
            final String aGuardianName
    ) {
        final var id = TeenID.unique();
        final var now = InstantUtils.now();
        final var deletedAt = isActive ? null : now;
        final var reEnrollmentDate = isActive ? null : now;
        return new Teen(
                id,
                aName,
                aBirthDate,
                isMember,
                isActive,
                hasDiscipleship,
                aPhone,
                aGuardianPhone,
                aGuardianName,
                now,
                reEnrollmentDate,
                new ArrayList<>(),
                now,
                now,
                deletedAt
        );
    }

    public static Teen with(
            final TeenID anId,
            final String name,
            final String birthDate,
            final boolean member,
            final boolean active,
            final boolean discipleship,
            final String phone,
            final String guardianPhone,
            final String guardianName,
            final Instant enrollmentDate,
            final Instant reEnrollmentDate,
            final List<PresenceID> presences,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        return new Teen(
                anId,
                name,
                birthDate,
                member,
                active,
                discipleship,
                phone,
                guardianPhone,
                guardianName,
                enrollmentDate,
                reEnrollmentDate,
                presences,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public static Teen with(final Teen aTeen) {
        return with(
                aTeen.getId(),
                aTeen.name(),
                aTeen.birthDate(),
                aTeen.isMember(),
                aTeen.isActive(),
                aTeen.hasDiscipleship(),
                aTeen.phone(),
                aTeen.guardianPhone(),
                aTeen.guardianName(),
                aTeen.enrollmentDate(),
                aTeen.reEnrollmentDate(),
                new ArrayList<>(aTeen.presences),
                aTeen.createdAt(),
                aTeen.updatedAt(),
                aTeen.deletedAt()
        );
    }

    @Override
    public void validate(final ValidationHandler aHandler) {
        new TeenValidator(this, aHandler).validate();
    }

    public Teen update(
            final String aName,
            final String aBirthDate,
            final boolean isMember,
            final boolean isActive,
            final boolean hasDiscipleship,
            final String aPhone,
            final String aGuardianPhone,
            final String aGuardianName,
            final Instant anEnrollmentDate,
            final List<PresenceID> presences
    ) {

        if (isActive) {
            activate();
        } else {
            deactivate();
        }
        this.name = aName;
        this.birthDate = aBirthDate;
        this.member = isMember;
        this.active = isActive;
        this.discipleship = hasDiscipleship;
        this.phone = aPhone;
        this.guardianPhone = aGuardianPhone;
        this.guardianName = aGuardianName;
        this.enrollmentDate = anEnrollmentDate;
        this.presences = new ArrayList<>(presences != null ? presences : Collections.emptyList());
        this.updatedAt = InstantUtils.now();
        selfValidate();
        return this;
    }

    public Teen activate() {
        if (this.deletedAt != null) {
            this.reEnrollmentDate = InstantUtils.now();
        }
        this.deletedAt = null;
        this.active = true;
        this.reEnrollmentDate = InstantUtils.now();
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Teen deactivate() {
        if (deletedAt() == null) {
            this.deletedAt = InstantUtils.now();
        }

        this.active = false;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public String name() {
        return name;
    }

    public String birthDate() {
        return birthDate;
    }

    public int age() {
        return BirthDateUtils.age(birthDate);
    }

    public boolean isMember() {
        return member;
    }

    public boolean isActive() {
        return active;
    }

    public boolean hasDiscipleship() {
        return discipleship;
    }

    public String phone() {
        return phone;
    }

    public String guardianPhone() {
        return guardianPhone;
    }

    public String guardianName() {
        return guardianName;
    }

    public Instant enrollmentDate() {
        return enrollmentDate;
    }

    public Instant reEnrollmentDate() {
        return reEnrollmentDate;
    }

    public List<PresenceID> presences() {
        return Collections.unmodifiableList(presences);
    }

    public Instant createdAt() {
        return createdAt;
    }

    public Instant updatedAt() {
        return updatedAt;
    }

    public Instant deletedAt() {
        return deletedAt;
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);

        if (notification.hasError()) {
            throw new NotificationException("Failed to create a Aggregate Teen", notification);
        }
    }

    public Teen addPresence(final PresenceID presenceID) {
        if (presenceID == null) {
            return this;
        }
        this.presences.add(presenceID);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Teen addPresences(final List<PresenceID> presences) {
        if (presences == null || presences.isEmpty()) {
            return this;
        }
        this.presences.addAll(presences);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Teen removePresence(final PresenceID presenceID) {
        if (presenceID == null) {
            return this;
        }
        this.presences.remove(presenceID);
        this.updatedAt = InstantUtils.now();
        return this;
    }
}
