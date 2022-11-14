package io.github.jtsato.moviesbattle.entrypoint.rest.domains.quiz;

import io.github.jtsato.moviesbattle.core.domains.quiz.model.Quiz;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Jorge Takeshi Sato
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class QuizPresenter {

    public static QuizResponse of(final Quiz quiz) {
        return new QuizResponse(quiz.id(),
                quiz.optionOneId(),
                quiz.optionOneTitle(),
                quiz.optionOneYear(),
                quiz.optionTwoId(),
                quiz.optionTwoTitle(),
                quiz.optionTwoYear(),
                quiz.createdAt(),
                quiz.updatedAt());
    }
}
