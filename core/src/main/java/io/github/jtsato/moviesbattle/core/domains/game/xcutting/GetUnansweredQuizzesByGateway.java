package io.github.jtsato.moviesbattle.core.domains.game.xcutting;

import io.github.jtsato.moviesbattle.core.domains.quiz.model.Quiz;

import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@FunctionalInterface
public interface GetUnansweredQuizzesByGateway {

    Optional<Quiz> execute(final Long gameId);
}
