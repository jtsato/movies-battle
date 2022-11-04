package io.github.jtsato.moviesbattle.core.domains.player.xcutting;

import io.github.jtsato.moviesbattle.core.domains.player.model.Player;

import java.util.Optional;

/**
 * @author Jorge Takeshi Sato
 */

@FunctionalInterface
public interface GetPlayerByEmailGateway {

    Optional<Player> execute(final String email);
}