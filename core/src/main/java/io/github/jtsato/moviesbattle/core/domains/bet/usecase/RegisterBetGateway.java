package io.github.jtsato.moviesbattle.core.domains.bet.usecase;

import io.github.jtsato.moviesbattle.core.domains.bet.model.Bet;

/**
 * @author Jorge Takeshi Sato
 */

@FunctionalInterface
public interface RegisterBetGateway {

    Bet execute(final Bet bet);
}
