package com.willeei.unidalv.domain.exceptions;


import com.willeei.unidalv.domain.AggregateRoot;
import com.willeei.unidalv.domain.Identifier;
import com.willeei.unidalv.domain.validation.Error;

import java.io.Serial;
import java.util.Collections;
import java.util.List;

public class NotFoundException extends DomainException {

    @Serial
    private static final long serialVersionUID = -6314296608568347729L;

    protected NotFoundException(final String aMessage, final List<Error> anErrors) {
        super(aMessage, anErrors);
    }

    public static NotFoundException with(
            final Class<? extends AggregateRoot<?>> anAggregate,
            final Identifier id
    ) {
        final var anError = "%s with ID %s was not found".formatted(
                anAggregate.getSimpleName(),
                id.getValue()
        );

        return new NotFoundException(anError, Collections.emptyList());
    }

    public static NotFoundException with(final Error error) {
        return new NotFoundException(error.message(), List.of(error));
    }
}
