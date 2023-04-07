package io.github.jtsato.moviesbattle.core.domains.player;

import io.github.jtsato.moviesbattle.core.common.GetLocalDateTime;
import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerCommand;
import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerGateway;
import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerUseCase;
import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Jorge Takeshi Sato
 */

@DisplayName("Register Player Use Case Test")
class RegisterPlayerUseCaseTest {

    @Mock
    private final RegisterPlayerGateway registerPlayerGateway = Mockito.mock(RegisterPlayerGateway.class);
    @Mock
    private final GetLocalDateTime getLocalDateTime = Mockito.mock(GetLocalDateTime.class);

    @InjectMocks
    private final RegisterPlayerUseCase registerPlayerUseCase =
            new RegisterPlayerUseCaseImpl(registerPlayerGateway, getLocalDateTime);

    @DisplayName("Successful to register player with valid parameters")
    @Test
    void successfulToRegisterPlayerWithValidParameters() {
        // Arrange
        when(getLocalDateTime.now())
                .thenReturn(LocalDateTime.parse("2020-03-12T22:04:59.123"));

        when(registerPlayerGateway.execute(
                new Player(null,
                        "John Smith",
                        "john.smith.zero@xyz.com",
                        LocalDateTime.parse("2020-03-12T22:04:59.123"),
                        LocalDateTime.parse("2020-03-12T22:04:59.123"))))
                .thenReturn(buildJohnSmithPlayer());

        final RegisterPlayerCommand registerPlayerCommand =
                new RegisterPlayerCommand("john.smith.zero@xyz.com", "John Smith");

        // Act
        final Player player = registerPlayerUseCase.execute(registerPlayerCommand);

        // Assert
        assertThat(player).isNotNull();
        assertThat(player.id()).isEqualTo(1L);
        assertThat(player.name()).isEqualTo("John Smith");
        assertThat(player.email()).isEqualTo("john.smith.zero@xyz.com");
        assertThat(player.createdAt()).isEqualTo(LocalDateTime.parse("2020-03-12T22:04:59.123"));
        assertThat(player.updatedAt()).isEqualTo(LocalDateTime.parse("2020-03-12T22:04:59.123"));
    }

    private static Player buildJohnSmithPlayer() {
        return new Player(1L, "John Smith", "john.smith.zero@xyz.com",
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123"));
    }
}
