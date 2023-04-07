package io.github.jtsato.moviesbattle.core.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Jorge Takeshi Sato
 */

@DisplayName("Get Local Date Test")
class GetLocalDateTest {

    @Mock
    private GetLocalDate getLocalDate = Mockito.mock(GetLocalDate.class);

    private final GetLocalDateImpl getLocalDateImpl = new GetLocalDateImpl();

    @DisplayName("Successful to get local date")
    @Test
    void successfulToGetLocalDate() {
        // Arrange
        when(getLocalDate.now())
                .thenReturn(LocalDate.parse("2020-03-12"));

        // Act
        final LocalDate localDate = getLocalDate.now();

        // Assert
        assertThat(localDate).isNotNull()
                .isInstanceOf(LocalDate.class)
                .isEqualTo(LocalDate.parse("2020-03-12"));
    }

    @DisplayName("Successful to get local date with default implementation")
    @Test
    void successfulToGetLocalDateWithDefaultConstructor() {
        // Arrange
        // Act
        final LocalDate localDate = getLocalDateImpl.now();

        // Assert
        assertThat(localDate)
                .isNotNull()
                .isInstanceOf(LocalDate.class);
    }
}
