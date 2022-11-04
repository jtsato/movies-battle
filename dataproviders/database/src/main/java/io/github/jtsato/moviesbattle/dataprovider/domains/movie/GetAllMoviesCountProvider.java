package io.github.jtsato.moviesbattle.dataprovider.domains.movie;

import io.github.jtsato.moviesbattle.core.domains.movie.xcutting.GetAllMoviesCountGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jorge Takeshi Sato
 */

@Transactional(readOnly = true)
@Service
public class GetAllMoviesCountProvider implements GetAllMoviesCountGateway {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public Long execute() {
        return movieRepository.count();
    }
}