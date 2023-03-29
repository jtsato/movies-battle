package io.github.jtsato.moviesbattle.infra.domains.movie;

import io.github.jtsato.moviesbattle.core.domains.movie.model.Movie;
import io.github.jtsato.moviesbattle.core.domains.movie.xcutting.GetRandomMovieGateway;
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
public class GetRandomMovieService implements GetRandomMovieGateway {

    private static final MovieResponseMapper MOVIE_RESPONSE_MAPPER = Mappers.getMapper(MovieResponseMapper.class);
    private final MoviesClient moviesClient;

    @Override
    public Optional<Movie> execute(int index) {
        final MovieResponse response = moviesClient.getRandomMovie();
        return Optional.of(MOVIE_RESPONSE_MAPPER.of(response));
    }
}
