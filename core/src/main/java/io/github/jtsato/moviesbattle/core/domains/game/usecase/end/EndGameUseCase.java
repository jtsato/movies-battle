package io.github.jtsato.moviesbattle.core.domains.game.usecase.end;

import io.github.jtsato.moviesbattle.core.domains.game.model.Game;

/**
 * @author Jorge Takeshi Sato
 */

@FunctionalInterface
public interface EndGameUseCase {

    Game execute(final EndGameCommand parameters);
}
