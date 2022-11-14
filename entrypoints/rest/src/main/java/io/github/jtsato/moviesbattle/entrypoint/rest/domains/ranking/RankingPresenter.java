package io.github.jtsato.moviesbattle.entrypoint.rest.domains.ranking;

import io.github.jtsato.moviesbattle.core.domains.ranking.models.Ranking;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jorge Takeshi Sato
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RankingPresenter {

    public static RankingResponse of(final Ranking ranking) {
        final List<PlayerScoreResponse> scores = ranking
                .getPlayersScores()
                .stream()
                .map(PlayerScorePresenter::of)
                .sorted(Comparator.comparing(PlayerScoreResponse::score).reversed())
                .collect(Collectors.toList());
        return new RankingResponse(scores);
    }
}
