package io.github.jtsato.moviesbattle.core.domains.game.usecases.end;

import io.github.jtsato.moviesbattle.core.domains.game.models.Game;

/**
 * @author Jorge Takeshi Sato
 */

@FunctionalInterface
public interface EndGameUseCase {

    Game execute(final EndGameCommand parameters);
}
