package io.github.jtsato.moviesbattle.infra.domains.movie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Jorge Takeshi Sato
 */

@DisplayName("Get All Movies Count Provider Test")
class GetAllMoviesCountServiceTest {

    @Mock
    private final MoviesRestClient moviesRestClient = Mockito.mock(MoviesRestClient.class);

    @InjectMocks
    private final GetAllMoviesCountRestClientProvider getAllMoviesCountProvider = new GetAllMoviesCountRestClientProvider(moviesRestClient);

    @DisplayName("Get All Movies Count")
    @Test
    void successfulToGetAllMoviesCount() {
        // Arrange
        when(moviesRestClient.getAllMoviesCount())
                .thenReturn(new MoviesCountResponse(2L));

        // Act
        final Long actual = getAllMoviesCountProvider.execute();

        // Assert
        assertThat(actual).isEqualTo(2L);
    }
}
