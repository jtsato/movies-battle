package io.github.jtsato.moviesbattle.entrypoint.rest.domains.game;

import io.github.jtsato.moviesbattle.core.domains.game.models.Game;
import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Jorge Takeshi Sato
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GamePresenter {

    public static GameResponse of(final Game game) {
        return new GameResponse(game.getId(),
                                     of(game.getPlayer()),
                                        game.getStatus().name(),
                                        game.getCreatedAt(),
                                        game.getUpdatedAt());
    }

    public static PlayerResponse of(final Player player) {
        return new PlayerResponse(player.getId(),
                                  player.getName(),
                                  player.getEmail(),
                                  player.getCreatedAt(),
                                  player.getUpdatedAt());
    }
}
