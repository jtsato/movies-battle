package io.github.jtsato.moviesbattle.entrypoint.rest.domains.bet;

import io.github.jtsato.moviesbattle.core.domains.bet.model.Bet;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Jorge Takeshi Sato
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BetPresenter {

    public static BetResponse of(final Bet bet) {
        return new BetResponse(bet.id(),
                bet.optionId(),
                bet.winTheBet(),
                bet.createdAt(),
                bet.updatedAt());
    }
}
