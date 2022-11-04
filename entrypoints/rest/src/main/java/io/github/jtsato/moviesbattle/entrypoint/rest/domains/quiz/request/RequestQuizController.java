package io.github.jtsato.moviesbattle.entrypoint.rest.domains.quiz.request;

import io.github.jtsato.moviesbattle.core.domains.quiz.model.Quiz;
import io.github.jtsato.moviesbattle.core.domains.quiz.usecase.RequestQuizCommand;
import io.github.jtsato.moviesbattle.core.domains.quiz.usecase.RequestQuizUseCase;
import io.github.jtsato.moviesbattle.entrypoint.rest.common.WebRequest;
import io.github.jtsato.moviesbattle.entrypoint.rest.common.metric.LogExecutionTime;
import io.github.jtsato.moviesbattle.entrypoint.rest.domains.quiz.QuizPresenter;
import io.github.jtsato.moviesbattle.entrypoint.rest.domains.quiz.QuizResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/*
 * A EntryPoint follows these steps:
 *
 * - Maps HTTP requests to Java objects
 * - Performs authorization checks
 * - Maps input to the input model of the use case
 * - Calls the use case
 * - Maps the output of the use case back to HTTP Returns an HTTP response
 */

/**
 * @author Jorge Takeshi Sato
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/quizzes")
public class RequestQuizController implements RequestQuizApiMethod {

    private static final Logger log = LoggerFactory.getLogger(RequestQuizController.class);

    private final RequestQuizUseCase requestQuizUseCase;
    private final WebRequest webRequest;

    @Override
    @LogExecutionTime
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public QuizResponse execute() {
        log.info("Starting Controller -> RequestQuizController for Player {}", webRequest.getEmail());
        final RequestQuizCommand command = new RequestQuizCommand(webRequest.getEmail(), webRequest.getFullName());
        final Quiz quiz = requestQuizUseCase.execute(command);
        return QuizPresenter.of(quiz);
    }
}
