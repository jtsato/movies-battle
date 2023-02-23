package io.github.jtsato.moviesbattle.entrypoint.rest.domains.game.end;

import io.github.jtsato.moviesbattle.core.domains.game.model.Game;
import io.github.jtsato.moviesbattle.core.domains.game.usecase.end.EndGameCommand;
import io.github.jtsato.moviesbattle.core.domains.game.usecase.end.EndGameUseCase;
import io.github.jtsato.moviesbattle.entrypoint.rest.common.WebRequest;
import io.github.jtsato.moviesbattle.entrypoint.rest.common.metric.LogExecutionTime;
import io.github.jtsato.moviesbattle.entrypoint.rest.domains.game.GamePresenter;
import io.github.jtsato.moviesbattle.entrypoint.rest.domains.game.GameResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
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
@RequestMapping("v1/games")
public class EndGameController implements EndGameApiMethod {

    private static final Logger log = LoggerFactory.getLogger(EndGameController.class);

    private final EndGameUseCase endGameUseCase;
    private final WebRequest webRequest;

    @Override
    @LogExecutionTime
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping
    public GameResponse execute() {
        log.info("Starting Controller -> EndGameController for Player {}", webRequest.getEmail());
        final EndGameCommand command = new EndGameCommand(webRequest.getEmail(), webRequest.getFullName());
        final Game game = endGameUseCase.execute(command);
        return GamePresenter.of(game);
    }
}
