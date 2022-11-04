package io.github.jtsato.moviesbattle.core.domains.game.usecases.start;

import io.github.jtsato.moviesbattle.core.common.GetLocalDateTime;
import io.github.jtsato.moviesbattle.core.domains.game.models.Game;
import io.github.jtsato.moviesbattle.core.domains.game.models.Status;
import io.github.jtsato.moviesbattle.core.domains.game.xcutting.GetGameByPlayerIdAndStatusGateway;
import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerCommand;
import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerUseCase;
import io.github.jtsato.moviesbattle.core.domains.player.xcutting.GetPlayerByEmailGateway;
import io.github.jtsato.moviesbattle.core.exception.InvalidActionException;
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

@DisplayName("Start Game Use Case Test")
public class StartGameUseCaseTest {

    @Mock private final GetPlayerByEmailGateway getPlayerByEmailGateway = Mockito.mock(GetPlayerByEmailGateway.class);
    @Mock private final RegisterPlayerUseCase registerPlayerUseCase = Mockito.mock(RegisterPlayerUseCase.class);
    @Mock private final GetGameByPlayerIdAndStatusGateway getGameByPlayerIdAndStatusGateway = Mockito.mock(GetGameByPlayerIdAndStatusGateway.class);
    @Mock private final GetLocalDateTime getLocalDateTime = Mockito.mock(GetLocalDateTime.class);
    @Mock private final RegisterGameGateway registerGameGateway = Mockito.mock(RegisterGameGateway.class);

    @InjectMocks
    private StartGameUseCase startGameUseCase =
            new StartGameUseCaseImpl(getPlayerByEmailGateway, registerPlayerUseCase,
                    getGameByPlayerIdAndStatusGateway, getLocalDateTime, registerGameGateway);

    @DisplayName("Fail to start a new game if there is a game in progress")
    @Test
    void failToStartANewGameIfThereIsAGameInProgress() {

        when(getPlayerByEmailGateway.execute("john.smith.zero@xyz.com"))
                .thenReturn(Optional.empty());

        when(registerPlayerUseCase.execute(new RegisterPlayerCommand("john.smith.zero@xyz.com", "John Smith")))
                .thenReturn(buildJohnSmithPlayer());

        when(getGameByPlayerIdAndStatusGateway.execute(1L, Status.IN_PROGRESS))
                .thenReturn(Optional.of(buildRegisteredGame()));

        final StartGameCommand command = new StartGameCommand("john.smith.zero@xyz.com", "John Smith");

        final Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> startGameUseCase.execute(command));

        assertThat(exception).isInstanceOf(InvalidActionException.class);

        final InvalidActionException invalidActionException = (InvalidActionException) exception;
        assertThat(invalidActionException.getArgs()).contains(String.valueOf(1L));
        assertThat(invalidActionException.getMessage()).isEqualTo("validation.game.already.in.progress");
    }

    @DisplayName("Successfully to start a new game if there is a game in progress")
    @Test
    void successfullyToStartANewGameIfThereIsAGameInProgress() {

        when(getPlayerByEmailGateway.execute("john.smith.zero@xyz.com"))
                .thenReturn(Optional.empty());

        when(registerPlayerUseCase.execute(new RegisterPlayerCommand("john.smith.zero@xyz.com", "John Smith")))
                .thenReturn(buildJohnSmithPlayer());

        when(getGameByPlayerIdAndStatusGateway.execute(1L, Status.IN_PROGRESS))
                .thenReturn(Optional.empty());

        when(getLocalDateTime.now())
                .thenReturn(LocalDateTime.parse("2020-03-12T22:04:59.123"));

        when(registerGameGateway.execute(buildGameToBeRegistered()))
                .thenReturn(buildRegisteredGame());

        final StartGameCommand command = new StartGameCommand("john.smith.zero@xyz.com", "John Smith");

        final Game game = startGameUseCase.execute(command);
        assertThat(game).isNotNull();
        assertThat(game.getId()).isEqualTo(1L);
        assertThat(game.getStatus()).isEqualTo(Status.IN_PROGRESS);
        assertThat(game.getCreatedAt()).isEqualTo(LocalDateTime.parse("2020-03-12T22:04:59.123"));
        assertThat(game.getUpdatedAt()).isEqualTo(LocalDateTime.parse("2020-03-12T22:04:59.123"));

        final Player player = game.getPlayer();
        assertThat(player).isNotNull();
        assertThat(player.getId()).isEqualTo(1L);
        assertThat(player.getEmail()).isEqualTo("john.smith.zero@xyz.com");
        assertThat(player.getName()).isEqualTo("John Smith");
        assertThat(player.getCreatedAt()).isEqualTo(LocalDateTime.parse("2020-03-12T22:04:59.123"));
        assertThat(player.getUpdatedAt()).isEqualTo(LocalDateTime.parse("2020-03-12T22:04:59.123"));
    }

    private static Player buildJohnSmithPlayer() {
        return new Player(1L, "John Smith", "john.smith.zero@xyz.com",
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123"));
    }

    private static Game buildGameToBeRegistered() {
        return new Game(null, buildJohnSmithPlayer(), Status.IN_PROGRESS,
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123")
        );
    }

    private static Game buildRegisteredGame() {
        return new Game(1L, buildJohnSmithPlayer(), Status.IN_PROGRESS,
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123")
        );
    }
}
