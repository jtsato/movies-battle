package io.github.jtsato.moviesbattle.dataprovider.domains.bet;

import io.github.jtsato.moviesbattle.core.domains.bet.model.Bet;
import io.github.jtsato.moviesbattle.core.domains.bet.usecase.RegisterBetGateway;
import io.github.jtsato.moviesbattle.dataprovider.domains.quiz.QuizRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jorge Takeshi Sato
 */

@Transactional
@Service
public class RegisterBetProvider implements RegisterBetGateway {

    private final BetMapper betMapper = Mappers.getMapper(BetMapper.class);

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Bet execute(final Bet bet) {
        final BetEntity betEntity = betMapper.of(bet);
        quizRepository.findById(bet.getQuiz().getId()).ifPresent(quizEntity -> {
            quizEntity.setBet(betEntity);
            betEntity.setQuiz(quizEntity);
        });
        final BetEntity betEntity1 = betRepository.saveAndFlush(betEntity);
        return betMapper.of(betEntity1);
    }
}
