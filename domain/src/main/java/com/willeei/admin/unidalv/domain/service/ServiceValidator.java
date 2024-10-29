package com.willeei.admin.unidalv.domain.service;

import com.willeei.admin.unidalv.domain.validation.Error;
import com.willeei.admin.unidalv.domain.validation.ValidationHandler;
import com.willeei.admin.unidalv.domain.validation.Validator;

public class ServiceValidator extends Validator {

    private static final int NAME_MAX_LENGTH = 255;
    private static final int NAME_MIN_LENGTH = 3;

    private final Service service;

    public ServiceValidator(final Service aService, final ValidationHandler aHandler) {
        super(aHandler);
        this.service = aService;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.service.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }
}
