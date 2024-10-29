package com.willeei.unidalv.domain.presence;

import com.willeei.unidalv.domain.ValueObject;
import com.willeei.unidalv.domain.utils.IdUtils;

import java.util.Objects;

public class Worship implements ValueObject {

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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }
}
