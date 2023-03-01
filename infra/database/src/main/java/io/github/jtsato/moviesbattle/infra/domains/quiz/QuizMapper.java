package io.github.jtsato.moviesbattle.infra.domains.quiz;

import io.github.jtsato.moviesbattle.core.domains.bet.model.Bet;
import io.github.jtsato.moviesbattle.core.domains.quiz.model.Quiz;
import io.github.jtsato.moviesbattle.infra.domains.bet.BetEntity;
import io.github.jtsato.moviesbattle.infra.domains.game.GameMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Jorge Takeshi Sato
 */

@Mapper(uses = {GameMapper.class})
public interface QuizMapper {

    Quiz of(final QuizEntity quizEntity);
    QuizEntity of(final Quiz quiz);

    @Mapping(target = "quiz", ignore = true)
    BetEntity of(Bet bet);

    @Mapping(target = "quiz", ignore = true)
    Bet of(BetEntity bet);
}
