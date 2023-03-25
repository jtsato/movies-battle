package io.github.jtsato.moviesbattle.core.domains.quiz.usecase;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jorge Takeshi Sato
 */

@DisplayName("Request Quiz Command Test")
class RequestQuizCommandTest {

    @DisplayName("Fail to create RequestQuizCommand with empty email")
    @Test
    void failToCreateRequestQuizCommandWithEmptyEmail() {
        // Arrange
        // Act
        final Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> new RequestQuizCommand(null, "playerName"));

        // Assert
        assertThat(exception).isInstanceOf(ConstraintViolationException.class);

        final ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
        assertThat(constraintViolationException.getConstraintViolations()).hasSize(1);
        assertThat(constraintViolationException.getMessage()).contains("playerEmail: validation.player.email.blank");
    }

    @DisplayName("Fail to create RequestQuizCommand with empty name")
    @Test
    void failToCreateRequestQuizCommandWithEmptyName() {
        // Arrange
        // Act
        final Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> new RequestQuizCommand("playerEmail", null));

        // Assert
        assertThat(exception).isInstanceOf(ConstraintViolationException.class);

        final ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
        assertThat(constraintViolationException.getConstraintViolations()).hasSize(1);
        assertThat(constraintViolationException.getMessage()).contains("playerName: validation.player.name.blank");
    }
}
