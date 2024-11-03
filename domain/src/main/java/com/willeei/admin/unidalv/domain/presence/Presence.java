package com.willeei.admin.unidalv.domain.presence;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

import com.willeei.admin.unidalv.domain.AggregateRoot;
import com.willeei.admin.unidalv.domain.exceptions.NotificationException;
import com.willeei.admin.unidalv.domain.service.Service;
import com.willeei.admin.unidalv.domain.teen.Teen;
import com.willeei.admin.unidalv.domain.utils.InstantUtils;
import com.willeei.admin.unidalv.domain.utils.LocalDateUtils;
import com.willeei.admin.unidalv.domain.validation.ValidationHandler;
import com.willeei.admin.unidalv.domain.validation.handler.Notification;

public class Presence extends AggregateRoot<PresenceID> {

    private String day;
    private String weekYear;
    private String weekMonth;
    private String month;
    private String year;
    private PresenceType type;
    private String justification;
    private final Service service;
    private final Teen teen;
    private boolean active;
    private final Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Presence(
            final PresenceID anId,
            final String aDay,
            final String aWeekYear,
            final String aWeekMonth,
            final String aMonth,
            final String aYear,
            final PresenceType aType,
            final String aJustification,
            final Service aService,
            final Teen aTeen,
            final boolean isActive,
            final Instant aCreationDate,
            final Instant aUpdateDate,
            final Instant aDeletedDate
    ) {
        super(anId);
        this.day = aDay;
        this.weekYear = aWeekYear;
        this.weekMonth = aWeekMonth;
        this.month = aMonth;
        this.year = aYear;
        this.type = aType;
        this.justification = aJustification;
        this.service = aService;
        this.teen = aTeen;
        this.active = isActive;
        this.createdAt = aCreationDate;
        this.updatedAt = aUpdateDate;
        this.deletedAt = aDeletedDate;
        selfValidate();
    }

    public static Presence newPresence(
            final LocalDate aDate,
            final PresenceType aType,
            final String aJustification,
            final Service aService,
            final Teen aTeen,
            final boolean isActive
    ) {
        Objects.requireNonNull(aDate, "'date' should not be null");

        final var anId = PresenceID.unique();
        final var now = Instant.now();
        final var deletedAt = isActive ? null : now;

        final var date = LocalDateUtils.with(aDate);
        final var day = date.day();
        final var weekYear = date.weekYear();
        final var weekMonth = date.weekMonth();
        final var month = date.month();
        final var year = date.year();

        return new Presence(
                anId,
                day,
                weekYear,
                weekMonth,
                month,
                year,
                aType,
                aJustification,
                aService,
                aTeen,
                isActive,
                now,
                now,
                deletedAt
        );
    }

    public static Presence with(
            final PresenceID anId,
            final String aDay,
            final String aWeekYear,
            final String aWeekMonth,
            final String aMonth,
            final String aYear,
            final PresenceType aType,
            final String aJustification,
            final Service aService,
            final Teen aTeen,
            final boolean isActive,
            final Instant aCreationDate,
            final Instant aUpdateDate,
            final Instant aDeletedDate
    ) {
        return new Presence(
                anId,
                aDay,
                aWeekYear,
                aWeekMonth,
                aMonth,
                aYear,
                aType,
                aJustification,
                aService,
                aTeen,
                isActive,
                aCreationDate,
                aUpdateDate,
                aDeletedDate
        );
    }

    public static Presence with(final Presence aPresence) {
        return new Presence(
                aPresence.getId(),
                aPresence.getDay(),
                aPresence.getWeekYear(),
                aPresence.getWeekMonth(),
                aPresence.getMonth(),
                aPresence.getYear(),
                aPresence.getType(),
                aPresence.getJustification(),
                aPresence.getService(),
                aPresence.getTeen(),
                aPresence.isActive(),
                aPresence.getCreatedAt(),
                aPresence.getUpdatedAt(),
                aPresence.getDeletedAt()
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new PresenceValidator(this, handler).validate();
    }

    public Presence update(
            final LocalDate aDate,
            final PresenceType aType,
            final String aJustification,
            final boolean isActive
    ) {
        final var date = LocalDateUtils.with(aDate);
        this.day = date.day();
        this.weekYear = date.weekYear();
        this.weekMonth = date.weekMonth();
        this.month = date.month();
        this.year = date.year();
        this.type = aType;
        this.justification = aJustification;
        this.active = isActive;
        this.updatedAt = InstantUtils.now();
        selfValidate();
        return this;
    }

    public Presence activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Presence deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = InstantUtils.now();
        }

        this.active = false;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getDay() {
        return day;
    }

    public String getWeekYear() {
        return weekYear;
    }

    public String getWeekMonth() {
        return weekMonth;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public PresenceType getType() {
        return type;
    }

    public String getJustification() {
        return justification;
    }

    public Service getService() {
        return service;
    }

    public Teen getTeen() {
        return teen;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);

        if (notification.hasError()) {
            throw new NotificationException("Failed to create a Aggregate Presence", notification);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Presence)) {
            return false;
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Presence{"
                + "createdAt=" + createdAt
                + ", day='" + day + '\''
                + ", weekYear='" + weekYear + '\''
                + ", weekMonth='" + weekMonth + '\''
                + ", month='" + month + '\''
                + ", year='" + year + '\''
                + ", type=" + type
                + ", justification='" + justification + '\''
                + ", active=" + active
                + ", updatedAt=" + updatedAt
                + ", deletedAt=" + deletedAt
                + '}';
    }
}
