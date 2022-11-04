package io.github.jtsato.moviesbattle.core.domains.game.usecases.end;

import io.github.jtsato.moviesbattle.core.domains.game.models.Game;

import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@FunctionalInterface
public interface UpdateGameStatusByIdGateway {

    Optional<Game> execute(final Game game);
}
