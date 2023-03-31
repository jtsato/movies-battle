package io.github.jtsato.moviesbattle.infra.domains.movie;

import io.github.jtsato.moviesbattle.core.domains.movie.model.Movie;
import org.mapstruct.Mapper;

/**
 * @author Jorge Takeshi Sato
 */

@Mapper
public interface MovieRestClientMapper {

    Movie of(final MovieResponse movieResponse);

    MovieResponse of(final Movie movie);
}
