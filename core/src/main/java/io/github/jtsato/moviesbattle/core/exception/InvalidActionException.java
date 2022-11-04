package io.github.jtsato.moviesbattle.core.exception;

/**
 * @author Jorge Takeshi Sato
 */

public class InvalidActionException extends CoreException {

    private static final long serialVersionUID = -6738656739101162231L;

    public InvalidActionException(final String message, final Object... args) {
        super(message, args);
    }
}
