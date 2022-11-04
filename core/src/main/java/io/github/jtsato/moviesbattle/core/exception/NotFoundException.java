package io.github.jtsato.moviesbattle.core.exception;

/**
 * @author Jorge Takeshi Sato
 */

public class NotFoundException extends CoreException {

    private static final long serialVersionUID = 5952828639711873898L;

    public NotFoundException(final String message, final Object... args) {
        super(message, args);
    }
}
