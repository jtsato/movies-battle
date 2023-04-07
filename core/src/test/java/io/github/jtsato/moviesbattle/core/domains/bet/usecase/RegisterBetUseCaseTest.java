package io.github.jtsato.moviesbattle.core.domains.bet.usecase;

import io.github.jtsato.moviesbattle.core.common.GetLocalDateTime;
import io.github.jtsato.moviesbattle.core.domains.bet.model.Bet;
import io.github.jtsato.moviesbattle.core.domains.game.model.Game;
import io.github.jtsato.moviesbattle.core.domains.game.model.Status;
import io.github.jtsato.moviesbattle.core.domains.game.xcutting.GetGameByPlayerIdAndStatusGateway;
import io.github.jtsato.moviesbattle.core.domains.game.xcutting.GetUnansweredQuizzesByGateway;
import io.github.jtsato.moviesbattle.core.domains.movie.model.Movie;
import io.github.jtsato.moviesbattle.core.domains.movie.xcutting.GetMovieByImdbIdGateway;
import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerCommand;
import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerUseCase;
import io.github.jtsato.moviesbattle.core.domains.player.xcutting.GetPlayerByEmailGateway;
import io.github.jtsato.moviesbattle.core.domains.quiz.model.Quiz;
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

@DisplayName("Register Bet Use Case Test")
class RegisterBetUseCaseTest {

    @Mock
    private final GetPlayerByEmailGateway getPlayerByEmailGateway = Mockito.mock(GetPlayerByEmailGateway.class);
    @Mock
    private final RegisterPlayerUseCase registerPlayerUseCase = Mockito.mock(RegisterPlayerUseCase.class);
    @Mock
    private final GetGameByPlayerIdAndStatusGateway getGameByPlayerIdAndStatusGateway = Mockito.mock(GetGameByPlayerIdAndStatusGateway.class);
    @Mock
    private final GetUnansweredQuizzesByGateway getUnansweredQuizzesByGateway = Mockito.mock(GetUnansweredQuizzesByGateway.class);
    @Mock
    private final GetMovieByImdbIdGateway getMovieByImdbIdGateway = Mockito.mock(GetMovieByImdbIdGateway.class);
    @Mock
    private final GetLocalDateTime getLocalDateTime = Mockito.mock(GetLocalDateTime.class);
    @Mock
    private final RegisterBetGateway registerBetGateway = Mockito.mock(RegisterBetGateway.class);

    @InjectMocks
    private final RegisterBetUseCase registerBetUseCase =
            new RegisterBetUseCaseImpl(getPlayerByEmailGateway, registerPlayerUseCase, getGameByPlayerIdAndStatusGateway, getUnansweredQuizzesByGateway, getMovieByImdbIdGateway, getLocalDateTime, registerBetGateway);

    @DisplayName("Successful to register bet with valid parameters")
    @Test
    void successfulToRegisterBetWithValidParameters() {
        // Arrange
        when(getPlayerByEmailGateway.execute("john.smith.zero@xyz.com"))
                .thenReturn(Optional.of(buildJohnSmithPlayer()));

        when(getGameByPlayerIdAndStatusGateway.execute(1L, Status.IN_PROGRESS))
                .thenReturn(Optional.of(buildGameInProgress()));

        when(getLocalDateTime.now())
                .thenReturn(LocalDateTime.parse("2020-03-12T22:04:59.123"));

        when(registerPlayerUseCase.execute(new RegisterPlayerCommand("john.smith.zero@xyz.com", "John Smith")))
                .thenReturn(buildJohnSmithPlayer());

        when(getUnansweredQuizzesByGateway.execute(1L))
                .thenReturn(Optional.of(buildUnansweredQuiz()));

        when(getMovieByImdbIdGateway.execute("tt0111161"))
                .thenReturn(Optional.of(buildTheShawshankRedemptionMovie()));

        when(getMovieByImdbIdGateway.execute("tt0468569"))
                .thenReturn(Optional.of(buildTheDarkKnightMovie()));

        when(registerBetGateway.execute(new Bet(null,
                buildUnansweredQuiz(),
                "tt0468569",
                Boolean.FALSE,
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123"))))
                .thenReturn(buildBet());

        // Act
        final Bet bet = registerBetUseCase.execute(
                new RegisterBetCommand("john.smith.zero@xyz.com", "John Smith", "tt0468569"));

        // Assert
        assertThat(bet).isNotNull();
        assertThat(bet.id()).isNotNull();
        assertThat(bet.quiz()).isNotNull();
        assertThat(bet.quiz().id()).isEqualTo(1L);
        assertThat(bet.quiz().game()).isNotNull();
        assertThat(bet.quiz().game()).isEqualTo(buildGameInProgress());
        assertThat(bet.quiz().optionOneId()).isEqualTo("tt0111161");
        assertThat(bet.quiz().optionOneTitle()).isEqualTo("The Shawshank Redemption");
        assertThat(bet.quiz().optionOneYear()).isEqualTo("1994");
        assertThat(bet.quiz().optionTwoId()).isEqualTo("tt0468569");
        assertThat(bet.quiz().optionTwoTitle()).isEqualTo("The Dark Knight");
        assertThat(bet.quiz().optionTwoYear()).isEqualTo("2008");
        assertThat(bet.quiz().createdAt()).isEqualTo(LocalDateTime.parse("2020-03-12T22:04:59.123"));
        assertThat(bet.quiz().updatedAt()).isEqualTo(LocalDateTime.parse("2020-03-12T22:04:59.123"));
        assertThat(bet.winTheBet()).isFalse();
        assertThat(bet.createdAt()).isEqualTo(LocalDateTime.parse("2020-03-12T22:04:59.123"));
        assertThat(bet.updatedAt()).isEqualTo(LocalDateTime.parse("2020-03-12T22:04:59.123"));
        assertThat(bet.optionId()).isEqualTo("tt0468569");
    }

    private Bet buildBet() {
        return new Bet(1L, buildUnansweredQuiz(), "tt0468569", Boolean.FALSE,
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123"));
    }

    private Movie buildTheShawshankRedemptionMovie() {
        return new Movie(1L, "tt0111161", "The Shawshank Redemption", "1994",
                "Drama", 9.3F, 2651602L, 24659898.6F, "http://poster.url");
    }

    private Movie buildTheDarkKnightMovie() {
        return new Movie(2L, "tt0468569", "The Dark Knight", "2008",
                "Action, Crime, Drama", 9.0F, 2628154L, 23653386F, "http://poster.url");
    }

    private static Player buildJohnSmithPlayer() {
        return new Player(1L, "John Smith", "john.smith.zero@xyz.com",
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123"));
    }

    private Game buildGameInProgress() {
        return new Game(1L,
                buildJohnSmithPlayer(),
                Status.IN_PROGRESS,
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123"));
    }

    private Quiz buildUnansweredQuiz() {
        return new Quiz(1L,
                buildGameInProgress(),
                null,
                "tt0111161",
                "The Shawshank Redemption",
                "1994",
                "tt0468569",
                "The Dark Knight",
                "2008",
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123"));
    }
}
