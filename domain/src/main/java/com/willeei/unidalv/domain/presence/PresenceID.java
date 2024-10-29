package com.willeei.unidalv.domain.presence;

import com.willeei.unidalv.domain.Identifier;
import com.willeei.unidalv.domain.utils.IdUtils;

import java.util.Objects;

public class PresenceID extends Identifier {

    private final String value;

    public PresenceID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static PresenceID unique() {
        return PresenceID.from(IdUtils.uuid());
    }

    public static PresenceID from(final String anId) {
        return new PresenceID(anId);
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

        final PresenceID teenID = (PresenceID) o;
        return getValue().equals(teenID.getValue());
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }
}
