package com.willeei.admin.unidalv.infrastructure.teen.persistence;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.willeei.admin.unidalv.domain.teen.Gender;
import com.willeei.admin.unidalv.domain.teen.Teen;
import com.willeei.admin.unidalv.infrastructure.presence.persistence.PresenceJpaEntity;

@Entity(name = "Teen")
@Table(name = "teens")
public class TeenJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private String birthDate;

    @Column(name = "member", nullable = false)
    private Boolean member;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "discipleship", nullable = false)
    private Boolean discipleship;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "guardian_phone", nullable = false)
    private String guardianPhone;

    @Column(name = "guardian_name", nullable = false)
    private String guardianName;

    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "enrollment_date", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant enrollmentDate;

    @Column(name = "re_enrollment_date", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant reEnrollmentDate;

    @OneToMany(mappedBy = "teen")
    private Set<PresenceJpaEntity> presences;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    public TeenJpaEntity() {
    }

    public TeenJpaEntity(
            final String id,
            final String name,
            final String birthDate,
            final Boolean isMember,
            final Boolean isActive,
            final Boolean hasDiscipleship,
            final String phone,
            final String guardianPhone,
            final String guardianName,
            final Gender gender,
            final Instant enrollmentDate,
            final Instant reEnrollmentDate,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.member = isMember;
        this.active = isActive;
        this.discipleship = hasDiscipleship;
        this.phone = phone;
        this.guardianPhone = guardianPhone;
        this.guardianName = guardianName;
        this.gender = gender;
        this.enrollmentDate = enrollmentDate;
        this.reEnrollmentDate = reEnrollmentDate;
        this.presences = new HashSet<>();
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static TeenJpaEntity from(final Teen teen, final Set<PresenceJpaEntity> presences) {
        final var entity = new TeenJpaEntity(
                teen.getId().getValue(),
                teen.getName(),
                teen.getBirthDate(),
                teen.isMember(),
                teen.isActive(),
                teen.hasDiscipleship(),
                teen.getPhone(),
                teen.getGuardianPhone(),
                teen.getGuardianName(),
                teen.getGender(),
                teen.getEnrollmentDate(),
                teen.getReEnrollmentDate(),
                teen.getCreatedAt(),
                teen.getUpdatedAt(),
                teen.getDeletedAt()
        );

        entity.setPresences(presences);

        return entity;
    }

    public Teen toAggregate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toAggregate'");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getMember() {
        return member;
    }

    public void setMember(Boolean member) {
        this.member = member;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDiscipleship() {
        return discipleship;
    }

    public void setDiscipleship(Boolean discipleship) {
        this.discipleship = discipleship;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGuardianPhone() {
        return guardianPhone;
    }

    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Instant getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Instant enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Instant getReEnrollmentDate() {
        return reEnrollmentDate;
    }

    public void setReEnrollmentDate(Instant reEnrollmentDate) {
        this.reEnrollmentDate = reEnrollmentDate;
    }

    public Set<PresenceJpaEntity> getPresences() {
        return presences;
    }

    public void setPresences(Set<PresenceJpaEntity> presences) {
        this.presences = presences;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }
}
