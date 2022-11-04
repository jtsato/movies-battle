package io.github.jtsato.moviesbattle.dataprovider.domains.quiz;

import io.github.jtsato.moviesbattle.core.domains.quiz.model.Quiz;
import io.github.jtsato.moviesbattle.core.domains.quiz.usecase.RegisterQuizGateway;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jorge Takeshi Sato
 */

@Transactional
@Service
public class RegisterQuizProvider implements RegisterQuizGateway {

    private final QuizMapper quizMapper = Mappers.getMapper(QuizMapper.class);
    
    @Autowired
    QuizRepository quizRepository;

    @Override
    public Quiz execute(final Quiz quiz) {
        final QuizEntity quizEntity = quizMapper.of(quiz);
        return quizMapper.of(quizRepository.saveAndFlush(quizEntity));
    }
}
