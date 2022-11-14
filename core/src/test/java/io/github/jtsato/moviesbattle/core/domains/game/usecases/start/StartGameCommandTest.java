package io.github.jtsato.moviesbattle.core.domains.game.usecases.start;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jorge Takeshi Sato
 */

@DisplayName("Start Game Command Test")
public class StartGameCommandTest {

    @DisplayName("Fail to create StartGameCommand with empty email")
    @Test
    void failToCreateStartGameCommandWithEmptyEmail() {

        final Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> new StartGameCommand(null, "playerName"));

        assertThat(exception).isInstanceOf(ConstraintViolationException.class);

        final ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
        assertThat(constraintViolationException.getConstraintViolations()).hasSize(1);
        assertThat(constraintViolationException.getMessage()).contains("playerEmail: validation.player.email.blank");
    }

    @DisplayName("Fail to create StartGameCommand with empty name")
    @Test
    void failToCreateStartGameCommandWithEmptyName() {

        final Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> new StartGameCommand("playerEmail", null));

        assertThat(exception).isInstanceOf(ConstraintViolationException.class);

        final ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
        assertThat(constraintViolationException.getConstraintViolations()).hasSize(1);
        assertThat(constraintViolationException.getMessage()).contains("playerName: validation.player.name.blank");
    }
}
