package io.github.jtsato.moviesbattle.infra.domains.game;

import io.github.jtsato.moviesbattle.core.domains.game.model.Game;
import io.github.jtsato.moviesbattle.core.domains.game.model.Status;
import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jorge Takeshi Sato
 */

@DisplayName("Register Game Provider Test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Import({RegisterGameProvider.class})
@Sql("RegisterGameProviderTest.sql")
class RegisterGameProviderTest {

    @Autowired
    private RegisterGameProvider registerGameProvider;

    @Autowired
    private GameRepository gameRepository;

    @DisplayName("Register Game")
    @Test
    void successfulToRegisterGame() {
        // Arrange
        final Player player = new Player(
                1L,
                "John Smith",
                "john.smith.zero@xyz.com",
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123"));

        final Game newGame = new Game(
                null,
                player,
                Status.IN_PROGRESS,
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123")
        );

        // Act
        final Game game = registerGameProvider.execute(newGame);

        // Assert
        assertThat(game).isNotNull();
        assertThat(game.id()).isNotNull();
        assertThat(game.player()).isNotNull();
        assertThat(game.player()).isEqualTo(player);
        assertThat(game.status()).isEqualTo(Status.IN_PROGRESS);
        assertThat(game.createdAt()).isEqualTo(LocalDateTime.parse("2020-03-12T22:04:59.123"));
        assertThat(game.updatedAt()).isEqualTo(LocalDateTime.parse("2020-03-12T22:04:59.123"));

        assertThat(gameRepository.count()).isEqualTo(2L);
    }
}
