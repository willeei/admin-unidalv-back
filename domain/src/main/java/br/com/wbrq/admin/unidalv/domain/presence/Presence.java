package br.com.wbrq.admin.unidalv.domain.presence;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

import br.com.wbrq.admin.unidalv.domain.AggregateRoot;
import br.com.wbrq.admin.unidalv.domain.exceptions.NotificationException;
import br.com.wbrq.admin.unidalv.domain.service.Service;
import br.com.wbrq.admin.unidalv.domain.teen.Teen;
import br.com.wbrq.admin.unidalv.domain.utils.InstantUtils;
import br.com.wbrq.admin.unidalv.domain.utils.LocalDateUtils;
import br.com.wbrq.admin.unidalv.domain.validation.ValidationHandler;
import br.com.wbrq.admin.unidalv.domain.validation.handler.Notification;

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
    private final Instant createdAt;
    private Instant updatedAt;

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
            final Instant aCreationDate,
            final Instant aUpdateDate
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
        this.createdAt = aCreationDate;
        this.updatedAt = aUpdateDate;
        selfValidate();
    }

    public static Presence newPresence(
            final LocalDate aDate,
            final PresenceType aType,
            final String aJustification,
            final Service aService,
            final Teen aTeen
    ) {
        Objects.requireNonNull(aDate, "'date' should not be null");

        final var anId = PresenceID.unique();
        final var now = Instant.now();

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
                now,
                now
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
            final Instant aCreationDate,
            final Instant aUpdateDate
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
                aCreationDate,
                aUpdateDate
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
                aPresence.getCreatedAt(),
                aPresence.getUpdatedAt()
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new PresenceValidator(this, handler).validate();
    }

    public Presence update(
            final LocalDate aDate,
            final PresenceType aType,
            final String aJustification
    ) {
        final var date = LocalDateUtils.with(aDate);
        this.day = date.day();
        this.weekYear = date.weekYear();
        this.weekMonth = date.weekMonth();
        this.month = date.month();
        this.year = date.year();
        this.type = aType;
        this.justification = aJustification;
        this.updatedAt = InstantUtils.now();
        selfValidate();
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

    public Instant getUpdatedAt() {
        return updatedAt;
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
                + ", updatedAt=" + updatedAt
                + '}';
    }
}
