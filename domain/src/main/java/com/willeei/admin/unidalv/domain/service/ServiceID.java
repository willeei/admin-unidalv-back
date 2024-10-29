package com.willeei.admin.unidalv.domain.service;

import com.willeei.admin.unidalv.domain.Identifier;
import com.willeei.admin.unidalv.domain.utils.IdUtils;

import java.util.Objects;

public class ServiceID extends Identifier {

    private final String value;

    public ServiceID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static ServiceID unique() {
        return ServiceID.from(IdUtils.uuid());
    }

    public static ServiceID from(final String anId) {
        return new ServiceID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ServiceID serviceID = (ServiceID) o;
        return getValue().equals(serviceID.getValue());
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }
}
