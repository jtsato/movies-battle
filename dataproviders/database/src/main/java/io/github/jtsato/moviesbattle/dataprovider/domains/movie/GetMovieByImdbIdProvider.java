package io.github.jtsato.moviesbattle.dataprovider.domains.movie;

import io.github.jtsato.moviesbattle.core.domains.movie.model.Movie;
import io.github.jtsato.moviesbattle.core.domains.movie.xcutting.GetMovieByImdbIdGateway;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@Transactional(readOnly = true)
@Service
public class GetMovieByImdbIdProvider implements GetMovieByImdbIdGateway {

    private final MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);
    
    @Autowired
    MovieRepository movieRepository;

    @Override
    public Optional<Movie> execute(String ImdbId) {
        final Optional<MovieEntity> optional = movieRepository.findByImdbIdIgnoreCase(ImdbId);
        return optional.map(movieMapper::of);
    }
}
