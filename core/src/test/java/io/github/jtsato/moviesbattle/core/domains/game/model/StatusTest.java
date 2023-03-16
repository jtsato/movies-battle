package io.github.jtsato.moviesbattle.core.domains.game.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jorge Takeshi Sato
 */

@DisplayName("Status functions")
class StatusTest {

    @DisplayName("Successful to run status functions")
    @Test
    void successfulToRunStatusFunctions() {
        // Arrange
        // Act
        // Assert
        assertThat(Status.IN_PROGRESS.is(Status.IN_PROGRESS)).isTrue();
        assertThat(Status.IN_PROGRESS.isNot(Status.IN_PROGRESS)).isFalse();
        assertThat(Status.IN_PROGRESS.isNot(Status.OVER)).isTrue();
        assertThat(Status.IN_PROGRESS.in(Status.OVER)).isFalse();
        assertThat(Status.IN_PROGRESS.notIn(Status.IN_PROGRESS)).isFalse();
        assertThat(Status.IN_PROGRESS.in(Status.IN_PROGRESS)).isTrue();
        assertThat(Status.IN_PROGRESS.notIn(Status.OVER)).isTrue();
        assertThat(Status.IN_PROGRESS.getMessageKey()).isEqualTo("enum-status-in-progress");
        assertThat(Status.OVER.getMessageKey()).isEqualTo("enum-status-over");
    }
}
