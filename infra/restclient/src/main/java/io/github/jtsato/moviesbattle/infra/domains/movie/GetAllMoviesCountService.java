package io.github.jtsato.moviesbattle.infra.domains.movie;

import io.github.jtsato.moviesbattle.core.domains.movie.xcutting.GetAllMoviesCountGateway;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author Jorge Takeshi Sato
 */

@Primary
@RequiredArgsConstructor
@Service
public class GetAllMoviesCountService implements GetAllMoviesCountGateway {

    private final MoviesClient moviesClient;

    @Override
    public Long execute() {
        final MoviesCountResponse response = moviesClient.getAllMoviesCount();
        return response.count();
    }
}
