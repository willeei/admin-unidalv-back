package com.willeei.admin.unidalv.domain.teen;

import java.util.Objects;

import com.willeei.admin.unidalv.domain.Identifier;
import com.willeei.admin.unidalv.domain.utils.IdUtils;

public class TeenID extends Identifier {

    private final String value;

    public TeenID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static TeenID unique() {
        return TeenID.from(IdUtils.uuid());
    }

    public static TeenID from(final String anId) {
        return new TeenID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final TeenID teenID = (TeenID) o;
        return getValue().equals(teenID.getValue());
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }
}
