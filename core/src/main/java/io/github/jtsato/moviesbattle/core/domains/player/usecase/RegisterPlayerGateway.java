package io.github.jtsato.moviesbattle.core.domains.player.usecase;

import io.github.jtsato.moviesbattle.core.domains.player.model.Player;

/**
 * @author Jorge Takeshi Sato
 */

@FunctionalInterface
public interface RegisterPlayerGateway {

    Player execute(final Player player);
}
