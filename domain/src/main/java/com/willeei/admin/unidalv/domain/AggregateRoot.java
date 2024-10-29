package com.willeei.admin.unidalv.domain;

import java.util.Collections;
import java.util.List;

import com.willeei.admin.unidalv.domain.event.DomainEvent;

public abstract class AggregateRoot<I extends Identifier> extends Entity<I> {

    protected AggregateRoot(final I id) {
        this(id, Collections.emptyList());
    }

    protected AggregateRoot(final I id, final List<DomainEvent> domainEvents) {
        super(id, domainEvents);
    }
}
