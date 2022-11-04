package io.github.jtsato.moviesbattle.core.domains.bet.usecase;

import io.github.jtsato.moviesbattle.core.domains.bet.model.Bet;

/**
 * @author Jorge Takeshi Sato
 */

@FunctionalInterface
public interface RegisterBetUseCase {

    Bet execute(final RegisterBetCommand command) ;
}
