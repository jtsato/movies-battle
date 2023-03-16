package io.github.jtsato.moviesbattle.core.domains.game.usecase.end;

import io.github.jtsato.moviesbattle.core.common.GetLocalDateTime;
import io.github.jtsato.moviesbattle.core.domains.game.model.Game;
import io.github.jtsato.moviesbattle.core.domains.game.model.Status;
import io.github.jtsato.moviesbattle.core.domains.game.xcutting.GetGameByPlayerIdAndStatusGateway;
import io.github.jtsato.moviesbattle.core.domains.game.xcutting.GetUnansweredQuizzesByGateway;
import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerCommand;
import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerUseCase;
import io.github.jtsato.moviesbattle.core.domains.player.xcutting.GetPlayerByEmailGateway;
import io.github.jtsato.moviesbattle.core.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Jorge Takeshi Sato
 */

@DisplayName("End Game Use Case Test")
class EndGameUseCaseTest {

    @Mock
    private final GetPlayerByEmailGateway getPlayerByEmailGateway = Mockito.mock(GetPlayerByEmailGateway.class);
    @Mock
    private final RegisterPlayerUseCase registerPlayerUseCase = Mockito.mock(RegisterPlayerUseCase.class);
    @Mock
    private final GetGameByPlayerIdAndStatusGateway getGameByPlayerIdAndStatusGateway = Mockito.mock(GetGameByPlayerIdAndStatusGateway.class);
    @Mock
    private final GetUnansweredQuizzesByGateway getUnansweredQuizzesByGateway = Mockito.mock(GetUnansweredQuizzesByGateway.class);
    @Mock
    private final GetLocalDateTime getLocalDateTime = Mockito.mock(GetLocalDateTime.class);
    @Mock
    private final UpdateGameStatusByIdGateway updateGameStatusByIdGateway = Mockito.mock(UpdateGameStatusByIdGateway.class);
    @InjectMocks
    private final EndGameUseCase endGameUseCase = new EndGameUseCaseImpl(getPlayerByEmailGateway, registerPlayerUseCase,
            getGameByPlayerIdAndStatusGateway, getUnansweredQuizzesByGateway, getLocalDateTime, updateGameStatusByIdGateway);

    @DisplayName("Fail to end a game if there is no game in progress")
    @Test
    void failToEndANewGameIfThereIsAGameInProgress() {
        // Arrange
        when(getPlayerByEmailGateway.execute("john.smith.zero@xyz.com"))
                .thenReturn(Optional.empty());

        when(registerPlayerUseCase.execute(new RegisterPlayerCommand("john.smith.zero@xyz.com", "John Smith")))
                .thenReturn(createJohnSmithPlayer());

        when(getGameByPlayerIdAndStatusGateway.execute(1L, Status.IN_PROGRESS))
                .thenReturn(Optional.empty());

        final EndGameCommand command = new EndGameCommand("john.smith.zero@xyz.com", "John Smith");

        // Act
        final Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> endGameUseCase.execute(command));

        // Assert
        assertThat(exception).isInstanceOf(NotFoundException.class);

        final NotFoundException notFoundException = (NotFoundException) exception;
        assertThat(notFoundException.getArgs()).contains(String.valueOf(1L));
        assertThat(notFoundException.getMessage()).isEqualTo("validation.game.in.progress.notfound");
    }

    @DisplayName("Successfully to end a game if there is a game in progress")
    @Test
    void successfullyToEndANewGameIfThereIsAGameInProgress() {
        // Arrange
        when(getPlayerByEmailGateway.execute("john.smith.zero@xyz.com"))
                .thenReturn(Optional.empty());

        when(registerPlayerUseCase.execute(new RegisterPlayerCommand("john.smith.zero@xyz.com", "John Smith")))
                .thenReturn(createJohnSmithPlayer());

        when(getGameByPlayerIdAndStatusGateway.execute(1L, Status.IN_PROGRESS))
                .thenReturn(Optional.of(createGameInProgress()));

        when(getUnansweredQuizzesByGateway.execute(1L))
                .thenReturn(Optional.empty());

        when(getLocalDateTime.now())
                .thenReturn(LocalDateTime.parse("2020-03-12T22:04:59.123"));

        when(updateGameStatusByIdGateway.execute(createGameOverToBeUpdated()))
                .thenReturn(Optional.of(createGameOverUpdated()));

        final EndGameCommand command = new EndGameCommand("john.smith.zero@xyz.com", "John Smith");

        // Act
        final Game game = endGameUseCase.execute(command);

        // Assert
        assertThat(game).isNotNull();
        assertThat(game.id()).isEqualTo(1L);
        assertThat(game.status()).isEqualTo(Status.OVER);
        assertThat(game.createdAt()).isEqualTo(LocalDateTime.parse("2020-03-12T22:04:59.123"));
        assertThat(game.updatedAt()).isEqualTo(LocalDateTime.parse("2020-03-12T22:04:59.123"));

        final Player player = game.player();
        assertThat(player).isNotNull();
        assertThat(player.id()).isEqualTo(1L);
        assertThat(player.email()).isEqualTo("john.smith.zero@xyz.com");
        assertThat(player.name()).isEqualTo("John Smith");
        assertThat(player.createdAt()).isEqualTo(LocalDateTime.parse("2020-03-12T22:04:59.123"));
        assertThat(player.updatedAt()).isEqualTo(LocalDateTime.parse("2020-03-12T22:04:59.123"));
    }

    private static Player createJohnSmithPlayer() {
        return new Player(1L, "John Smith", "john.smith.zero@xyz.com",
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123"));
    }

    private static Game createGameInProgress() {
        return new Game(1L, createJohnSmithPlayer(), Status.IN_PROGRESS,
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123")
        );
    }

    private static Game createGameOverToBeUpdated() {
        return new Game(1L, createJohnSmithPlayer(), Status.OVER,
                null, LocalDateTime.parse("2020-03-12T22:04:59.123")
        );
    }

    private static Game createGameOverUpdated() {
        return new Game(1L, createJohnSmithPlayer(), Status.OVER,
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123")
        );
    }
}
