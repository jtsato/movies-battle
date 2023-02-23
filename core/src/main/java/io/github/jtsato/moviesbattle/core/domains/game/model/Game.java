package io.github.jtsato.moviesbattle.core.domains.game.model;

import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Jorge Takeshi Sato
 */

@Generated
public record Game(
        Long id,
        Player player,
        Status status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt)
        implements Serializable {

    @Serial
    private static final long serialVersionUID = -5142356851101054436L;
}
