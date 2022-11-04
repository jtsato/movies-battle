package io.github.jtsato.moviesbattle.core.domains.game.usecases.start;

import io.github.jtsato.moviesbattle.core.domains.game.models.Game;

/**
 * @author Jorge Takeshi Sato
 */

@FunctionalInterface
public interface StartGameUseCase {

    Game execute(final StartGameCommand command);
}
