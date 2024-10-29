package com.willeei.admin.unidalv.infrastructure.configuration.presence.persistence;

import com.willeei.admin.unidalv.domain.presence.Presence;
import com.willeei.admin.unidalv.domain.presence.PresenceID;
import com.willeei.admin.unidalv.domain.presence.PresenceType;

import javax.persistence.*;
import java.time.Instant;

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

    @Column(name = "active", nullable = false)
    private boolean active;

    @ManyToOne(optional = false)
    @JoinColumn(name = "worship_id", nullable = false)
    private WorshipJpaEntity worship;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

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
            final boolean active,
            final WorshipJpaEntity worship,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        this.id = id;
        this.day = day;
        this.month = month;
        this.year = year;
        this.weekMonth = weekMonth;
        this.weekYear = weekYear;
        this.type = type;
        this.active = active;
        this.worship = worship;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static PresenceJpaEntity from(final Presence presence) {
        return new PresenceJpaEntity(
                presence.getId().getValue(),
                presence.getDay(),
                presence.getMonth(),
                presence.getYear(),
                presence.getMonth(),
                presence.getWeekYear(),
                presence.getType(),
                presence.isActive(),
                WorshipJpaEntity.from(presence.getWorship()),
                presence.getCreatedAt(),
                presence.getUpdatedAt(),
                presence.getDeletedAt()
        );
    }

    public Presence toAggregate() {
        return Presence.with(
                PresenceID.from(getId()),
                getDay(),
                getMonth(),
                getYear(),
                getWeekMonth(),
                getWeekYear(),
                getType(),
                getWorship().toAggregate(),
                isActive(),
                getCreatedAt(),
                getUpdatedAt(),
                getDeletedAt()
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

    public boolean isActive() {
        return active;
    }

    public PresenceJpaEntity setActive(boolean active) {
        this.active = active;
        return this;
    }

    public WorshipJpaEntity getWorship() {
        return worship;
    }

    public PresenceJpaEntity setWorship(WorshipJpaEntity worship) {
        this.worship = worship;
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

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public PresenceJpaEntity setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }
}
