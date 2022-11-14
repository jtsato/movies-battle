package io.github.jtsato.moviesbattle.core.common.validation;

import jakarta.validation.*;
import java.util.Set;

/**
 * @author Jorge Takeshi Sato
 */

public abstract class SelfValidating<T> {

    private final Validator validator;

    protected SelfValidating() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    protected void validateSelf() {
        @SuppressWarnings("unchecked")
        final Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
