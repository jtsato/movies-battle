package io.github.jtsato.moviesbattle.infra.domains.movie;

import io.github.jtsato.moviesbattle.core.domains.movie.model.Movie;
import io.github.jtsato.moviesbattle.core.domains.movie.xcutting.GetRandomMovieGateway;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@RequiredArgsConstructor
@Service
public class GetRandomMovieRestClientProvider implements GetRandomMovieGateway {

    private static final MovieRestClientMapper MOVIE_RESPONSE_MAPPER = Mappers.getMapper(MovieRestClientMapper.class);
    private final MoviesRestClient moviesRestClient;

    @Override
    public Optional<Movie> execute(int index) {
        final MovieResponse response = moviesRestClient.getRandomMovie();
        return Optional.of(MOVIE_RESPONSE_MAPPER.of(response));
    }
}
