package io.github.jtsato.moviesbattle.dataprovider.domains.quiz;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import io.github.jtsato.moviesbattle.core.domains.quiz.model.Quiz;
import io.github.jtsato.moviesbattle.core.domains.quiz.usecase.GetAllQuizzesByGameIdGateway;
import io.github.jtsato.moviesbattle.dataprovider.common.ListMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jorge Takeshi Sato
 */

@Transactional(readOnly = true)
@Service
public class GetAllQuizzesByGameIdProvider implements GetAllQuizzesByGameIdGateway {

    private final ListMapper<Quiz, QuizEntity> listMapper = new ListMapper<>() { };
    private final QuizMapper quizMapper = Mappers.getMapper(QuizMapper.class);

    @Autowired
    QuizRepository quizRepository;

    @Override
    public List<Quiz> execute(Long gameId) {
        final EntityGraph entityGraph = EntityGraphUtils.fromAttributePaths("game", "bet");
        final List<QuizEntity> quizEntities = quizRepository.findByGameId(gameId, entityGraph);
        return listMapper.of(quizEntities, quizMapper::of);
    }
}
