package io.github.jtsato.moviesbattle.core.domains.player;

import io.github.jtsato.moviesbattle.core.domains.player.usecase.RegisterPlayerCommand;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jorge Takeshi Sato
 */

@DisplayName("Register Player Command Test")
class RegisterPlayerCommandTest {

    @DisplayName("Fail to create RegisterPlayerCommand with empty email")
    @Test
    void failToCreateRegisterPlayerCommandWithEmptyEmail() {
        // Arrange
        // Act
        final Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> new RegisterPlayerCommand(null, "playerName"));

        // Assert
        assertThat(exception).isInstanceOf(ConstraintViolationException.class);

        final ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
        assertThat(constraintViolationException.getConstraintViolations()).hasSize(1);
        assertThat(constraintViolationException.getMessage()).contains("email: validation.player.email.blank");
    }

    @DisplayName("Fail to create RegisterPlayerCommand with empty name")
    @Test
    void failToCreateRegisterPlayerCommandWithEmptyName() {
        // Arrange
        // Act
        final Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> new RegisterPlayerCommand("playerEmail", null));

        // Assert
        assertThat(exception).isInstanceOf(ConstraintViolationException.class);

        final ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
        assertThat(constraintViolationException.getConstraintViolations()).hasSize(1);
        assertThat(constraintViolationException.getMessage()).contains("name: validation.player.name.blank");
    }

    @DisplayName("Successfully create RegisterPlayerCommand")
    @Test
    void successfullyCreateRegisterPlayerCommand() {
        // Arrange
        // Act
        final RegisterPlayerCommand command = new RegisterPlayerCommand("playerEmail", "playerName");

        // Assert
        assertThat(command.getEmail()).isEqualTo("playerEmail");
        assertThat(command.getName()).isEqualTo("playerName");
    }
}
