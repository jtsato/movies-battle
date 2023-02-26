package io.github.jtsato.moviesbattle.dataprovider.domains.bet;

import io.github.jtsato.moviesbattle.core.domains.bet.model.Bet;
import io.github.jtsato.moviesbattle.core.domains.quiz.model.Quiz;
import io.github.jtsato.moviesbattle.dataprovider.domains.quiz.QuizEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Jorge Takeshi Sato
 */

@Mapper
public interface BetMapper {

    Bet of(final BetEntity betEntity);
    BetEntity of(final Bet bet);

    @Mapping(target = "bet", ignore = true)
    QuizEntity of(Quiz quiz);

    @Mapping(target = "bet", ignore = true)
    Quiz of(QuizEntity quiz);
}