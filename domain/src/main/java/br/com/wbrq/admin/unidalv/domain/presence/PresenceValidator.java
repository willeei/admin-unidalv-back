package br.com.wbrq.admin.unidalv.domain.presence;

import br.com.wbrq.admin.unidalv.domain.validation.Error;
import br.com.wbrq.admin.unidalv.domain.validation.ValidationHandler;
import br.com.wbrq.admin.unidalv.domain.validation.Validator;

public class PresenceValidator extends Validator {

    private final Presence presence;

    public PresenceValidator(final Presence aPresence, final ValidationHandler aHandler) {
        super(aHandler);
        this.presence = aPresence;
    }

    @Override
    public void validate() {
        checkPresenceTypeConstraints();
        checkPresenceJustificationConstraints();
    }

    private void checkPresenceTypeConstraints() {
        final var type = this.presence.getType();
        if (type == null) {
            this.validationHandler().append(new Error("'type' should not be null"));
        }
    }

    private void checkPresenceJustificationConstraints() {
        final var type = this.presence.getType();
        final var justification = this.presence.getJustification();

        if (type != null) {
            if ((type.equals(PresenceType.PRESENT) || type.equals(PresenceType.ABSENT)) && justification != null) {
                this.validationHandler().append(new Error("'justification' should only be user with type 'JUSTIFIED'"));
                return;
            }
            
            if (type.equals(PresenceType.JUSTIFIED) && justification == null) {
                this.validationHandler().append(new Error("'justification' should not be null"));
                return;
            }

            if (type.equals(PresenceType.JUSTIFIED) && justification != null && justification.isBlank()) {
                this.validationHandler().append(new Error("'justification' should not be empty"));
            }
        }
    }
}
