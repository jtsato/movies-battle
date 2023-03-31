package io.github.jtsato.moviesbattle.infra.domains.movie;

import io.github.jtsato.moviesbattle.core.domains.movie.xcutting.GetAllMoviesCountGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jorge Takeshi Sato
 */

@RequiredArgsConstructor
@Service
public class GetAllMoviesCountRestClientProvider implements GetAllMoviesCountGateway {

    private final MoviesRestClient moviesRestClient;

    @Override
    public Long execute() {
        final MoviesCountResponse response = moviesRestClient.getAllMoviesCount();
        return response.count();
    }
}
