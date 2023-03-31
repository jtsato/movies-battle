package io.github.jtsato.moviesbattle.infra.domains.movie;

import io.github.jtsato.moviesbattle.core.domains.movie.model.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * @author Jorge Takeshi Sato
 */

@DisplayName("Get Random Movie Provider Test")
class GetRandomMovieServiceTest {

    @Mock
    private final MoviesRestClient moviesRestClient = Mockito.mock(MoviesRestClient.class);

    @InjectMocks
    private final GetRandomMovieRestClientProvider getRandomMovieProvider = new GetRandomMovieRestClientProvider(moviesRestClient);

    @DisplayName("Get Random Movie")
    @Test
    void successfulToGetRandomMovie() {
        // Arrange
        when(moviesRestClient.getRandomMovie())
                .thenReturn(buildMovieResponse());

        // Act
        final Optional<Movie> optional = getRandomMovieProvider.execute(0);

        // Assert
        Assertions.assertThat(optional).isPresent();

        final Movie movie = optional.get();

        Assertions.assertThat(movie).isNotNull();

        Assertions.assertThat(movie.id()).isEqualTo(1L);
        Assertions.assertThat(movie.imdbId()).isEqualTo("tt0468569");
        Assertions.assertThat(movie.title()).isEqualTo("The Dark Knight");
        Assertions.assertThat(movie.year()).isEqualTo("2008");
        Assertions.assertThat(movie.genre()).isEqualTo("Action, Crime, Drama");
        Assertions.assertThat(movie.imdbRating()).isEqualTo(9.0F);
        Assertions.assertThat(movie.imdbVotes()).isEqualTo(2628154L);
        Assertions.assertThat(movie.score()).isEqualTo(23653386L);
        Assertions.assertThat(movie.posterUrl()).isEqualTo("http.poster.url");
    }

    private static MovieResponse buildMovieResponse() {
        return new MovieResponse(1L, "tt0468569", "The Dark Knight", "2008", "Action, Crime, Drama", 9.0F, 2628154L, 23653386F, "http.poster.url");
    }
}
