package io.github.jtsato.moviesbattle.entrypoint.rest.domains.game;

import io.github.jtsato.moviesbattle.core.domains.game.model.Game;
import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Jorge Takeshi Sato
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GamePresenter {

    public static GameResponse of(final Game game) {
        return new GameResponse(game.id(),
                                     of(game.player()),
                                        game.status().name(),
                                        game.createdAt(),
                                        game.updatedAt());
    }

    public static PlayerResponse of(final Player player) {
        return new PlayerResponse(player.id(),
                                  player.name(),
                                  player.email(),
                                  player.createdAt(),
                                  player.updatedAt());
    }
}
