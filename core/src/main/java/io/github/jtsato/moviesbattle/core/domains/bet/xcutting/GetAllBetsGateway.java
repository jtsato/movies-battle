package io.github.jtsato.moviesbattle.core.domains.bet.xcutting;

import io.github.jtsato.moviesbattle.core.domains.bet.model.Bet;

import java.util.List;

/**
 * @author Jorge Takeshi Sato
 */

@FunctionalInterface
public interface GetAllBetsGateway {

    List<Bet> execute();
}
