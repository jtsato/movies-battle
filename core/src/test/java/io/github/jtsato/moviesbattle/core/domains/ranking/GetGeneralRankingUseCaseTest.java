package io.github.jtsato.moviesbattle.core.domains.ranking;

import io.github.jtsato.moviesbattle.core.domains.bet.model.Bet;
import io.github.jtsato.moviesbattle.core.domains.bet.xcutting.GetAllBetsGateway;
import io.github.jtsato.moviesbattle.core.domains.game.model.Game;
import io.github.jtsato.moviesbattle.core.domains.game.model.Status;
import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerCommand;
import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerUseCase;
import io.github.jtsato.moviesbattle.core.domains.player.xcutting.GetPlayerByEmailGateway;
import io.github.jtsato.moviesbattle.core.domains.quiz.model.Quiz;
import io.github.jtsato.moviesbattle.core.domains.ranking.models.Ranking;
import io.github.jtsato.moviesbattle.core.domains.ranking.usecase.GetGeneralRankingUseCase;
import io.github.jtsato.moviesbattle.core.domains.ranking.usecase.GetGeneralRankingUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Jorge Takeshi Sato
 */

class GetGeneralRankingUseCaseTest {

    @Mock private final GetPlayerByEmailGateway getPlayerByEmailGateway = Mockito.mock(GetPlayerByEmailGateway.class);
    @Mock private final RegisterPlayerUseCase registerPlayerUseCase = Mockito.mock(RegisterPlayerUseCase.class);
    @Mock private final GetAllBetsGateway getAllBetsGateway = Mockito.mock(GetAllBetsGateway.class);

    @InjectMocks
    private final GetGeneralRankingUseCase getGeneralRankingUseCase =
            new GetGeneralRankingUseCaseImpl(getPlayerByEmailGateway, registerPlayerUseCase, getAllBetsGateway);

    @DisplayName("Fail to get general ranking if there is no player")
    @Test
    void failToGetGeneralRankingIfThereIsNoPlayer() {

        // Arrange
        when(getPlayerByEmailGateway.execute("john.smith.zero@xyz.com"))
                .thenReturn(Optional.empty());

        when(registerPlayerUseCase.execute(new RegisterPlayerCommand("john.smith.zero@xyz.com", "John Smith")))
                .thenReturn(buildJohnSmithPlayer());

        when(getAllBetsGateway.execute())
                .thenReturn(Collections.emptyList());

        // Act
        final Ranking ranking = getGeneralRankingUseCase.execute();

        // Assert
        assertThat(ranking).isNotNull();
        assertThat(ranking.getPlayersScores()).isEmpty();
    }

    @DisplayName("Success to get general ranking")
    @Test
    void successToGetGeneralRanking() {

        // Arrange
        when(getPlayerByEmailGateway.execute("john.smith.zero@xyz.com"))
                .thenReturn(Optional.of(buildJohnSmithPlayer()));

        when(getAllBetsGateway.execute())
                .thenReturn(buildBets());

        // Act
        final Ranking ranking = getGeneralRankingUseCase.execute();

        // Assert
        assertThat(ranking).isNotNull();
        assertThat(ranking.getPlayersScores()).isNotEmpty();
        assertThat(ranking.getPlayersScores()).hasSize(1);
    }

    private List<Bet> buildBets() {
        return List.of(
                new Bet(1L, buildQuizOne(), "tt0468569", true, LocalDateTime.parse("2020-03-12T22:04:59.123"), LocalDateTime.parse("2020-03-12T22:04:59.123")),
                new Bet(2L, buildQuizTwo(), "tt1375666", true, LocalDateTime.parse("2020-03-12T22:04:59.123"), LocalDateTime.parse("2020-03-12T22:04:59.123"))
        );
    }

    private static Player buildJohnSmithPlayer() {
        return new Player(1L, "John Smith", "john.smith.zero@xyz.com",
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123"));
    }

    private static Quiz buildQuizOne() {
        return new Quiz(1L, buildGame(), null,
                "tt0468569", "The Dark Knight", "2008",
                "tt1375666", "Inception", "2010",
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123"));
    }

    private static Quiz buildQuizTwo() {
        return new Quiz(2L, buildGame(), null,
                "tt0137523", "Fight Club", "1999",
                "tt0109830", "Forrest Gump", "1994",
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123"));
    }

    private static Game buildGame() {
        return new Game(1L, buildJohnSmithPlayer(), Status.OVER, LocalDateTime.parse("2020-03-12T22:04:59.123"), LocalDateTime.parse("2020-03-12T22:04:59.123"));
    }
}
