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
        return new QuizResponse(quiz.getId(),
                quiz.getOptionOneId(),
                quiz.getOptionOneTitle(),
                quiz.getOptionOneYear(),
                quiz.getOptionTwoId(),
                quiz.getOptionTwoTitle(),
                quiz.getOptionTwoYear(),
                quiz.getCreatedAt(),
                quiz.getUpdatedAt());
    }
}
