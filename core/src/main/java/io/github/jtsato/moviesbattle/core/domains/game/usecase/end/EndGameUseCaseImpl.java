package io.github.jtsato.moviesbattle.core.domains.game.usecase.end;

import io.github.jtsato.moviesbattle.core.common.GetLocalDateTime;
import io.github.jtsato.moviesbattle.core.domains.game.model.Game;
import io.github.jtsato.moviesbattle.core.domains.game.model.Status;
import io.github.jtsato.moviesbattle.core.domains.game.xcutting.GetGameByPlayerIdAndStatusGateway;
import io.github.jtsato.moviesbattle.core.domains.game.xcutting.GetUnansweredQuizzesByGateway;
import io.github.jtsato.moviesbattle.core.domains.player.xcutting.GetPlayerByEmailGateway;
import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerCommand;
import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerUseCase;
import io.github.jtsato.moviesbattle.core.exception.InvalidActionException;
import io.github.jtsato.moviesbattle.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

import jakarta.inject.Named;
import java.time.LocalDateTime;
import java.util.Optional;

/*
 * A Use Case follows these steps:
 * - Takes input
 * - Validates business rules
 * - Manipulates the model state
 * - Returns output
 */

/**
 * @author Jorge Takeshi Sato
 */

@Named
@RequiredArgsConstructor
public class EndGameUseCaseImpl implements EndGameUseCase {

    private final GetPlayerByEmailGateway getPlayerByEmailGateway;
    private final RegisterPlayerUseCase registerPlayerUseCase;
    private final GetGameByPlayerIdAndStatusGateway getGameByPlayerIdAndStatusGateway;
    private final GetUnansweredQuizzesByGateway getUnansweredQuizzesByGateway;
    private final GetLocalDateTime getLocalDateTime;
    private final UpdateGameStatusByIdGateway updateGameStatusByIdGateway;

    @Override
    public Game execute(final EndGameCommand command) {

        final Player player = getPlayerOrRegister(command.getPlayerEmail(), command.getPlayerName());
        final Long gameId = getGameInProgressByPlayerId(player.id()).id();

        checkIfGameHasUnansweredQuiz(gameId);

        final LocalDateTime updatedAt = getLocalDateTime.now();

        final Game game = new Game(gameId, player, Status.OVER, null, updatedAt);

        final Optional<Game> optional = updateGameStatusByIdGateway.execute(game);
        return optional.orElseThrow(() -> new NotFoundException("validation.game.id.notfound", String.valueOf(gameId)));
    }

    private Player getPlayerOrRegister(final String email, String name) {
        final Optional<Player> optional = getPlayerByEmailGateway.execute(email);
        return optional.orElseGet(() -> registerNewPlayer(email, name));
    }

    private Player registerNewPlayer(final String email, String name) {
        final RegisterPlayerCommand registerPlayerCommand = new RegisterPlayerCommand(email, name);
        return registerPlayerUseCase.execute(registerPlayerCommand);
    }

    private Game getGameInProgressByPlayerId(final Long playerId) {
        Optional<Game> optional = getGameByPlayerIdAndStatusGateway.execute(playerId, Status.IN_PROGRESS);
        return optional.orElseThrow(() -> new NotFoundException("validation.game.in.progress.notfound", String.valueOf(playerId)));
    }

    private void checkIfGameHasUnansweredQuiz(final Long gameId){
        getUnansweredQuizzesByGateway.execute(gameId)
            .ifPresent(quiz -> {
                final String messageKey = "validation.game.unanswered.quiz";
                throw new InvalidActionException(messageKey, String.valueOf(gameId));
            });
    }
}
