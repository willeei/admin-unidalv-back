package br.com.wbrq.admin.unidalv.infrastructure.presence.persistence;

import java.time.Instant;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.wbrq.admin.unidalv.domain.presence.Presence;
import br.com.wbrq.admin.unidalv.domain.presence.PresenceID;
import br.com.wbrq.admin.unidalv.domain.presence.PresenceType;
import br.com.wbrq.admin.unidalv.infrastructure.service.persistence.ServiceJpaEntity;
import br.com.wbrq.admin.unidalv.infrastructure.teen.persistence.TeenJpaEntity;

@Entity(name = "Presence")
@Table(name = "presences")
public class PresenceJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "day", nullable = false)
    private String day;

    @Column(name = "month", nullable = false)
    private String month;

    @Column(name = "year", nullable = false)
    private String year;

    @Column(name = "week_month", nullable = false)
    private String weekMonth;

    @Column(name = "week_year", nullable = false)
    private String weekYear;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PresenceType type;

    @Column(name = "justification")
    private String justification;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @ManyToOne
    private ServiceJpaEntity service;

    @ManyToOne
    private TeenJpaEntity teen;

    public PresenceJpaEntity() {
    }

    public PresenceJpaEntity(
            final String id,
            final String day,
            final String month,
            final String year,
            final String weekMonth,
            final String weekYear,
            final PresenceType type,
            final String justification,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        this.id = id;
        this.day = day;
        this.month = month;
        this.year = year;
        this.weekMonth = weekMonth;
        this.weekYear = weekYear;
        this.type = type;
        this.justification = justification;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static PresenceJpaEntity from(final Presence presence) {
        final var entity = new PresenceJpaEntity(
                presence.getId().getValue(),
                presence.getDay(),
                presence.getMonth(),
                presence.getYear(),
                presence.getWeekMonth(),
                presence.getWeekYear(),
                presence.getType(),
                presence.getJustification(),
                presence.getCreatedAt(),
                presence.getUpdatedAt()
        );

        entity.setService(ServiceJpaEntity.from(presence.getService(), Set.of(entity)));
        entity.setTeen(TeenJpaEntity.from(presence.getTeen(), Set.of(entity)));

        return entity;
    }

    public Presence toAggregate() {
        return Presence.with(
                PresenceID.from(getId()),
                getDay(),
                getWeekYear(),
                getWeekMonth(),
                getMonth(),
                getYear(),
                getType(),
                getJustification(),
                getService().toAggregate(),
                getTeen().toAggregate(),
                getCreatedAt(),
                getUpdatedAt()
        );
    }

    public String getId() {
        return id;
    }

    public PresenceJpaEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getDay() {
        return day;
    }

    public PresenceJpaEntity setDay(String day) {
        this.day = day;
        return this;
    }

    public String getMonth() {
        return month;
    }

    public PresenceJpaEntity setMonth(String month) {
        this.month = month;
        return this;
    }

    public String getYear() {
        return year;
    }

    public PresenceJpaEntity setYear(String year) {
        this.year = year;
        return this;
    }

    public String getWeekMonth() {
        return weekMonth;
    }

    public PresenceJpaEntity setWeekMonth(String weekMonth) {
        this.weekMonth = weekMonth;
        return this;
    }

    public String getWeekYear() {
        return weekYear;
    }

    public PresenceJpaEntity setWeekYear(String weekYear) {
        this.weekYear = weekYear;
        return this;
    }

    public PresenceType getType() {
        return type;
    }

    public PresenceJpaEntity setType(PresenceType type) {
        this.type = type;
        return this;
    }

    public String getJustification() {
        return justification;
    }

    public PresenceJpaEntity setJustification(String justification) {
        this.justification = justification;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public PresenceJpaEntity setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public PresenceJpaEntity setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public ServiceJpaEntity getService() {
        return service;
    }

    public PresenceJpaEntity setService(ServiceJpaEntity service) {
        this.service = service;
        return this;
    }

    public TeenJpaEntity getTeen() {
        return teen;
    }

    public PresenceJpaEntity setTeen(TeenJpaEntity teen) {
        this.teen = teen;
        return this;
    }
}
