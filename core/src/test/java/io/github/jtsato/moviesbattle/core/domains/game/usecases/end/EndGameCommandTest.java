package io.github.jtsato.moviesbattle.core.domains.game.usecases.end;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jorge Takeshi Sato
 */

@DisplayName("End Game Command Test")
public class EndGameCommandTest {

    @DisplayName("Fail to create EndGameCommand with empty email")
    @Test
    void failToCreateEndGameCommandWithEmptyEmail() {

        final Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> new EndGameCommand(null, "playerName"));

        assertThat(exception).isInstanceOf(ConstraintViolationException.class);

        final ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
        assertThat(constraintViolationException.getConstraintViolations()).hasSize(1);
        assertThat(constraintViolationException.getMessage()).contains("playerEmail: validation.player.email.blank");
    }

    @DisplayName("Fail to create EndGameCommand with empty name")
    @Test
    void failToCreateEndGameCommandWithEmptyName() {

        final Exception exception =
                Assertions.assertThrows(Exception.class,
                        () -> new EndGameCommand("playerEmail", null));

        assertThat(exception).isInstanceOf(ConstraintViolationException.class);

        final ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
        assertThat(constraintViolationException.getConstraintViolations()).hasSize(1);
        assertThat(constraintViolationException.getMessage()).contains("playerName: validation.player.name.blank");
    }
}
