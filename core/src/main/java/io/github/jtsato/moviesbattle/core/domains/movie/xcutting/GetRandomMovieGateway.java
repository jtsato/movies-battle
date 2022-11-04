package io.github.jtsato.moviesbattle.core.domains.movie.xcutting;

import io.github.jtsato.moviesbattle.core.domains.movie.model.Movie;

import java.util.Optional;

@FunctionalInterface
public interface GetRandomMovieGateway {

    Optional<Movie> execute();
}