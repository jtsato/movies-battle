package io.github.jtsato.moviesbattle.core.domains.bet.usecase;

import io.github.jtsato.moviesbattle.core.common.GetLocalDateTime;
import io.github.jtsato.moviesbattle.core.domains.bet.model.Bet;
import io.github.jtsato.moviesbattle.core.domains.game.model.Game;
import io.github.jtsato.moviesbattle.core.domains.game.model.Status;
import io.github.jtsato.moviesbattle.core.domains.game.xcutting.GetGameByPlayerIdAndStatusGateway;
import io.github.jtsato.moviesbattle.core.domains.game.xcutting.GetUnansweredQuizzesByGateway;
import io.github.jtsato.moviesbattle.core.domains.movie.model.Movie;
import io.github.jtsato.moviesbattle.core.domains.movie.xcutting.GetMovieByImdbIdGateway;
import io.github.jtsato.moviesbattle.core.domains.player.xcutting.GetPlayerByEmailGateway;
import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerCommand;
import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerUseCase;
import io.github.jtsato.moviesbattle.core.domains.quiz.model.Quiz;
import io.github.jtsato.moviesbattle.core.exception.InvalidActionException;
import io.github.jtsato.moviesbattle.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

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
public class RegisterBetUseCaseImpl implements RegisterBetUseCase {

    private final GetPlayerByEmailGateway getPlayerByEmailGateway;
    private final RegisterPlayerUseCase registerPlayerUseCase;
    private final GetGameByPlayerIdAndStatusGateway getGameByPlayerIdAndStatusGateway;
    private final GetUnansweredQuizzesByGateway getUnansweredQuizzesByGateway;
    private final GetMovieByImdbIdGateway getMovieByImdbIdGateway;
    private final GetLocalDateTime getLocalDateTime;
    private final RegisterBetGateway registerBetGateway;

    @Override
    public Bet execute(final RegisterBetCommand command) {

        final Player player = getPlayerOrRegister(command.getPlayerEmail(), command.getPlayerName());
        final Game game = getGameInProgressByPlayerId(player.id());
        final Optional<Quiz> optional = getUnansweredQuizByGameId(game.id());

        if (optional.isEmpty()){
            throw new NotFoundException("validation.game.no.quizzes.to.be.answered", game.id());
        }

        final Quiz quiz = optional.get();

        if (StringUtils.equals(quiz.optionOneId(), command.getOptionId()) && StringUtils.equals(quiz.optionTwoId(), command.getOptionId())){
            throw new InvalidActionException("validation.bet.invalid.option", command.getOptionId(), quiz.id());
        }

        Movie movieOne = getMovieByImdbId(quiz.optionOneId());
        Movie movieTwo = getMovieByImdbId(quiz.optionTwoId());

        final String winner = (movieOne.score() >= movieTwo.score()) ? movieOne.imdbId() : movieTwo.imdbId();
        final boolean winTheBet = StringUtils.equals(winner, command.getOptionId());
        final LocalDateTime createdAt = getLocalDateTime.now();
        final LocalDateTime updatedAt = getLocalDateTime.now();

        final Bet bet = new Bet(null, quiz, command.getOptionId(), winTheBet, createdAt, updatedAt);

        return registerBetGateway.execute(bet);
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
        Optional<Game> optionalGame = getGameByPlayerIdAndStatusGateway.execute(playerId, Status.IN_PROGRESS);
        return optionalGame.orElseThrow(() -> new NotFoundException("validation.game.in.progress.notfound", String.valueOf(playerId)));
    }

    private Optional<Quiz> getUnansweredQuizByGameId(final Long gameId) {
        return getUnansweredQuizzesByGateway.execute(gameId);
    }

    private Movie getMovieByImdbId(final String ImdbId) {
        Optional<Movie> optional = getMovieByImdbIdGateway.execute(ImdbId);
        return optional.orElseThrow(() -> new NotFoundException("validation.movie.notfound", ImdbId));
    }
}
