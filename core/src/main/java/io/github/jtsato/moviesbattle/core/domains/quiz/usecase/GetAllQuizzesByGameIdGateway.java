package io.github.jtsato.moviesbattle.core.domains.quiz.usecase;

import io.github.jtsato.moviesbattle.core.domains.quiz.model.Quiz;

import java.util.List;

/**
 * @author Jorge Takeshi Sato
 */

@FunctionalInterface
public interface GetAllQuizzesByGameIdGateway {

    List<Quiz> execute(final Long gameId);
}
