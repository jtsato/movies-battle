package io.github.jtsato.moviesbattle.core.domains.ranking.usecase;

import io.github.jtsato.moviesbattle.core.domains.ranking.models.Ranking;

/**
 * @author Jorge Takeshi Sato
 */

@FunctionalInterface
public interface GetGeneralRankingUseCase {

    Ranking execute();
}