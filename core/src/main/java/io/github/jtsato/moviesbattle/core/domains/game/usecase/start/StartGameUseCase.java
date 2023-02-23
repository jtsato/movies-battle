package io.github.jtsato.moviesbattle.core.domains.game.usecase.start;

import io.github.jtsato.moviesbattle.core.domains.game.model.Game;

/**
 * @author Jorge Takeshi Sato
 */

@FunctionalInterface
public interface StartGameUseCase {

    Game execute(final StartGameCommand command);
}
