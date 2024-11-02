package com.willeei.admin.unidalv.domain.presence;

import com.willeei.admin.unidalv.domain.validation.Error;
import com.willeei.admin.unidalv.domain.validation.ValidationHandler;
import com.willeei.admin.unidalv.domain.validation.Validator;

public class PresenceValidator extends Validator {

    private final Presence presence;

    public PresenceValidator(final Presence aPresence, final ValidationHandler aHandler) {
        super(aHandler);
        this.presence = aPresence;
    }

    @Override
    public void validate() {
        checkPresenceTypeConstraints();
    }

    private void checkPresenceTypeConstraints() {
        final var type = this.presence.getType();
        if (type == null) {
            this.validationHandler().append(new Error("'type' should not be null"));
        }
    }
}
