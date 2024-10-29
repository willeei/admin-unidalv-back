package com.willeei.unidalv.domain.teen;

import com.willeei.unidalv.domain.validation.Error;
import com.willeei.unidalv.domain.validation.ValidationHandler;
import com.willeei.unidalv.domain.validation.Validator;

public class TeenValidator extends Validator {

    private static final int NAME_MAX_LENGTH = 255;
    private static final int NAME_MIN_LENGTH = 3;
    private final Teen teen;

    public TeenValidator(final Teen aTeen, final ValidationHandler aHandler) {
        super(aHandler);
        this.teen = aTeen;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkBirthDateConstraints();
        checkPhoneConstraints();
        checkGuardianPhoneConstraints();
        checkGuardianNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.teen.getName();
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

    private void checkBirthDateConstraints() {
        final var birthDate = this.teen.getBirthDate();
        if (birthDate == null) {
            this.validationHandler().append(new Error("'birthDate' should not be null"));
            return;
        }

        if (birthDate.isBlank()) {
            this.validationHandler().append(new Error("'birthDate' should not be empty"));
        }
    }

    private void checkPhoneConstraints() {
        final var phone = this.teen.getPhone();
        if (phone == null) {
            this.validationHandler().append(new Error("'phone' should not be null"));
            return;
        }

        if (phone.isBlank()) {
            this.validationHandler().append(new Error("'phone' should not be empty"));
        }
    }

    private void checkGuardianPhoneConstraints() {
        final var guardianPhone = this.teen.getGuardianPhone();
        if (guardianPhone == null) {
            this.validationHandler().append(new Error("'guardianPhone' should not be null"));
            return;
        }

        if (guardianPhone.isBlank()) {
            this.validationHandler().append(new Error("'guardianPhone' should not be empty"));
        }
    }

    private void checkGuardianNameConstraints() {
        final var guardianName = this.teen.getGuardianName();
        if (guardianName == null) {
            this.validationHandler().append(new Error("'guardianName' should not be null"));
            return;
        }

        if (guardianName.isBlank()) {
            this.validationHandler().append(new Error("'guardianName' should not be empty"));
            return;
        }

        final int length = guardianName.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'guardianName' must be between 3 and 255 characters"));
        }
    }
}
