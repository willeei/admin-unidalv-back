package com.willeei.unidalv.domain.presence;

import com.willeei.unidalv.domain.validation.Error;
import com.willeei.unidalv.domain.validation.ValidationHandler;
import com.willeei.unidalv.domain.validation.Validator;

public class PresenceValidator extends Validator {

    private final Presence presence;

    public PresenceValidator(final Presence aPresence, final ValidationHandler aHandler) {
        super(aHandler);
        this.presence = aPresence;
    }

    @Override
    public void validate() {
        checkPresenceTypeConstraints();
        checkWorshipConstraints();
    }

    private void checkPresenceTypeConstraints() {
        final var type = this.presence.getType();
        if (type == null) {
            this.validationHandler().append(new Error("'type' should not be null"));
        }
    }

    private void checkWorshipConstraints() {
        final var worship = this.presence.getWorship();
        if (worship == null) {
            this.validationHandler().append(new Error("'worship' should not be null"));
        }
    }
}
