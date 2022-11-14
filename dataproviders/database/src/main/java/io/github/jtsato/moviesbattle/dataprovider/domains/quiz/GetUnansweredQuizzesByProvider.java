package io.github.jtsato.moviesbattle.dataprovider.domains.quiz;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import io.github.jtsato.moviesbattle.core.domains.game.xcutting.GetUnansweredQuizzesByGateway;
import io.github.jtsato.moviesbattle.core.domains.quiz.model.Quiz;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetUnansweredQuizzesByProvider implements GetUnansweredQuizzesByGateway {

    private final QuizMapper quizMapper = Mappers.getMapper(QuizMapper.class);

    QuizRepository quizRepository;

    @Override
    public Optional<Quiz> execute(final Long gameId) {
        final EntityGraph entityGraph = EntityGraphUtils.fromAttributePaths("game");
        Optional<QuizEntity> quizEntity = quizRepository.findByGameIdAndBetIsNull(gameId, entityGraph);
        return quizEntity.map(quizMapper::of);
    }
}
