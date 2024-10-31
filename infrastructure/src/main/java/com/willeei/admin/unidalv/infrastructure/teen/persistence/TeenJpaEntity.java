package com.willeei.admin.unidalv.infrastructure.teen.persistence;

import java.time.Instant;
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

    public static TeenJpaEntity from(Teen teen) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'from'");
    }

    public Teen toAggregate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toAggregate'");
    }
}
