package io.github.jtsato.moviesbattle.dataprovider.domains.quiz;

import io.github.jtsato.moviesbattle.core.domains.bet.model.Bet;
import io.github.jtsato.moviesbattle.core.domains.quiz.model.Quiz;
import io.github.jtsato.moviesbattle.dataprovider.domains.bet.BetEntity;
import io.github.jtsato.moviesbattle.dataprovider.domains.game.GameMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Jorge Takeshi Sato
 */

@Mapper(uses = {GameMapper.class})
public interface QuizMapper {

    Quiz of(final QuizEntity quizEntity);
    QuizEntity of(final Quiz quiz);

    @Mappings({@Mapping(target = "quiz", ignore = true)})
    BetEntity of(Bet bet);

    @Mappings({@Mapping(target = "quiz", ignore = true)})
    Bet of(BetEntity bet);
}
