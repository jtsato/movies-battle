package io.github.jtsato.moviesbattle.entrypoint.rest.domains.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Jorge Takeshi Sato
 */

@Getter
@AllArgsConstructor
public class GameResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1698092149790294590L;

    private final Long id;
    private final PlayerResponse player;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
