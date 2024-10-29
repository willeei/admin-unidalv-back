package com.willeei.admin.unidalv.infrastructure.teen.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.willeei.admin.unidalv.domain.teen.Teen;

@Entity
@Table
public class TeenJpaEntity {

    @Id
    private String id;

    public static TeenJpaEntity from(Teen teen) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'from'");
    }

    public Teen toAggregate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toAggregate'");
    }
}
