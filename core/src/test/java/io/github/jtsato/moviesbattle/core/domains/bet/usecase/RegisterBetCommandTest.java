package io.github.jtsato.moviesbattle.core.domains.bet.usecase;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jorge Takeshi Sato
 */

@DisplayName("Register Bet Command Test")
class RegisterBetCommandTest {

    @DisplayName("Fail to create RegisterBetCommand with empty email")
    @Test
    void failToCreateRegisterBetCommandWithEmptyEmail() {
        // Arrange
        // Act
        final Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> new RegisterBetCommand(null, "playerName", "tt0468569"));

        // Assert
        assertThat(exception).isInstanceOf(ConstraintViolationException.class);

        final ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
        assertThat(constraintViolationException.getConstraintViolations()).hasSize(1);
        assertThat(constraintViolationException.getMessage()).contains("playerEmail: validation.player.email.blank");
    }

    @DisplayName("Fail to create RegisterBetCommand with empty name")
    @Test
    void failToCreateRegisterBetCommandWithEmptyName() {
        // Arrange
        // Act
        final Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> new RegisterBetCommand("playerEmail", null, "tt0468569"));

        // Assert
        assertThat(exception).isInstanceOf(ConstraintViolationException.class);

        final ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
        assertThat(constraintViolationException.getConstraintViolations()).hasSize(1);
        assertThat(constraintViolationException.getMessage()).contains("playerName: validation.player.name.blank");
    }

    @DisplayName("Fail to create RegisterBetCommand with empty optionId")
    @Test
    void failToCreateRegisterBetCommandWithEmptyOptionId() {
        // Arrange
        // Act
        final Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> new RegisterBetCommand("playerEmail", "playerName", null));

        // Assert
        assertThat(exception).isInstanceOf(ConstraintViolationException.class);

        final ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
        assertThat(constraintViolationException.getConstraintViolations()).hasSize(1);
        assertThat(constraintViolationException.getMessage()).contains("optionId: validation.bet.option.blank");
    }

    @DisplayName("Successfully create RegisterBetCommand")
    @Test
    void successfullyCreateRegisterBetCommand() {
        // Arrange
        // Act
        final RegisterBetCommand command = new RegisterBetCommand("playerEmail", "playerName", "tt0468569");

        // Assert
        assertThat(command.getPlayerEmail()).isEqualTo("playerEmail");
        assertThat(command.getPlayerName()).isEqualTo("playerName");
        assertThat(command.getOptionId()).isEqualTo("tt0468569");
    }
}
