package io.github.jtsato.moviesbattle.core.domains.quiz.usecase;

import io.github.jtsato.moviesbattle.core.domains.quiz.model.Quiz;

/**
 * @author Jorge Takeshi Sato
 */

@FunctionalInterface
public interface RegisterQuizGateway {

    Quiz execute(final Quiz quiz);
}
