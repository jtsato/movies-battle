package io.github.jtsato.moviesbattle.dataprovider.domains.movie;

import io.github.jtsato.moviesbattle.core.domains.movie.model.Movie;
import io.github.jtsato.moviesbattle.core.domains.movie.xcutting.GetRandomMovieGateway;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@Transactional(readOnly = true)
@Service
public class GetRandomMovieProvider implements GetRandomMovieGateway {

    private final MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);

    @Autowired
    MovieRepository movieRepository;

    @Override
    public Optional<Movie> execute() {
        final long count = movieRepository.count();
        final int index = (int) (Math.random() * count);
        final Optional<MovieEntity> optional = movieRepository.findAll(PageRequest.of(index, 1)).stream().findFirst();
        return optional.map(movieMapper::of);
    }
}
