package io.github.jtsato.moviesbattle.core.domains.game.xcutting;

import io.github.jtsato.moviesbattle.core.domains.game.model.Game;
import io.github.jtsato.moviesbattle.core.domains.game.model.Status;

import java.util.Optional;

@FunctionalInterface
public interface GetGameByPlayerIdAndStatusGateway {

    Optional<Game> execute(final Long playerId, final Status status);
}
