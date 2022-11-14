package io.github.jtsato.moviesbattle.entrypoint.rest.domains.ranking;

import io.github.jtsato.moviesbattle.core.domains.ranking.models.PlayerScore;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Jorge Takeshi Sato
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlayerScorePresenter {

    public static PlayerScoreResponse of(final PlayerScore playerScore) {
        return new PlayerScoreResponse(playerScore.playerEmail(),
                playerScore.playerName(),
                playerScore.score());
    }
}
