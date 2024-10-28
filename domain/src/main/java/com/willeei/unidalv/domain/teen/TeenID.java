package com.willeei.unidalv.domain.teen;

import com.willeei.unidalv.domain.Identifier;
import com.willeei.unidalv.domain.utils.IdUtils;

import java.util.Objects;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final TeenID teenID = (TeenID) o;
        return getValue().equals(teenID.getValue());
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }
}
