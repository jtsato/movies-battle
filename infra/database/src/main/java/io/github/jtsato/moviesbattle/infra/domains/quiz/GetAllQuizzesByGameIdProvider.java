package io.github.jtsato.moviesbattle.infra.domains.quiz;

import com.cosium.spring.data.jpa.entity.graph.domain2.DynamicEntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import io.github.jtsato.moviesbattle.core.domains.quiz.model.Quiz;
import io.github.jtsato.moviesbattle.core.domains.quiz.usecase.GetAllQuizzesByGameIdGateway;
import io.github.jtsato.moviesbattle.infra.common.ListMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jorge Takeshi Sato
 */

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetAllQuizzesByGameIdProvider implements GetAllQuizzesByGameIdGateway {

    private final ListMapper<Quiz, QuizEntity> listMapper = new ListMapper<>() { };
    private final QuizMapper quizMapper = Mappers.getMapper(QuizMapper.class);

    private final QuizRepository quizRepository;

    @Override
    public List<Quiz> execute(Long gameId) {
        final EntityGraph entityGraph = DynamicEntityGraph.loading().addPath("game").addPath("bet").build();
        final List<QuizEntity> quizEntities = quizRepository.findByGameId(gameId, entityGraph);

        return listMapper.of(quizEntities, quizMapper::of);
    }
}
