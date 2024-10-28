package com.willeei.unidalv.domain.presence;

import com.willeei.unidalv.domain.ValueObject;
import com.willeei.unidalv.domain.utils.IdUtils;

import java.util.Objects;

public class Worship extends ValueObject {

    private final String id;
    private final String name;
    private final int point;

    private Worship(final String id, final String name, final int point) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.point = point;
    }

    public static Worship with(final String name, final int point) {
        return new Worship(IdUtils.uuid(), name, point);
    }

    public static Worship with(final String id, final String name, final int point) {
        return new Worship(id, name, point);
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public int point() {
        return point;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Worship worship = (Worship) o;
        return Objects.equals(id, worship.id) && Objects.equals(name, worship.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        return result;
    }
}
