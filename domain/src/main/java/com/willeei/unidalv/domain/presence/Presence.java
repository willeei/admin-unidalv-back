package com.willeei.unidalv.domain.presence;

import com.willeei.unidalv.domain.AggregateRoot;
import com.willeei.unidalv.domain.exceptions.NotificationException;
import com.willeei.unidalv.domain.utils.InstantUtils;
import com.willeei.unidalv.domain.utils.LocalDateUtils;
import com.willeei.unidalv.domain.validation.ValidationHandler;
import com.willeei.unidalv.domain.validation.handler.Notification;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

public class Presence extends AggregateRoot<PresenceID> {

    private final Instant createdAt;
    private String day;
    private String weekYear;
    private String weekMonth;
    private String month;
    private String year;
    private PresenceType type;
    private Worship worship;
    private boolean active;
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
            final Worship aWorship,
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
        this.worship = aWorship;
        this.active = isActive;
        this.createdAt = aCreationDate;
        this.updatedAt = aUpdateDate;
        this.deletedAt = aDeletedDate;
        selfValidate();
    }

    public static Presence newPresence(
            final LocalDate aDate,
            final PresenceType aType,
            final Worship aWorship,
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

        return new Presence(anId, day, weekYear, weekMonth, month, year, aType, aWorship, isActive, now, now, deletedAt);
    }

    public static Presence with(
            final PresenceID anId,
            final String aDay,
            final String aWeekYear,
            final String aWeekMonth,
            final String aMonth,
            final String aYear,
            final PresenceType aType,
            final Worship aWorship,
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
                aWorship,
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
                aPresence.getWorship(),
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
            final Worship aWorship,
            final boolean isActive
    ) {
        final var date = LocalDateUtils.with(aDate);
        this.day = date.day();
        this.weekYear = date.weekYear();
        this.weekMonth = date.weekMonth();
        this.month = date.month();
        this.year = date.year();
        this.type = aType;
        this.worship = aWorship;
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

    public Worship getWorship() {
        return worship;
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
}
