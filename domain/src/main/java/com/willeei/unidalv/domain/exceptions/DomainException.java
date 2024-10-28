package com.willeei.unidalv.domain.exceptions;

import com.willeei.unidalv.domain.validation.Error;

import java.io.Serial;
import java.util.List;

public class DomainException extends NoStacktraceException {

    @Serial
    private static final long serialVersionUID = 310659850828051172L;

    protected final transient List<Error> errors;

    protected DomainException(final String aMessage, final List<Error> anErrors) {
        super(aMessage);
        this.errors = anErrors;
    }

    public static DomainException with(final Error anErrors) {
        return new DomainException(anErrors.message(), List.of(anErrors));
    }

    public static DomainException with(final List<Error> anErrors) {
        return new DomainException("", anErrors);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
