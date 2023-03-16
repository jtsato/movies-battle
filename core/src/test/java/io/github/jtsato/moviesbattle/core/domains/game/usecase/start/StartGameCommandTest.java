package io.github.jtsato.moviesbattle.core.domains.game.usecase.start;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jorge Takeshi Sato
 */

@DisplayName("Start Game Command Test")
class StartGameCommandTest {

    @DisplayName("Fail to create StartGameCommand with empty email")
    @Test
    void failToCreateStartGameCommandWithEmptyEmail() {

        // Arrange
        // Act
        final Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> new StartGameCommand(null, "playerName"));

        // Assert
        assertThat(exception).isInstanceOf(ConstraintViolationException.class);

        final ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
        assertThat(constraintViolationException.getConstraintViolations()).hasSize(1);
        assertThat(constraintViolationException.getMessage()).contains("playerEmail: validation.player.email.blank");
    }

    @DisplayName("Fail to create StartGameCommand with empty name")
    @Test
    void failToCreateStartGameCommandWithEmptyName() {

        // Arrange
        // Act
        final Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> new StartGameCommand("playerEmail", null));

        // Assert
        assertThat(exception).isInstanceOf(ConstraintViolationException.class);

        final ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
        assertThat(constraintViolationException.getConstraintViolations()).hasSize(1);
        assertThat(constraintViolationException.getMessage()).contains("playerName: validation.player.name.blank");
    }
}
