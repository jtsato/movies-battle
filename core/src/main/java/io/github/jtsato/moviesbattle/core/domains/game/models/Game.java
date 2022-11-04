package io.github.jtsato.moviesbattle.core.domains.game.models;

import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Jorge Takeshi Sato
 */

@Generated
@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Game implements Serializable {

    private static final long serialVersionUID = -5142356851101054436L;

    private final Long id;
    private final Player player;
    private final Status status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
