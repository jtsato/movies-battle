package io.github.jtsato.moviesbattle.infra.domains.movie;

import io.github.jtsato.moviesbattle.core.domains.movie.model.Movie;
import io.github.jtsato.moviesbattle.core.domains.movie.xcutting.GetMovieByImdbIdGateway;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@RequiredArgsConstructor
@Service
public class GetMovieByImdbIdRestClientProvider implements GetMovieByImdbIdGateway {

    private static final MovieRestClientMapper MOVIE_RESPONSE_MAPPER = Mappers.getMapper(MovieRestClientMapper.class);
    private final MoviesRestClient moviesRestClient;

    public Optional<Movie> execute(final String imdbId) {
        final MovieResponse response = moviesRestClient.getMovieByImdbId(imdbId);
        final Movie movie = MOVIE_RESPONSE_MAPPER.of(response);
        return Optional.ofNullable(movie);
    }
}
