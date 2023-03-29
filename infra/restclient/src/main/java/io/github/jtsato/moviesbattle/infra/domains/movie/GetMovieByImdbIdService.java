package io.github.jtsato.moviesbattle.infra.domains.movie;

import io.github.jtsato.moviesbattle.core.domains.movie.model.Movie;
import io.github.jtsato.moviesbattle.core.domains.movie.xcutting.GetMovieByImdbIdGateway;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@Primary
@RequiredArgsConstructor
@Service
public class GetMovieByImdbIdService implements GetMovieByImdbIdGateway {

    private static final MovieResponseMapper MOVIE_RESPONSE_MAPPER = Mappers.getMapper(MovieResponseMapper.class);
    private final MoviesClient moviesClient;

    public Optional<Movie> execute(final String imdbId) {
        final MovieResponse response = moviesClient.getMovieByImdbId(imdbId);
        final Movie movie = MOVIE_RESPONSE_MAPPER.of(response);
        return Optional.ofNullable(movie);
    }
}
