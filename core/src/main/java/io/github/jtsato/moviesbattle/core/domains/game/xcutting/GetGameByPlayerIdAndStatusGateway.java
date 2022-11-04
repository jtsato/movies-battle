package io.github.jtsato.moviesbattle.core.domains.game.xcutting;

import io.github.jtsato.moviesbattle.core.domains.game.models.Game;
import io.github.jtsato.moviesbattle.core.domains.game.models.Status;

import java.util.Optional;

@FunctionalInterface
public interface GetGameByPlayerIdAndStatusGateway {

    Optional<Game> execute(final Long playerId, final Status status);
}
